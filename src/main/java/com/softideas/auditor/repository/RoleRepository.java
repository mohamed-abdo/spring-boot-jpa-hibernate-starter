package com.softideas.auditor.repository;

import com.softideas.entities.Role;
import com.softideas.entities.RoleCode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role,String> {
    Optional<Role> findByCode(RoleCode role);
}
