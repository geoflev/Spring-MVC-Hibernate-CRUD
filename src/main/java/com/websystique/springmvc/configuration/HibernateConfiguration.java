package com.websystique.springmvc.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
//metaferw diladi to sessionFactory apo to back sto front?
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
//@Configuration indicates that this class contains one or more bean methods annotated 
//with @Bean producing beans manageable
//by spring container. In our case, this class represent hibernate configuration.
@EnableTransactionManagement
//elegxei kata poso auta poy ginontai apo back sti basi ginontai swsta
//(kanei kai serializable)
//enabling Spring’s annotation-driven transaction management capability.
@ComponentScan({"com.websystique.springmvc.configuration"})
//@ComponentScan is equivalent to context:component-scan base-package="..." in xml,
//providing with where to look for spring managed beans/classes.
@PropertySource(value = {"classpath:application.properties"})
/*@PropertySource is used to declare a set of properties(defined in a properties 
file in application classpath) in Spring run-time Environment, providing flexibility
to have different values in different application environments.*/
public class HibernateConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    //Method sessionFactory() is creating a LocalSessionFactoryBean, which 
    //exactly mirrors the XML based configuration :
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        //odigeies syndesis sti basi
        sessionFactory.setPackagesToScan(new String[]{"com.websystique.springmvc.model"});
        //odigeies gia to pou tha brei ta entities
        //ftiakse pinaka apo string pou legetai com....
        //kai paei sto model kai briskei tin employee(kati tetoio)
        sessionFactory.setHibernateProperties(hibernateProperties());
        //odigeies gia ta hibernate properties
        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        //DriverManagerDataSource mia class pou kanei connection
        
        //Interface representing the environment in which the current application is running.
        //Models two key aspects of the application environment: profiles and properties.
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }

    //to jpql egine hql
    //blepw ton kwdika pou exw grapsei kai to emfanizei se sql
    //kai ayta yparxoyn mesa sto application.properties
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        return properties;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(s);
        return txManager;
    }
}
