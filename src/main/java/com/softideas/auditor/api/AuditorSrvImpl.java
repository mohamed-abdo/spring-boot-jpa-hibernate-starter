package com.softideas.auditor.api;

import com.softideas.auditor.repository.RoleRepository;
import com.softideas.auditor.repository.UserRepository;
import com.softideas.domain.User;
import com.softideas.entities.Role;
import com.softideas.functors.Mapper;
import org.hibernate.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
@EnableJpaRepositories(basePackages = {"com.softideas.auditor.repository"})
public class AuditorSrvImpl implements AuditorSrv, Mapper {

    private final Logger logger = LoggerFactory.getLogger(AuditorSrvImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional(readOnly = true)
    @Override
    public List<User> getUsers() {
        logger.debug("get-users started.");
        return StreamSupport
                .stream(userRepository.findAll().spliterator(), false)
                .map(userMapperToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public String addUser(User user) {
        logger.debug("add user started:", user);
        com.softideas.entities.User userEntity = userMapperToEntity(user);
        String roleCode = "ADMIN";
        logger.debug("finding role by code :{}", roleCode);
        Role role = roleRepository.findById(roleCode).get();
        logger.debug("role:{}", role);
        userEntity.setRole(role);
        logger.debug("convert domain into entity ", userEntity);
        com.softideas.entities.User result = userRepository.save(userEntity);
        logger.debug("add user done, userId:{}", result);
        return result.getId().toString();
    }

    @Override
    public String addRole(com.softideas.domain.Role role) {
        Role roleEntity = roleMapperToEntity(role.getCode());
        roleEntity.setCode(role.getCode());
        roleEntity.setName(role.getName());
        roleEntity.setDescription(role.getDescription());
        logger.debug("Starting adding role entity: {}", roleEntity);
        Role result = roleRepository.save(roleEntity);
        logger.debug("Finished adding role: {}", result);
        return result.getCode();
    }

    @Override
    public Collection<User> findUserByName(String name) {
        logger.debug("find user by name:{}", name);
        Collection<User> users = userRepository.findByName(name)
                .stream()
                .map(userMapperToDomain)
                .collect(Collectors.toList());
        logger.debug("find reaul: {}", users);
        return users;
    }

    @Override
    public User updateUser(User user) {
        logger.debug("start updating user: {}", user);
        com.softideas.entities.User userToUpdate = userRepository.findById(user.getId()).orElseThrow(() -> new ObjectNotFoundException(user.getId(), "user not found by Id."));
        userToUpdate.setName(user.getName());
        userToUpdate.setRole(roleMapperToEntity(user.getRole()));
        com.softideas.entities.User result = userRepository.save(userToUpdate);
        logger.debug("finished updating user entity: {}", result);
        return userMapperToDomain.apply(result);
    }

    @Override
    public Collection<User> getUsersByRoleCode(String roleCode) {
        logger.debug("start finding users by role code: {}", roleCode);
        Collection<User> result = userRepository.findByRoleCode(roleCode)
                .stream()
                .map(userMapperToDomain)
                .collect(Collectors.toList());
        logger.debug("finished finding users: {}", result);
        return result;
    }

    @Override
    public boolean deleteUser(String username) {
        logger.debug("start deleting user: {}", username);
        Integer result = userRepository.deleteByNameIgnoreCase(username);
        logger.debug("finished deleting user result: {}", result);
        return result > 0;
    }
}
