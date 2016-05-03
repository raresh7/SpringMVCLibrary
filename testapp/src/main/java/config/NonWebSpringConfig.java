package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import aspects.Logging;
@Configuration
//@PropertySource("classpath:controller.properties")
@ComponentScan(basePackages={"controllers", "aspects", "dao", "entities", "services", "config"}) 
@EnableAspectJAutoProxy(proxyTargetClass=true)
@EnableTransactionManagement
@Import(DataSourceConfig.class)
public class NonWebSpringConfig {

	@Autowired
	DataSourceConfig dataSourceConfig;

    
    @Bean
    public Logging logging(){
    	return new Logging();
    }
    
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
       LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
       sessionFactory.setDataSource(dataSourceConfig.ds());
       sessionFactory.setPackagesToScan(new String[] { "entities" });
       return sessionFactory;
    }
    
    @Bean
    public HibernateTransactionManager transactionManager() {
	    HibernateTransactionManager transactionManager = new HibernateTransactionManager();
	    transactionManager.setSessionFactory(sessionFactory().getObject());
	    //transactionManager.setDataSource(ds());
	    return transactionManager;
}

}







