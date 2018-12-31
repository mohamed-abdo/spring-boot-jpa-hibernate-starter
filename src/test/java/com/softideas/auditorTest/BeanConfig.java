package com.softideas.auditorTest;

import com.softideas.auditor.api.AuditorSrv;
import com.softideas.auditor.api.AuditorSrvImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    @Qualifier("AuditorSrvTest")
    public AuditorSrv auditor() {
        return new AuditorSrvImpl();
    }

}
