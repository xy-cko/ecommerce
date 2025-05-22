package com.company.ecommerce.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@JmixEntity
@Table(name = "PRODUCT_CART_ITEM", indexes = {
        @Index(name = "IDX_PRODUCT_CART_ITEM_CART", columnList = "CART_ID"),
        @Index(name = "IDX_PRODUCT_CART_ITEM_PRODUCT", columnList = "PRODUCT_ID")
})
@Entity
public class ProductCartItem {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "PAYMENT_TYPE")
    private String paymentType;

    @Column(name = "DATE_")
    private LocalDate date;

    @JoinColumn(name = "CART_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cart cart;

    @JoinColumn(name = "PRODUCT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Column(name = "UNIT")
    private Integer unit;

    @Column(name = "AMOUNT")
    private Double amount;

    public PaymentType getPaymentType() {
        return paymentType == null ? null : PaymentType.fromId(paymentType);
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType == null ? null : paymentType.getId();
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}