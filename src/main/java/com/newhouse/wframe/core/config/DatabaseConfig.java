package com.newhouse.wframe.core.config;


import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories({"com.newhouse.wframe.core.repository", "com.newhouse.wframe.model.repository"})
@PropertySource(value="classpath:application.properties")
public class DatabaseConfig {

	private static final Logger logger = LoggerFactory.getLogger(DatabaseConfig.class);
	
	private static final String CONFIG_DIALECT = "org.hibernate.dialect.MySQL5Dialect"; 
	private static final String[] CONFIG_PACKAGESTOSCAN = {
			"com.newhouse.wframe.core.entity",
			"com.newhouse.wframe.model.entity"
	};
	
	@Autowired
	private Environment env;
	
    @Bean
    public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.username"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		return dataSource;	
    }
    
    @Bean(name="entityManagerFactory")
    public EntityManagerFactory   entityManagerFactory() {
    	LocalContainerEntityManagerFactoryBean factory; 
    	factory = new LocalContainerEntityManagerFactoryBean();
    	HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    	factory.setDataSource(dataSource());
    	factory.setJpaVendorAdapter(vendorAdapter);
    	factory.setPackagesToScan(CONFIG_PACKAGESTOSCAN);
    	Properties jpaProperties = new Properties();
    	jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
    	jpaProperties.put("hibernate.connection.driver_class", CONFIG_DIALECT);
    	jpaProperties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
    	jpaProperties.put("hibernate.format_sql", env.getProperty("hibernate.show_sql"));
    	factory.setJpaProperties(jpaProperties);
    	factory.afterPropertiesSet();
    	//factory.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
    			
		return factory.getObject();
    }
    
    @Bean
    //@DependsOn("entityManagerFactory")
    public ResourceDatabasePopulator initDatabase(DataSource dataSource) throws Exception{
    	
    	ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
    	populator.addScript(new ClassPathResource("data.sql"));
    	populator.populate(dataSource.getConnection());
    	return populator;
    }
/*    
    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        //hibernateJpaVendorAdapter.setShowSql(Boolean.valueOf(env.getProperty("hibernate.show_sql")));
        //hibernateJpaVendorAdapter.setGenerateDdl(Boolean.valueOf(env.getProperty("hibernate.hbm2ddl.auto")));
        //hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
        //hibernateJpaVendorAdapter.setDatabasePlatform(CONFIG_DIALECT);
        return hibernateJpaVendorAdapter;
    }
*/
    @Bean
    public PlatformTransactionManager transactionManager() {
    	JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
    }
    


}
