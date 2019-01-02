package com.softideas.auditor.api;

import com.softideas.domain.Role;
import com.softideas.domain.User;
import com.softideas.entities.RoleCode;

import java.util.Collection;

public interface AuditorSrv {
    Collection<User> getUsers();

    String addUser(User user);

    String addRole(Role role);

    Collection<User> findUserByName(String name);

    User updateUser(User user);

    Collection<User> getUsersByRoleCode(RoleCode roleCode);

    boolean deleteUser(String username);
}
