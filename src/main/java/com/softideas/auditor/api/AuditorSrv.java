package com.softideas.auditor.api;

import com.softideas.domain.User;

import java.util.Collection;

public interface AuditorSrv {
    Collection<User> getUsers();
}
