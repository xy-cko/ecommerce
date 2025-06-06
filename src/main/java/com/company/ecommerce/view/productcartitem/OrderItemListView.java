package com.company.ecommerce.view.productcartitem;

import com.company.ecommerce.entity.ProductCartItem;
import com.company.ecommerce.service.OrderItemsQuery;
import com.company.ecommerce.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


@Route(value = "order-items", layout = MainView.class)
@ViewController(id = "OrderItem.list")
@ViewDescriptor(path = "order-item-list-view.xml")
@LookupComponent("productCartItemsDataGrid")
@DialogMode(width = "64em")
public class OrderItemListView extends StandardListView<ProductCartItem> {
    @ViewComponent
    private CollectionLoader<ProductCartItem> productCartItemsDl;

    @Subscribe
    public void onInit(BeforeShowEvent event) {
        UUID cartId = OrderItemsQuery.getCartId();
        productCartItemsDl.setParameter("cartId", cartId);
        productCartItemsDl.load();
    }

}