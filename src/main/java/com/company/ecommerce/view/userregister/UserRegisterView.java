package com.company.ecommerce.view.userregister;


import com.company.ecommerce.entity.User;
import com.company.ecommerce.service.RegistrationService;
import com.company.ecommerce.view.login.LoginView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import io.jmix.core.Metadata;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.ViewNavigators;
import io.jmix.flowui.component.checkbox.JmixCheckbox;
import io.jmix.flowui.component.textfield.JmixPasswordField;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.view.*;
import io.jmix.securityflowui.password.PasswordValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Route(value = "user-register-view")
@ViewController(id = "UserRegisterView")
@ViewDescriptor(path = "user-register-view.xml")
@AnonymousAllowed
public class UserRegisterView extends StandardView {
    @ViewComponent
    private TypedTextField<String> firstNameField;
    @ViewComponent
    private TypedTextField<String> lastNameField;
    @ViewComponent
    private TypedTextField<String> usernameField;
    @ViewComponent
    private TypedTextField<String> emailField;
    @ViewComponent
    private JmixPasswordField passwordField;
    @ViewComponent
    private JmixCheckbox vendorCheckBox;

    @Autowired
    private PasswordValidation passwordValidation;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ViewNavigators viewNavigators;
    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private Metadata metadata;
    @Autowired
    private Notifications notifications;

    @Subscribe("registerBtn")
    public void onRegisterBtnClick(final ClickEvent<Button> event) {
        Boolean isVendor = vendorCheckBox.getValue();
        User user = metadata.create(User.class);
        user.setUsername(usernameField.getValue());
        user.setPassword(passwordEncoder.encode(passwordField.getValue()));
        user.setFirstName(firstNameField.getValue());
        user.setLastName(lastNameField.getValue());
        user.setEmail(emailField.getValue());

        List<String> errors = passwordValidation.validate(user, passwordField.getValue());
        if (!errors.isEmpty()) {
            notifications.create(String.join("\n", errors))
                    .withPosition(Notification.Position.BOTTOM_END)
                    .show();
        } else {
                if(Boolean.TRUE.equals(isVendor)){
                    registrationService.registerVendor(user);
                } else {
                    registrationService.registerCustomer(user);
                }
            navigateToLoginView();
        }
    }

    @Subscribe("alreadyRegisteredBtn")
    public void onAlreadyRegisteredBtnClick(final ClickEvent<Button> event) {
        navigateToLoginView();
    }

    private void navigateToLoginView() {
        viewNavigators.view(this, LoginView.class).navigate();
    }
}