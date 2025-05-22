package com.company.ecommerce.view.main;

import com.company.ecommerce.entity.Product;
import com.company.ecommerce.view.product.ProductShowView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteParam;
import com.vaadin.flow.router.RouteParameters;
import io.jmix.flowui.app.main.StandardMainView;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.ViewComponent;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import io.jmix.flowui.view.navigation.ViewNavigationSupport;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route("")
@ViewController(id = "MainView")
@ViewDescriptor(path = "main-view.xml")
public class MainView extends StandardMainView {
    @ViewComponent
    private CollectionLoader<Product> bestProductsDl;

    @ViewComponent
    private CollectionContainer<Product> bestProductsDc;

    @ViewComponent
    private InstanceContainer<Product> product1Dc;
    @ViewComponent
    private InstanceContainer<Product> product2Dc;
    @ViewComponent
    private InstanceContainer<Product> product3Dc;

    @Autowired
    private ViewNavigationSupport viewNavigationSupport;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        bestProductsDl.load();

        List<Product> bestProducts = bestProductsDc.getItems();

        if (bestProducts.size() > 0) product1Dc.setItem(bestProducts.get(0));
        if (bestProducts.size() > 1) product2Dc.setItem(bestProducts.get(1));
        if (bestProducts.size() > 2) product3Dc.setItem(bestProducts.get(2));
    }

    @Subscribe(id = "viewFirst", subject = "clickListener")
    public void onViewFirstClick(final ClickEvent<JmixButton> event) {
        Product product = product1Dc.getItem();
        viewNavigationSupport.navigate(ProductShowView.class, new RouteParameters(new RouteParam("id", product.getId().toString())));
    }

    @Subscribe(id = "viewSecond", subject = "clickListener")
    public void onViewSecondClick(final ClickEvent<JmixButton> event) {
        Product product = product2Dc.getItem();
        viewNavigationSupport.navigate(ProductShowView.class, new RouteParameters(new RouteParam("id", product.getId().toString())));
    }

    @Subscribe(id = "viewThird", subject = "clickListener")
    public void onViewThirdClick(final ClickEvent<JmixButton> event) {
        Product product = product3Dc.getItem();
        viewNavigationSupport.navigate(ProductShowView.class, new RouteParameters(new RouteParam("id", product.getId().toString())));
    }

}
