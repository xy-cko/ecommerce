package com.company.ecommerce.view.product;

import com.company.ecommerce.entity.Product;
import com.company.ecommerce.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import io.jmix.flowui.view.navigation.ViewNavigationSupport;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "requestedproducts/:id", layout = MainView.class)
@ViewController(id = "RequestedProduct.detail")
@ViewDescriptor(path = "requested-product-detail-view.xml")
@EditedEntityContainer("productDc")
public class RequestedProductDetailView extends StandardDetailView<Product> {
    @Autowired
    private DataManager dataManager;
    @Autowired
    private Notifications notifications;
    @Autowired
    private ViewNavigationSupport viewNavigationSupport;
    @Subscribe(id = "acceptButton", subject = "clickListener")
    public void onAcceptButtonClick(final ClickEvent<JmixButton> event) {
        Product product = dataManager.load(Product.class)
                .id(getEditedEntity().getId()).one();
        product.setIsAccepted(true);
        dataManager.save(product);
        notifications.create("accepted the product.").withPosition(Notification.Position.MIDDLE).show();
        viewNavigationSupport.navigate("RequestedProduct.list");
    }
}