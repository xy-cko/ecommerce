package com.company.ecommerce.service;

import com.company.ecommerce.entity.Cart;
import com.company.ecommerce.entity.Order;
import com.company.ecommerce.entity.PaymentType;
import com.company.ecommerce.entity.Product;
import com.company.ecommerce.entity.ProductCartItem;
import com.company.ecommerce.entity.Status;
import com.company.ecommerce.entity.User;
import io.jmix.core.DataManager;
import io.jmix.core.querycondition.PropertyCondition;
import io.jmix.flowui.Notifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Component
public class CartService {
    @Autowired
    private DataManager dataManager;

    @Autowired
    private Notifications notifications;

    @Transactional
    public void processCheckOut(Cart cart, PaymentType paymentType) {
        try {
            // Create order
            User user = cart.getCustomer();
            Order order = dataManager.create(Order.class);
            order.setCart(cart);
            Status status = dataManager.load(Status.class)
                    .query("SELECT s FROM Status s WHERE s.name = 'checked out'")
                    .one();
            order.setStatus(status);
            order.setDate(LocalDate.now());
            cart.setStatus(status);
            order.setTotal(calculateTotal(cart));
            List<ProductCartItem> productItemsInCart = dataManager.load(ProductCartItem.class)
                    .condition(PropertyCondition.equal("cart", cart)).list();
            for (ProductCartItem productItems : productItemsInCart) {
                productItems.setDate(LocalDate.now());
                productItems.setPaymentType(paymentType);
                Product product = productItems.getProduct();
                product.setUnitsSold(product.getUnitsSold() + productItems.getUnit());
                dataManager.save(product);
                dataManager.save(productItems);
            }
            if (paymentType == PaymentType.CASH) {
                if (user.getBalance() == null) {
                    user.setBalance(Double.valueOf(0));
                }
                for (ProductCartItem productItems : productItemsInCart) {
                    user.setBalance(user.getBalance() + productItems.getAmount());
                }
            }
            dataManager.save(user);
            dataManager.save(order);
            dataManager.save(cart);

        } catch (Exception e) {
            notifications.create("Checkout failed").show();
            e.printStackTrace();
        }
    }

    private double calculateTotal(Cart cart) {
        return dataManager.loadValue("select SUM(i.amount) from ProductCartItem i where i.cart = :cart", Double.class)
                .parameter("cart", cart)
                .one();
    }

    @Transactional
    public void addToCart(Cart cart, Product product, int units) {
        ProductCartItem existingItem = dataManager.load(ProductCartItem.class)
                .query("SELECT i FROM ProductCartItem i " +
                        "WHERE i.cart = :cart AND i.product = :product AND i.cart.status.name = 'not checked out'")
                .parameter("cart", cart)
                .parameter("product", product)
                .optional()
                .orElse(null);

        if (existingItem != null) {
            // Update existing item
            existingItem.setUnit(existingItem.getUnit() + units);
            existingItem.setAmount(existingItem.getUnit() * product.getPrice());
            dataManager.save(existingItem);
        } else {
            // Create new item
            ProductCartItem newItem = dataManager.create(ProductCartItem.class);
            newItem.setCart(cart);
            newItem.setProduct(product);
            newItem.setUnit(units);
            newItem.setAmount(units * product.getPrice());
            dataManager.save(newItem);
        }
        product.setStock(product.getStock().subtract(BigInteger.valueOf(units)));
        dataManager.save(product);
    }


    public Cart getOrCreateCart(User user) {
        return dataManager.load(Cart.class)
                .query("SELECT c FROM Cart c WHERE c.customer = :user and c.status.name = :status")
                .parameter("user", user)
                .parameter("status", "not checked out")
                .optional()
                .orElseGet(() -> {
                    Cart newCart = dataManager.create(Cart.class);
                    newCart.setCustomer(user);
                    newCart.setStatus(dataManager.load(Status.class).condition(PropertyCondition.equal("name", "not checked out")).one());
                    return dataManager.save(newCart);
                });
    }
}