package com.company.ecommerce.security;

import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.security.role.annotation.SpecificPolicy;
import io.jmix.securityflowui.role.annotation.MenuPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;

@ResourceRole(name = "AdminAccessRole", code = AdminAccessRole.CODE)
public interface AdminAccessRole {
    String CODE = "admin-access-role";
    @ViewPolicy(viewIds = "*")
    @MenuPolicy(menuIds = "*")
    @SpecificPolicy(resources = "*")
    @EntityPolicy(entityName = "Category", actions = {EntityPolicyAction.ALL})
    @EntityPolicy(entityName = "Status", actions = {EntityPolicyAction.ALL})
    @EntityPolicy(entityName = "User", actions = {EntityPolicyAction.ALL})
    @EntityPolicy(entityName = "Product", actions = {EntityPolicyAction.READ})
    void adminAccess();
}