package com.company.ecommerce.view.cart;

import com.company.ecommerce.entity.Cart;
import com.company.ecommerce.entity.ProductCartItem;
import com.company.ecommerce.service.OrderItemsQuery;
import com.company.ecommerce.view.main.MainView;
import com.company.ecommerce.view.productcartitem.OrderItemListView;
import com.vaadin.flow.component.grid.ItemClickEvent;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteParam;
import com.vaadin.flow.router.RouteParameters;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.ViewNavigators;
import io.jmix.flowui.view.*;
import io.jmix.flowui.view.navigation.ViewNavigationSupport;
import org.springframework.beans.factory.annotation.Autowired;


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
    @Autowired
    private Notifications notifications;

    @Subscribe("cartsDataGrid")
    public void onCartsDataGridItemClick(final ItemClickEvent<Cart> event) {
        Cart selectedCart = event.getItem();
        if (selectedCart != null) {
            notifications.create(selectedCart.getId().toString()).show();
            OrderItemsQuery.setCartId(selectedCart.getId());
            viewNavigators.listView(ProductCartItem.class)
                    .withViewClass(OrderItemListView.class).navigate();


            /*viewNavigators.listView(ProductCartItem.class)
                    .withViewClass(OrderItemListView.class)
                    .withRouteParameters(new RouteParameters(
                            new RouteParam("cartId", selectedCart.getId().toString())
                    ))
                    .navigate();

             */

        }
    }
}