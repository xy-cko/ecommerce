package com.company.ecommerce.view.status;

import com.company.ecommerce.entity.Status;
import com.company.ecommerce.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "statuses", layout = MainView.class)
@ViewController(id = "Status.list")
@ViewDescriptor(path = "status-list-view.xml")
@LookupComponent("statusesDataGrid")
@DialogMode(width = "64em")
public class StatusListView extends StandardListView<Status> {
}