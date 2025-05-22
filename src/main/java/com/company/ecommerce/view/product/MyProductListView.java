package com.company.ecommerce.view.product;

import com.company.ecommerce.entity.Product;
import com.company.ecommerce.entity.User;
import com.company.ecommerce.repository.ProductRepository;
import com.company.ecommerce.view.main.MainView;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;


@Route(value = "my_products", layout = MainView.class)
@ViewController(id = "MyProduct.list")
@ViewDescriptor(path = "my-product-list-view.xml")
@LookupComponent("productsDataGrid")
@DialogMode(width = "64em")
public class MyProductListView extends StandardListView<Product> {
}