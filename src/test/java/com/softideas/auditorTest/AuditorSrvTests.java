package com.softideas.auditorTest;

import com.softideas.auditor.api.AuditorSrv;
import com.softideas.domain.Role;
import com.softideas.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBootConfiguration
@EntityScan(basePackages = {"com.softideas.entities"})
@ComponentScan(basePackages = {"com.softideas.auditor.api", "com.softideas.auditorTest"})
@EnableJpaRepositories(basePackages = {"com.softideas.auditor.repository"})
public class AuditorSrvTests {

    @Autowired
    @Qualifier("AuditorSrvTest")
    private AuditorSrv auditorSrv;

    @Test
    public void testGetAuditors() {
        Collection<User> users = auditorSrv.getUsers();
        Assert.assertNotNull(users);
        //initialized by setup.sql
        Assert.assertEquals(3, users.size());
        Assert.assertTrue(users.stream().anyMatch(u -> u.getRole().equalsIgnoreCase("DEFAULT")));
        Assert.assertTrue(users.stream().anyMatch(u -> u.getRole().equalsIgnoreCase("ADMIN")));
    }

    @Test
    public void testAddAuditor() {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setName("mohamed.abdo");
        user.setRole("admin");
        String userId = auditorSrv.addUser(user);
        Assert.assertNotNull(userId);
    }

    @Test
    public void testAddRole() {
        Role role = new Role() {
            {
                {
                    setCode("DEV");
                }
                {
                    setName("developer");
                }
            }
        };
        String roleCode = auditorSrv.addRole(role);
        Assert.assertNotNull(roleCode);
    }

    @Test
    public void testUpdateUser() {
        Collection<User> users = auditorSrv.findUserByName("mohamed.abdo");
        Assert.assertEquals(1, users.size());
        User user = users.stream().findAny().orElse(null);
        Assert.assertNotNull(user);
        user.setName(user.getName() + " updated name");
        User result = auditorSrv.updateUser(user);
        Assert.assertNotNull(result);
        Assert.assertTrue(result.getName().equalsIgnoreCase(user.getName()));
    }

    @Test
    public void testGetUsersByRole() {
        String roleCode = "ADMIN";
        Collection<User> users = auditorSrv.getUsersByRoleCode(roleCode);
        Assert.assertTrue(users.size() > 0);
        Assert.assertTrue(users.stream().anyMatch(u -> u.getRole().equalsIgnoreCase(roleCode)));
    }

    @Test
    public void testDeleteUser() {
        String username = "mohamed.abdo";
        boolean result = auditorSrv.deleteUser(username);
        Assert.assertTrue(result);
    }
}