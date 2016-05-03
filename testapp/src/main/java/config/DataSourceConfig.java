package config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource ds(){
    	DriverManagerDataSource ds = new DriverManagerDataSource();
    	ds.setDriverClassName("org.postgresql.Driver");
    	ds.setPassword("admin");
    	ds.setUsername("postgres");
    	ds.setUrl("jdbc:postgresql://127.0.0.1:5432/testapp");
    	return ds;
    }
    
    @Bean
    public Properties getHibernateProperties() {

        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        hibernateProperties.put("hibernate.show_sql", true);
        hibernateProperties.put("hibernate.generate_statistics", false);
        hibernateProperties.put("hibernate.hbm2ddl.auto", "update");
        hibernateProperties.put("hibernate.use_sql_comments", false);

        return hibernateProperties;
    }
}
