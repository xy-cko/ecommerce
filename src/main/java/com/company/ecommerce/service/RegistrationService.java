package com.company.ecommerce.service;

import com.company.ecommerce.entity.User;
import com.company.ecommerce.security.CustomerAccessRole;
import com.company.ecommerce.security.VendorAccessRole;
import io.jmix.core.UnconstrainedDataManager;
import io.jmix.security.role.assignment.RoleAssignmentRoleType;
import io.jmix.securitydata.entity.RoleAssignmentEntity;
import org.springframework.stereotype.Component;

@Component
public class RegistrationService {
    // Use UnconstrainedDataManager to bypass data access check (https://docs.jmix.io/jmix/security/authorization.html#data-access-checks)
    // when saving entities in anonymous session.
    private final UnconstrainedDataManager unconstrainedDataManager;

    public RegistrationService(UnconstrainedDataManager unconstrainedDataManager) {
        this.unconstrainedDataManager = unconstrainedDataManager;
    }

    public void registerCustomer(User user) {
        RoleAssignmentEntity roleAssignment = unconstrainedDataManager.create(RoleAssignmentEntity.class);
        roleAssignment.setUsername(user.getUsername());
        roleAssignment.setRoleCode(CustomerAccessRole.CODE);
        roleAssignment.setRoleType(RoleAssignmentRoleType.RESOURCE);

        unconstrainedDataManager.save(user, roleAssignment);
    }
    public void registerVendor(User user) {

        RoleAssignmentEntity roleAssignment = unconstrainedDataManager.create(RoleAssignmentEntity.class);
        roleAssignment.setUsername(user.getUsername());

        roleAssignment.setRoleCode(VendorAccessRole.CODE);
        roleAssignment.setRoleType(RoleAssignmentRoleType.RESOURCE);



        unconstrainedDataManager.save(user, roleAssignment);
    }
}