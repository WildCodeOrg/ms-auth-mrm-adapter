package org.skyhigh.msauthmrmadapter.repository;

import org.skyhigh.msauthmrmadapter.model.entity.UserGroupRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface UserGroupRoleRepo extends JpaRepository<UserGroupRole, UUID> {
    @Query(value =
            "SELECT ugr.id, ugr.role_name, ugr.description, ugr.is_critical " +
            "FROM user_group_roles ugr " +
            "JOIN users_roles ur ON ugr.id = ur.role_id " +
            "JOIN universal_user uu ON ur.user_id = uu.id " +
            "WHERE uu.login = ?1",
            nativeQuery = true
    )
    List<UserGroupRole> findUserRoleByLogin(String login);
}
