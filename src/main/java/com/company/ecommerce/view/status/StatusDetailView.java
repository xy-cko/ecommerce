package com.company.ecommerce.view.status;

import com.company.ecommerce.entity.Status;
import com.company.ecommerce.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "statuses/:id", layout = MainView.class)
@ViewController(id = "Status.detail")
@ViewDescriptor(path = "status-detail-view.xml")
@EditedEntityContainer("statusDc")
public class StatusDetailView extends StandardDetailView<Status> {
}