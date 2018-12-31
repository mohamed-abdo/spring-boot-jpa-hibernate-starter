package com.softideas.functors;


import com.softideas.domain.User;
import com.softideas.entities.Role;
import org.springframework.data.repository.NoRepositoryBean;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.function.Function;

@NoRepositoryBean
public interface Mapper {
    default <T, R> R map(final T source, Class<? extends R> destType) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        R obj = destType.getDeclaredConstructor().newInstance();
        return Objects.requireNonNull(obj);
    }

    Function<com.softideas.entities.User, User> userMapperToDomain = (user) -> {
        Objects.nonNull(user);
        User userDomain = new User();
        userDomain.setId(user.getId());
        userDomain.setName(user.getName());
        userDomain.setRole(user.getRole().getName());
        return userDomain;
    };

    default com.softideas.entities.User userMapperToEntity(User user) {
        Objects.nonNull(user);
        com.softideas.entities.User userEntity = new com.softideas.entities.User();
        userEntity.setId(user.getId());
        userEntity.setName(user.getName());
        userEntity.setRole(roleMapperToEntity(user.getRole()));
        return userEntity;
    }

    default Role roleMapperToEntity(String roleName) {
        Objects.nonNull(roleName);
        String code;
        switch (roleName.toLowerCase()) {
            case "admin":
                code = "ADMIN";
                break;
            default:
                code = "DEFAULT";
                break;
        }
        Role role = new Role();
        role.setCode(code);
        role.setName(roleName);
        return role;
    }

}
