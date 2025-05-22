package com.company.ecommerce.security;

import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.security.role.annotation.SpecificPolicy;
import io.jmix.securityflowui.role.annotation.MenuPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;

@ResourceRole(name = "CustomerAccessRole", code = CustomerAccessRole.CODE)
public interface CustomerAccessRole {
    String CODE = "customer-access-role";
    @ViewPolicy(viewIds = {"LoginView", "MainView", "AllProduct.list", "Product.detail", "Product.show", "MyCartItem.list", "inputDialog", "CustomerOrder_.list"})

    @MenuPolicy(menuIds = {"AllProduct.list", "MyCartItem.list", "CustomerOrder_.list"})

    @EntityPolicy(entityName = "Cart", actions = {EntityPolicyAction.READ, EntityPolicyAction.CREATE, EntityPolicyAction.UPDATE})
    @EntityAttributePolicy(entityName = "Cart", attributes = {"status", "order"}, action = EntityAttributePolicyAction.MODIFY)

    @EntityPolicy(entityName = "Product", actions = {EntityPolicyAction.READ, EntityPolicyAction.UPDATE})
    @EntityAttributePolicy(entityName = "Product", attributes = {"stock", "unitsSold", "status"}, action = EntityAttributePolicyAction.MODIFY)
    @EntityAttributePolicy(entityName = "Product", attributes = "*", action = EntityAttributePolicyAction.VIEW)

    @EntityPolicy(entityName = "ProductCartItem", actions = EntityPolicyAction.ALL)
    @EntityAttributePolicy(entityName = "ProductCartItem", attributes = "*", action = EntityAttributePolicyAction.MODIFY)

    @EntityPolicy(entityName = "Order", actions = EntityPolicyAction.ALL)
    @EntityAttributePolicy(entityName = "Order", attributes = "*", action = EntityAttributePolicyAction.MODIFY)

    @EntityPolicy(entityName = "Status", actions = {EntityPolicyAction.READ})
    @EntityAttributePolicy(entityName = "Status", attributes = "name", action = EntityAttributePolicyAction.VIEW)

    @EntityPolicy(entityName = "Order_", actions = {EntityPolicyAction.ALL})
    @EntityAttributePolicy(entityName = "Order_", attributes = {"status", "cart", "total", "date"}, action = EntityAttributePolicyAction.MODIFY)
    @EntityAttributePolicy(entityName = "Order_", attributes = "*", action = EntityAttributePolicyAction.VIEW)

    @EntityPolicy(entityName = "User", actions = EntityPolicyAction.READ)


    @SpecificPolicy(resources = "ui.loginToUi")
    void customerAccess();
}