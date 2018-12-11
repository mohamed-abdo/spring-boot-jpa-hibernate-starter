package com.softideas.auditorTest;

import com.softideas.auditor.api.AuditorSrv;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBootConfiguration
@EntityScan(basePackages = {"com.softideas.auditor.entity"})
@ComponentScan({"com.softideas.auditor.*", "com.softideas.auditorTest"})
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
        Assert.assertNotNull(users.stream().anyMatch(u -> u.getRole().equalsIgnoreCase("DEFAULT")));
        Assert.assertNotNull(users.stream().anyMatch(u -> u.getRole().equalsIgnoreCase("ADMIN")));
    }
}
