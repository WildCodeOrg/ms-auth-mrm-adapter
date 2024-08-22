package org.skyhigh.msauthmrmadapter.service;

import jakarta.validation.constraints.NotNull;
import org.skyhigh.msauthmrmadapter.model.dto.AuthorityDTO;
import org.skyhigh.msauthmrmadapter.model.entity.OperationPermission;
import org.skyhigh.msauthmrmadapter.model.entity.UserGroupRole;
import org.skyhigh.msauthmrmadapter.repository.OperationPermissionRepo;
import org.skyhigh.msauthmrmadapter.repository.UserGroupRoleRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    private static final Logger log = Logger.getLogger(AuthorityServiceImpl.class.getName());

    private final OperationPermissionRepo operationPermissionRepo;
    private final UserGroupRoleRepo userGroupRoleRepo;

    public AuthorityServiceImpl(OperationPermissionRepo operationPermissionRepo, UserGroupRoleRepo userGroupRoleRepo) {
        this.operationPermissionRepo = operationPermissionRepo;
        this.userGroupRoleRepo = userGroupRoleRepo;
    }

    @Override
    public AuthorityDTO getAuthorities(@NotNull String userLogin) {
        List<String> authoritiesWithRoleBasedPermissions = new ArrayList<>();
        List<UserGroupRole> roles = userGroupRoleRepo.findUserRoleByLogin(userLogin);
        for (UserGroupRole role : roles) {
            List<OperationPermission> roleBasedPermissions
                    = operationPermissionRepo.findRoleBasedPermissions(role.getId());
            authoritiesWithRoleBasedPermissions.addAll(
                    roleBasedPermissions.stream()
                            .map(x -> role.getRoleName() + "::" + x.getOperationEndpoint())
                            .toList()
            );
        }

        List<String> authorities = new ArrayList<>(
                !authoritiesWithRoleBasedPermissions.isEmpty()
                        ? authoritiesWithRoleBasedPermissions
                        : roles.stream().map(x -> x.getRoleName() + "::").toList());

        List<OperationPermission> forceAssignedPermissions = operationPermissionRepo.findForceAssignedPermissionsByLogin(userLogin);
        authorities.addAll(forceAssignedPermissions.stream().map(x -> "::" + x.getOperationEndpoint()).toList());

        return new AuthorityDTO(authorities);
    }
}
