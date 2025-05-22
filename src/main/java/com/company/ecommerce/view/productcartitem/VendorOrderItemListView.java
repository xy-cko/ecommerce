package com.company.ecommerce.view.productcartitem;

import com.company.ecommerce.entity.ProductCartItem;
import com.company.ecommerce.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "endor-order-items", layout = MainView.class)
@ViewController(id = "VendorOrderItem.list")
@ViewDescriptor(path = "Vendor-order-item-list-view.xml")
@LookupComponent("productCartItemsDataGrid")
@DialogMode(width = "64em")
public class VendorOrderItemListView extends StandardListView<ProductCartItem> {
}