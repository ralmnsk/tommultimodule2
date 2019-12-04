package com.github.ralmnsk.dao.connection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class JpaConfigTest {
    @Autowired
    private Environment env;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactory;
    @Autowired
    private JpaTransactionManager transactionManager;
    @Autowired
    private PropertySourcesPlaceholderConfigurer properties;
    @Autowired
    private TransactionTemplate transactionTemplate;
    @Autowired
    private Properties additionalProperties;


    @Test
    void dataSource() {
        assertNotNull(dataSource);
    }

    @Test
    void entityManagerFactory() {
        assertNotNull(entityManagerFactory);
    }

    @Test
    void transactionManager() {
        assertNotNull(transactionManager);
    }

    @Test
    void additionalProperties() {
        assertNotNull(additionalProperties);
    }

    @Test
    void properties() {
        assertNotNull(properties);
    }

    @Test
    void transactionTemplate() {
        assertNotNull(transactionManager);
    }
}