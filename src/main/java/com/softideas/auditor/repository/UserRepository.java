package com.softideas.auditor.repository;

import com.softideas.entities.RoleCode;
import com.softideas.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
    Collection<User> findByName(String name);

    @Query("select u from User u where u.role.code = :role")
    Collection<User> findByRoleCode(@Param("role") RoleCode roleCode);

    Integer deleteByNameIgnoreCase(String username);
}
