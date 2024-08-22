package org.skyhigh.msauthmrmadapter.repository;

import jakarta.validation.constraints.NotNull;
import org.skyhigh.msauthmrmadapter.model.entity.OperationPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface OperationPermissionRepo extends JpaRepository<OperationPermission, UUID> {
    @Query(value =
                "SELECT DISTINCT op.id, op.permission_name, op.operation_endpoint, op.is_critical " +
                "FROM operation_permissions op " +
                "JOIN user_permission up ON op.id = up.permission_id " +
                "JOIN roles_operations ro ON ro.permission_id = op.id " +
                "JOIN user_group_roles ugr ON ugr.id = ro.role_id " +
                "JOIN users_roles ur ON ugr.id = ur.role_id " +
                "JOIN universal_user uu ON up.user_id = uu.id OR uu.id = ur.user_id " +
                "WHERE uu.login = ?1",
            nativeQuery = true
    )
    List<OperationPermission> findAllUserPermissionsByLogin(@NotNull String login);

    @Query(value =
            "SELECT DISTINCT op.id, op.permission_name, op.operation_endpoint, op.is_critical " +
                    "FROM operation_permissions op " +
                    "JOIN user_permission up ON op.id = up.permission_id " +
                    "JOIN universal_user uu ON up.user_id = uu.id " +
                    "WHERE uu.login = ?1",
            nativeQuery = true
    )
    List<OperationPermission> findForceAssignedPermissionsByLogin(@NotNull String login);

    @Query(value =
            "SELECT DISTINCT op.id, op.permission_name, op.operation_endpoint, op.is_critical " +
            "FROM operation_permissions op " +
            "JOIN roles_operations ro ON ro.permission_id = op.id " +
            "WHERE ro.role_id = ?1",
            nativeQuery = true
    )
    List<OperationPermission> findRoleBasedPermissions(@NotNull UUID roleId);
}
