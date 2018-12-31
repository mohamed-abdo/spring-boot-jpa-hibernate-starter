package com.softideas.auditorTest;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.persistence.SharedCacheMode;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class PersistenceContext {
    private final String[] entityPackages = {"com.softideas.entities"};

    @Value("${hibernate.dialect}")
    private String hibernate_dialect;
    @Value("${hibernate.format_sql}")
    private String hibernate_format_sql;
    @Value("${hibernate.hbm2ddl.auto}")
    private String hibernate_hbm2ddl_auto;
    @Value("${hibernate.ejb.naming_strategy}")
    private String hibernate_ejb_naming_strategy;
    @Value("${hibernate.show_sql}")
    private String hibernate_show_sql;

    @Bean
    public Properties jpaProperties() {
        return new Properties() {
            {
                put("hibernate.dialect", hibernate_dialect);
            }

            {
                put("hibernate.format_sql", hibernate_format_sql);
            }

            {
                put("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
            }

            {
                put("hibernate.ejb.naming_strategy", hibernate_ejb_naming_strategy);
            }

            {
                put("hibernate.show_sql", hibernate_show_sql);
            }

        };
    }


    @Bean
    @Primary
    @Qualifier("H2")
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder() {
            {
                setType(EmbeddedDatabaseType.H2);
            }

            {
                addScript("classpath:schema.sql");
            }

            {
                addScript("classpath:setup.sql");
            }
        }.build();
    }

    @Profile("test")
    @Bean
    public DataSource primaryDataSource() {
        return new EmbeddedDatabaseBuilder() {
            {
                setType(EmbeddedDatabaseType.H2);
            }
        }.build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("H2") DataSource dataSource, Properties jpaProperties) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setPackagesToScan(entityPackages);
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setPersistenceUnitName("auditor_domain");
        em.setSharedCacheMode(SharedCacheMode.ALL);
        em.setJpaDialect(new HibernateJpaDialect());
        em.setJpaProperties(jpaProperties);
        em.setDataSource(dataSource);
        return em;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager() {
            {
                setEntityManagerFactory(entityManagerFactory);
            }
        };
    }

}
