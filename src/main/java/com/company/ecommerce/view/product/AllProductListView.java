package com.company.ecommerce.view.product;

import com.company.ecommerce.entity.Product;
import com.company.ecommerce.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.grid.ItemClickEvent;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteParam;
import com.vaadin.flow.router.RouteParameters;
import io.jmix.flowui.ViewNavigators;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;


@Route(value = "allproducts", layout = MainView.class)
@ViewController(id = "AllProduct.list")
@ViewDescriptor(path = "all-product-list-view.xml")
@LookupComponent("productsList")
@DialogMode(width = "64em")
public class AllProductListView extends StandardListView<Product> {
    @Autowired
    private ViewNavigators viewNavigators;
    @Subscribe("productsDataGrid")
    public void onProductsDataGridItemClick(final ItemClickEvent<Product> event) {
        Product product = event.getItem();
        if (product != null) {
            viewNavigators.detailView(Product.class)
                    .withViewClass(ProductShowView.class)
                    .withRouteParameters(new RouteParameters(
                            new RouteParam("id", product.getId().toString())
                    ))
                    .navigate();
        }
    }
}