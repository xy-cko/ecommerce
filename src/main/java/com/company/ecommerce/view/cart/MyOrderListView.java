package com.company.ecommerce.view.cart;

import com.company.ecommerce.entity.Cart;
import com.company.ecommerce.view.main.MainView;
import com.company.ecommerce.view.productcartitem.OrderItemListView;
import com.vaadin.flow.component.grid.ItemClickEvent;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteParameters;
import io.jmix.flowui.ViewNavigators;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;


@Route(value = "myOrders", layout = MainView.class)
@ViewController(id = "MyOrder.list")
@ViewDescriptor(path = "my-order-list-view.xml")
@LookupComponent("cartsDataGrid")
@DialogMode(width = "64em")
public class MyOrderListView extends StandardListView<Cart> {
    @Autowired
    private ViewNavigators viewNavigators;

    /*@Subscribe("cartsDataGrid")
    public void onCartsDataGridItemClick(final ItemClickEvent<Cart> event) {
        Cart selectedCart = event.getItem();
        if (selectedCart != null) {
            viewNavigators.listView(OrderItemListView.class)
                    .withRouteParameters(new RouteParameters("cartId", selectedCart.getId().toString()))
                    .navigate();
        }
    }
     */
}