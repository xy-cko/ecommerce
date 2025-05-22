package com.company.ecommerce.view.productcartitem;

import com.company.ecommerce.entity.ProductCartItem;
import com.company.ecommerce.view.main.MainView;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Location;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteParameters;
import io.jmix.core.DataManager;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;



@Route(value = "order-items/:cartId", layout = MainView.class)
@ViewController(id = "OrderItem.list")
@ViewDescriptor(path = "order-item-list-view.xml")
@LookupComponent("productCartItemsDataGrid")
@DialogMode(width = "64em")
public class OrderItemListView extends StandardListView<ProductCartItem> {
    @ViewComponent
    private CollectionLoader<ProductCartItem> productCartItemsDl;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        
    }
}