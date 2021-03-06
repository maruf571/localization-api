package com.maruf.localization.security.repository;

import com.maruf.localization.security.entity.Role;
import com.maruf.localization.security.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, String> {

    @Query(value =
            "SELECT r FROM Role r " +
                    " WHERE " +
                    " r.name=?1")
    Role findRoleByName(RoleName roleName);


    @Query(value =
            "SELECT r FROM Role r " +
                    " WHERE " +
                    " r.name=?1")
    Set<Role> findRolesByName(RoleName roleName);
}
