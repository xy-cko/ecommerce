package com.company.ecommerce.view.cart;

import com.company.ecommerce.entity.Cart;
import com.company.ecommerce.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "myOrders", layout = MainView.class)
@ViewController(id = "MyOrder.list")
@ViewDescriptor(path = "my-order-list-view.xml")
@LookupComponent("cartsDataGrid")
@DialogMode(width = "64em")
public class MyOrderListView extends StandardListView<Cart> {
}