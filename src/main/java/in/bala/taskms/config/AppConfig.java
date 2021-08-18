package in.bala.taskms.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages="in.bala.taskms")
public class AppConfig {
	
	
	// Hibernate Properties
	public Properties getHibernateProperties() {
		Properties prop=new Properties();

		prop.put("hibernate.dialect","org.hibernate.dialect.MySQL5Dialect");
		prop.put("hibernate.show_sql", "true");
		prop.put("hibernate.format_sql", "true");
		prop.put("hibernate.hbm2ddl.auto", "update");
		
		return prop;
	}
	
	@Bean
	public DataSource getDataSource() {
		BasicDataSource bds=new BasicDataSource();
		bds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		bds.setUrl("jdbc:mysql://localhost:3306/testDB?useSSL=false");
		bds.setUsername("root");
		bds.setPassword("root");
		
		return bds;
	}
	
	@Bean
	public SessionFactory getSessionFactory(DataSource ds) {
		LocalSessionFactoryBuilder lsb=new LocalSessionFactoryBuilder(ds);
		lsb.addProperties(getHibernateProperties());
		lsb.scanPackages("in.bala.taskms");
		
		return lsb.buildSessionFactory();
       
	}
	
	
	@Bean
	public HibernateTransactionManager getTransactionManager(SessionFactory sf) {
		HibernateTransactionManager htm=new HibernateTransactionManager(sf);
		return htm;
		
	}
	
	// view Resolver Configuration
	@Bean
	public ViewResolver getViewResolver() {
		
		InternalResourceViewResolver ivr= new InternalResourceViewResolver();
		ivr.setViewClass(JstlView.class);
		ivr.setPrefix("/WEB-INF/views/");
		ivr.setSuffix(".jsp");
		
		return ivr;
	}

}
