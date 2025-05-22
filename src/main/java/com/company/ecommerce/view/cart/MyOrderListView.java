package com.company.ecommerce.view.cart;

import com.company.ecommerce.entity.Cart;
import com.company.ecommerce.entity.Order;
import com.company.ecommerce.view.main.MainView;
import com.company.ecommerce.view.productcartitem.OrderItemListView;
import com.vaadin.flow.component.grid.ItemClickEvent;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteParam;
import com.vaadin.flow.router.RouteParameters;
import io.jmix.flowui.ViewNavigators;
import io.jmix.flowui.view.*;
import io.jmix.flowui.view.navigation.ViewNavigationSupport;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;


@Route(value = "myOrders", layout = MainView.class)
@ViewController(id = "MyOrder.list")
@ViewDescriptor(path = "my-order-list-view.xml")
@LookupComponent("cartsDataGrid")
@DialogMode(width = "64em")
public class MyOrderListView extends StandardListView<Cart> {
    @Autowired
    private ViewNavigationSupport viewNavigationSupport;
    @Autowired
    private ViewNavigators viewNavigators;

    @Subscribe("cartsDataGrid")
    public void onCartsDataGridItemClick(final ItemClickEvent<Cart> event) {
        Cart cart = event.getItem();
        System.out.println(cart.getId().toString());
        if (cart != null) {
            UUID cartId = cart.getId();
            String cartIdString = cartId.toString();
            viewNavigationSupport.navigate(
                    OrderItemListView.class,
                    new RouteParameters(
                            new RouteParam("cartId", cartIdString)
                    )
            );
        }
    }

}