package com.github.ralmnsk.dao.connection;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.context.annotation.*;

        import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
        import org.springframework.core.env.Environment;

        import org.springframework.core.io.ClassPathResource;
        import org.springframework.core.io.Resource;
        import org.springframework.jdbc.datasource.DriverManagerDataSource;
        import org.springframework.orm.jpa.JpaTransactionManager;
        import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
        import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
        import org.springframework.transaction.annotation.EnableTransactionManagement;
        import org.springframework.beans.factory.annotation.Value;
        import javax.persistence.EntityManagerFactory;
        import javax.sql.DataSource;
        import java.util.Properties;

@Configuration
@ComponentScan({"com.github.ralmnsk.service","com.github.ralmnsk.dao",
        "com.github.ralmnsk.model", "com.github.ralmnsk.demo",
        "com.github.ralmnsk.service.user"})
//@EnableJpaRepositories(basePackages = "com.github.ralmnsk.dao")
//@PropertySource(value={"classpath:application.properties"})
@EnableTransactionManagement

public class JpaConfig {
//    @Value("${spring.datasource.url}")
//    private String db;
//    @Value("${spring.datasource.password}")
//    private String pass;
//    @Value("${spring.datasource.driver-class-name}")
//    private String driver;
//    @Value("${spring.datasource.username}")
//    private String name;
//    @Value("${spring.jpa.show-sql}")
//    private String show;
//    @Value("${spring.jpa.hibernate.ddl-auto}")
//    private String ddl;
//    @Value("${spring.jpa.properties.hibernate.dialect}")
//    private String dialect;

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));//("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));

//        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://remotemysql.com:3306/eYcooOkOuh?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Moscow");
//        dataSource.setUsername("eYcooOkOuh");
//        dataSource.setPassword("OPDoegkjHh");

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(false);
        vendorAdapter.setDatabasePlatform(env.getProperty("spring.datasource.password"));
         LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
        em.setPackagesToScan(new String[] {"com.github.ralmnsk.model" });
        em.setDataSource(dataSource());
        em.afterPropertiesSet();
//        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return em;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setDataSource(dataSource());
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

     public Properties additionalProperties() {
        Properties hibernateProperties = new Properties();

        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        hibernateProperties.setProperty("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
        hibernateProperties.setProperty("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));

        return hibernateProperties;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties(){
        PropertySourcesPlaceholderConfigurer pspc =
                new PropertySourcesPlaceholderConfigurer();
        Resource[] resources = new ClassPathResource[ ]
                { new ClassPathResource( "application.properties" ) };
        pspc.setLocations( resources );
        pspc.setIgnoreUnresolvablePlaceholders( true );
        return pspc;
    }

}


