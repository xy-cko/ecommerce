package com.company.ecommerce.view.productcartitem;

import com.company.ecommerce.entity.Cart;
import com.company.ecommerce.entity.PaymentType;
import com.company.ecommerce.entity.Product;
import com.company.ecommerce.entity.ProductCartItem;
import com.company.ecommerce.service.CartService;
import com.company.ecommerce.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.flowui.Dialogs;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.action.inputdialog.InputDialogAction;
import io.jmix.flowui.app.inputdialog.InputDialog;
import io.jmix.flowui.app.inputdialog.InputParameter;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.DialogMode;
import io.jmix.flowui.view.LookupComponent;
import io.jmix.flowui.view.StandardListView;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.ViewComponent;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import io.jmix.flowui.view.navigation.ViewNavigationSupport;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.List;


@Route(value = "my-cart-items", layout = MainView.class)
@ViewController(id = "MyCartItem.list")
@ViewDescriptor(path = "my-cart-item-list-view.xml")
@LookupComponent("productCartItemsDataGrid")
@DialogMode(width = "64em")
public class MyCartItemListView extends StandardListView<ProductCartItem> {
    @Autowired
    private DataManager dataManager;
    @ViewComponent
    private DataGrid<ProductCartItem> productCartItemsDataGrid;
    @Autowired
    private Dialogs dialog;
    @Autowired
    private Notifications notifications;
    @Autowired
    private CartService cartService;
    @Autowired
    private ViewNavigationSupport viewNavigationSupport;

    @Subscribe(id = "removeButton", subject = "clickListener")
    public void onRemoveButtonClick(final ClickEvent<JmixButton> event) {
        ProductCartItem editedCartItem = productCartItemsDataGrid.getSingleSelectedItem();
        Product product = editedCartItem.getProduct();
        product.setStock(product.getStock().add(BigInteger.valueOf(editedCartItem.getUnit())));
        dataManager.save(product);
    }

    @Subscribe(id = "checkOutButton", subject = "clickListener")
    public void onCheckOutButtonClick(final ClickEvent<JmixButton> event) {
        Cart cart = dataManager.load(Cart.class)
                .query("select c from Cart c where c.customer.id = :current_user_id and c.status.name = 'not checked out'")
                .one();
        List<ProductCartItem> items = dataManager.load(ProductCartItem.class)
                .query("select e from ProductCartItem e where e.cart.customer.id = :current_user_id and e.cart.status.name = 'not checked out'")
                .list();
        if (items.isEmpty()) {
            notifications.create("Cart is empty")
                    .withType(Notifications.Type.WARNING)
                    .show();
            return;
        }
        dialog.createInputDialog(this)
                .withHeader("Check Out")
                .withParameters(
                        InputParameter.enumParameter("paymentType", PaymentType.class)
                                .withLabel("Payment Method")
                                .withRequired(true)
                )
                .withActions(
                        InputDialogAction.action("proceed")
                                .withText("Proceed")
                                .withHandler(actionEvent -> {
                                            InputDialogAction inputDialogAction = ((InputDialogAction) actionEvent.getSource());
                                            InputDialog inputDialog = inputDialogAction.getInputDialog();

                                            PaymentType paymentType = inputDialog.getValue("paymentType");
                                            cartService.processCheckOut(cart, paymentType);

                                            notifications.create("successfully checked out").withPosition(Notification.Position.MIDDLE).show();

                                            viewNavigationSupport.navigate("CustomerOrder_.list");

                                            inputDialog.closeWithDefaultAction();
                                        }
                                ),
                        InputDialogAction.action("cancel").withText("Cancel")
                                .withHandler(actionEvent -> {
                                    InputDialogAction inputDialogAction = ((InputDialogAction) actionEvent.getSource());
                                    InputDialog inputDialog = inputDialogAction.getInputDialog();
                                    inputDialog.closeWithDefaultAction();
                                })
                )
                .open();
    }
}