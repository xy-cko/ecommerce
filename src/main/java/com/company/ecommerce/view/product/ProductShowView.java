package com.company.ecommerce.view.product;

import com.company.ecommerce.service.CartService;
import com.company.ecommerce.entity.*;
import com.company.ecommerce.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.flowui.Dialogs;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.app.inputdialog.DialogActions;
import io.jmix.flowui.app.inputdialog.DialogOutcome;
import io.jmix.flowui.app.inputdialog.InputParameter;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import io.jmix.flowui.view.navigation.ViewNavigationSupport;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;

@Route(value = "productsShow/:id", layout = MainView.class)
@ViewController(id = "Product.show")
@ViewDescriptor(path = "product-show-view.xml")
@EditedEntityContainer("productDc")
public class ProductShowView extends StandardDetailView<Product> {
    @ViewComponent
    private InstanceContainer<Product> productDc;

    @Autowired
    private DataManager dataManager;

    @Autowired
    protected Dialogs dialogs;

    @Autowired
    private CurrentAuthentication currentAuthentication;

    @Autowired
    CartService cartService;

    @Autowired
    private ViewNavigationSupport viewNavigationSupport;

    @Autowired
    private Notifications notifications;
    @Subscribe(id = "addToCartButton", subject = "clickListener")
    public void onAddToCartButtonClick(final ClickEvent<JmixButton> event) {
        Product product = dataManager.load(Product.class)
                .id(getEditedEntity().getId())
                .one();
        dialogs.createInputDialog(this)
                .withHeader("add to cart")
                .withLabelsPosition(Dialogs.InputDialogBuilder.LabelsPosition.TOP)
                .withHeader("stock: " + product.getStock())
                .withParameter(
                        InputParameter.intParameter("unit")
                                .withLabel("Unit")
                                .withRequired(true)
                )
                .withActions(DialogActions.OK_CANCEL)
                .withCloseListener(closeEvent -> {
                    if(!closeEvent.closedWith(DialogOutcome.OK)){
                        return;
                    }
                    int unit = closeEvent.getValue("unit");
                    BigInteger stock = product.getStock();
                    if (stock.compareTo(BigInteger.valueOf(unit)) < 0) {
                        notifications.create("Not enough stock available")
                                .withType(Notifications.Type.ERROR)
                                .show();
                        return;
                    }
                    try {
                        User currentUser = (User) currentAuthentication.getUser();
                        Cart cart = cartService.getOrCreateCart(currentUser);
                        cartService.addToCart(cart, product, unit);

                        viewNavigationSupport.navigate("AllProduct.list");

                        notifications.create(unit + " items added to cart")
                                .withPosition(Notification.Position.MIDDLE)
                                .show();
                    } catch (Exception e) {
                        notifications.create("Error adding to cart: " + e.getMessage())
                                .withType(Notifications.Type.ERROR)
                                .show();
                    }
                })
                .open();
    }

}