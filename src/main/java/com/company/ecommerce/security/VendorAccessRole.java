package com.company.ecommerce.security;

import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.*;
import io.jmix.securityflowui.role.annotation.MenuPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;

@ResourceRole(name = "VendorAccessRole", code = VendorAccessRole.CODE)
public interface VendorAccessRole {
    String CODE = "vendor-access-role";
    @ViewPolicy(viewIds = {"LoginView", "MainView", "AllProduct.list", "Product.detail", "MyProduct.list", "Product.show",})
    @ViewPolicy(viewIds = "UserRegisterView")

    @MenuPolicy(menuIds = {"AllProduct.list", "MyProduct.list"})

    @EntityPolicy(entityName = "Order", actions = {EntityPolicyAction.READ, EntityPolicyAction.UPDATE})
    @EntityAttributePolicy(entityName = "Order", attributes = "status", action = EntityAttributePolicyAction.MODIFY)

    @EntityPolicy(entityName = "Product", actions = {EntityPolicyAction.ALL})
    @EntityAttributePolicy(entityName = "Product", attributes = "*", action = EntityAttributePolicyAction.MODIFY)

    @EntityPolicy(entityName = "Product", actions = {EntityPolicyAction.ALL})
    @EntityAttributePolicy(entityName = "Product", attributes = "*", action = EntityAttributePolicyAction.MODIFY)

    @EntityPolicy(entityName = "Order", actions = EntityPolicyAction.DELETE)
    @EntityAttributePolicy(entityName = "Order", attributes = "*", action = EntityAttributePolicyAction.VIEW)

    @EntityPolicy(entityName = "Status", actions = EntityPolicyAction.READ)
    @EntityAttributePolicy(entityName = "Status", attributes = "name", action = EntityAttributePolicyAction.VIEW)

    @EntityPolicy(entityName = "User", actions = EntityPolicyAction.READ)
    @SpecificPolicy(resources = "ui.loginToUi")

    void vendorAccess();
}