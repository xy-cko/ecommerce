package com.company.ecommerce.view.product;

import com.company.ecommerce.entity.Product;
import com.company.ecommerce.entity.User;
import com.company.ecommerce.view.main.MainView;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.core.FileRef;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.upload.FileStorageUploadField;
import io.jmix.flowui.component.upload.receiver.FileTemporaryStorageBuffer;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.kit.component.upload.event.FileUploadFailedEvent;
import io.jmix.flowui.kit.component.upload.event.FileUploadSucceededEvent;
import io.jmix.flowui.upload.TemporaryStorage;
import io.jmix.flowui.view.*;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.UUID;

@Route(value = "products/:id", layout = MainView.class)
@ViewController(id = "Product.detail")
@ViewDescriptor(path = "product-detail-view.xml")
@EditedEntityContainer("productDc")
public class ProductDetailView extends StandardDetailView<Product> {
    @Autowired
    private Notifications notifications;
    @Autowired
    private CurrentAuthentication currentAuthentication;
    @Autowired
    private TemporaryStorage temporaryStorage;
    @Autowired
    private DataManager dataManager;

    @Subscribe("imageField")
    public void onFileFieldFileUploadSucceeded(final FileUploadSucceededEvent<FileStorageUploadField> event) {
        FileTemporaryStorageBuffer buffer = (FileTemporaryStorageBuffer) event.getReceiver();
        UUID fileId = buffer.getFileData().getFileInfo().getId();

        File file = temporaryStorage.getFile(fileId);
        if (file != null) {
            FileRef fileRef = temporaryStorage.putFileIntoStorage(fileId, event.getFileName());
            getEditedEntity().setImage(fileRef);
            notifications.create("Image uploaded successfully")
                    .withPosition(Notification.Position.MIDDLE)
                    .show();
        }
    }

    @Subscribe("imageField")
    public void onFileFieldFileUploadFailed(final FileUploadFailedEvent<FileStorageUploadField> event) {
        notifications.create("Upload failed: " + event.getReason())
                .withType(Notifications.Type.ERROR)
                .show();
    }

    @Subscribe("saveAction")
    public void onSaveAction(final ActionPerformedEvent event) {
        Product product = getEditedEntity();
        if(product != null) {
            User currentUser = (User) currentAuthentication.getUser();
            product.setVendor(currentUser);
            dataManager.save(product);
        }

        if (product.getImage() == null) {
            notifications.create("Please upload a product image")
                    .withType(Notifications.Type.DEFAULT)
                    .show();
            return;
        }
        closeWithSave();

    }
}