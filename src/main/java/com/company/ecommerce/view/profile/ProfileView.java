package com.company.ecommerce.view.profile;


import com.company.ecommerce.entity.User;
import com.company.ecommerce.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route(value = "profile-view", layout = MainView.class)
@ViewController(id = "ProfileView")
@ViewDescriptor(path = "profile-view.xml")
public class ProfileView extends StandardView {
    @Autowired
    private DataManager dataManager;
    @ViewComponent
    private CollectionLoader<User> profileDl;
    @ViewComponent
    private CollectionContainer<User> profileDc;
    @ViewComponent
    private InstanceContainer<User> userDc;
    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        profileDl.load();
        List<User> users = profileDc.getItems();
        if(!users.isEmpty()) {
            userDc.setItem(users.getFirst());
        }
    }
}