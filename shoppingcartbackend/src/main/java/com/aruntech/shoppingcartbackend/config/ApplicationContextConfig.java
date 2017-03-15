package com.aruntech.shoppingcartbackend.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.aruntech.shoppingcartbackend.model.Category;
import com.aruntech.shoppingcartbackend.model.OrderTable;
import com.aruntech.shoppingcartbackend.model.Product;
import com.aruntech.shoppingcartbackend.model.ShoppingCart;
import com.aruntech.shoppingcartbackend.model.Supplier;
import com.aruntech.shoppingcartbackend.model.User;


@Configuration

@ComponentScan("com.aruntech*")

@EnableTransactionManagement

public class ApplicationContextConfig 
{
	private static Logger log = LoggerFactory.getLogger(ApplicationContextConfig.class);
   
	@Bean(name="dataSource")
	public DataSource getH2DataSource()
	   	{
			log.debug("Configuring Data Source in ApplicationContextConfig");
	       	DriverManagerDataSource dataSource=new DriverManagerDataSource();
	       	dataSource.setUrl("jdbc:h2:tcp://localhost/~/ShoppingCart");
			dataSource.setDriverClassName("org.h2.Driver");
			dataSource.setUsername("Arun");
			dataSource.setPassword("arun");
			log.debug("Returning DataSource");
			return dataSource;
	   	}

	private Properties getHibernateProperties()
		{
			log.debug("Getting Hibernate properties");
			Properties properties=new Properties();
			properties.put("hibernate.dialect","org.hibernate.dialect.H2Dialect");
			log.debug("Returning Hibernate properties");
			return properties;

		}

 
	@Autowired
	@Bean(name="sessionFactory")
	public SessionFactory getsessionFactory(DataSource dataSource)
		{
			log.debug("Session factory activated");
			LocalSessionFactoryBuilder sessionBuilder=new LocalSessionFactoryBuilder(dataSource);
			sessionBuilder.addProperties(getHibernateProperties());
			sessionBuilder.addAnnotatedClass(User.class);
			sessionBuilder.addAnnotatedClass(Supplier.class);
			sessionBuilder.addAnnotatedClass(Category.class);
			sessionBuilder.addAnnotatedClass(Product.class);
			sessionBuilder.addAnnotatedClass(OrderTable.class);
			sessionBuilder.addAnnotatedClass(ShoppingCart.class);
			log.debug("Returning SessionFactory");
			return sessionBuilder.buildSessionFactory();
		}

	@Autowired
	@Bean(name="transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory)
		{
			log.debug("Getting Transaction Manager");
			HibernateTransactionManager transactionManager=new HibernateTransactionManager(sessionFactory);
			log.debug("Returning Transaction manager");
			return transactionManager;
		}

}
