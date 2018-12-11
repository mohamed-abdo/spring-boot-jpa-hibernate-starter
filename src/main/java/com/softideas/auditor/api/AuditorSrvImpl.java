package com.softideas.auditor.api;

import com.softideas.auditor.repository.UserRepository;
import com.softideas.domain.User;
import com.softideas.functors.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
@EnableJpaRepositories(basePackages = {"com.softideas.auditor.repository.*"})
public class AuditorSrvImpl implements AuditorSrv, Mapper {

    private final Logger logger = LoggerFactory.getLogger(AuditorSrvImpl.class);

    @Autowired
    private UserRepository userRepository;


    @Override
    public List<User> getUsers() {
        return StreamSupport
                .stream(userRepository.findAll().spliterator(), false)
                .map(userMapper)
                .collect(Collectors.toList());
    }
}
