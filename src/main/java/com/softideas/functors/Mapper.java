package com.softideas.functors;


import com.softideas.domain.User;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.function.Function;

public interface Mapper {
    default <T, R> R map(final T source, Class<? extends R> destType) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        R obj = destType.getDeclaredConstructor().newInstance();
        return Objects.requireNonNull(obj);
    }

    Function<com.softideas.auditor.entity.User, User> userMapper = (user) -> {
        Objects.nonNull(user);
        return new User() {
            {
                setId(user.getId());
            }

            {
                setName(user.getName());
            }

            {
                setRole(user.getRole().getName());
            }
        };
    };

}
