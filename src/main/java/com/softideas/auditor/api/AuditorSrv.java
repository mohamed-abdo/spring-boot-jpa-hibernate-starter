package com.softideas.auditor.api;

import com.softideas.domain.Role;
import com.softideas.domain.User;

import java.util.Collection;

public interface AuditorSrv {
    Collection<User> getUsers();

    String addUser(User user);

    String addRole(Role role);

    Collection<User> findUserByName(String name);

    User updateUser(User user);

    Collection<User> getUsersByRoleCode(String roleCode);

    boolean deleteUser(String username);
}
