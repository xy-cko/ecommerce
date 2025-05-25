package com.company.ecommerce.view.product;

import com.company.ecommerce.entity.Acceptence;
import com.company.ecommerce.entity.Product;
import com.company.ecommerce.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;


@Route(value = "my_products", layout = MainView.class)
@ViewController(id = "MyProduct.list")
@ViewDescriptor(path = "my-product-list-view.xml")
@LookupComponent("productsDataGrid")
@DialogMode(width = "64em")
public class MyProductListView extends StandardListView<Product> {
    @ViewComponent
    private DataGrid<Product> productsDataGrid;

    @Autowired
    private DataManager dataManager;

    @Autowired
    private Notifications notifications;

    @Subscribe(id = "editButton", subject = "clickListener")
    public void onEditButtonClick(final ClickEvent<JmixButton> event) {
        Product editedItem = productsDataGrid.getSingleSelectedItem();
        if (editedItem != null) {
            editedItem.setIsAccepted(Acceptence.WAITING_FOR_APPROVAL);
            dataManager.save(editedItem);
        }

    }

}