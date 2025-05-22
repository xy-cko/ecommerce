package com.company.ecommerce.security;

import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.*;
import io.jmix.securityflowui.role.annotation.MenuPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;

@ResourceRole(name = "VendorAccessRole", code = VendorAccessRole.CODE)
public interface VendorAccessRole {
    String CODE = "vendor-access-role";
    @ViewPolicy(viewIds = {"ProfileView","VendorOrderItem.list","Category.list","LoginView", "MainView", "AllProduct.list", "Product.detail", "MyProduct.list", "Product.show",})
    @ViewPolicy(viewIds = "UserRegisterView")

    @MenuPolicy(menuIds = {"ProfileView","AllProduct.list", "MyProduct.list", "VendorOrderItem.list"})

    @EntityPolicy(entityName = "ProductCartItem", actions = EntityPolicyAction.READ)
    @EntityAttributePolicy(entityName = "ProductCartItem", attributes = "*", action = EntityAttributePolicyAction.VIEW)

    @EntityPolicy(entityName = "Order", actions = {EntityPolicyAction.READ, EntityPolicyAction.UPDATE})
    @EntityAttributePolicy(entityName = "Order", attributes = "status", action = EntityAttributePolicyAction.MODIFY)

    @EntityPolicy(entityName = "Product", actions = {EntityPolicyAction.ALL})
    @EntityAttributePolicy(entityName = "Product", attributes = "*", action = EntityAttributePolicyAction.MODIFY)

    @EntityPolicy(entityName = "Order", actions = EntityPolicyAction.DELETE)
    @EntityAttributePolicy(entityName = "Order", attributes = "*", action = EntityAttributePolicyAction.VIEW)

    @EntityPolicy(entityName = "Status", actions = EntityPolicyAction.READ)
    @EntityAttributePolicy(entityName = "Status", attributes = "name", action = EntityAttributePolicyAction.VIEW)

    @EntityPolicy(entityName = "Category", actions = EntityPolicyAction.READ)
    @EntityAttributePolicy(entityName = "Category", attributes = "*", action = EntityAttributePolicyAction.VIEW)

    @EntityPolicy(entityName = "User", actions = EntityPolicyAction.READ)
    @EntityAttributePolicy(entityName = "User", attributes = "*", action = EntityAttributePolicyAction.VIEW)

    @EntityPolicy(entityName = "Cart", actions = EntityPolicyAction.READ)
    @EntityAttributePolicy(entityName = "Cart", attributes = "*", action = EntityAttributePolicyAction.VIEW)

    @SpecificPolicy(resources = "ui.loginToUi")

    void vendorAccess();
}