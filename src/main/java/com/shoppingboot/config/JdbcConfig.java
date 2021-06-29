package com.shoppingboot.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.shoppingboot.dao.CartDAO;
import com.shoppingboot.dao.CartDAOImpl;
import com.shoppingboot.dao.ContactDAO;
import com.shoppingboot.dao.ContactDAOImpl;
import com.shoppingboot.dao.LoginDAO;
import com.shoppingboot.dao.LoginDAOImpl;
import com.shoppingboot.dao.OrdersDAO;
import com.shoppingboot.dao.OrdersDAOImpl;
import com.shoppingboot.dao.ProductDAO;
import com.shoppingboot.dao.ProductDAOImpl;
import com.shoppingboot.dao.UserDAO;
import com.shoppingboot.dao.UserDAOImpl;

@Configuration
@PropertySource(value= {"classpath:environment.properties"})
public class JdbcConfig {
	
	@Autowired
	private Environment env;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(env.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(env.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(env.getRequiredProperty("jdbc.password"));
        
        return dataSource;
    }
 
    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }
    
    
    @Bean
    public CartDAO cartDAO(){
    	CartDAOImpl cartDao = new CartDAOImpl();
    	cartDao.setJdbcTemplate(jdbcTemplate());
    	return cartDao;
    }
    
    @Bean
    public ContactDAO contactDAO(){
    	ContactDAOImpl contactDao = new ContactDAOImpl();
    	contactDao.setJdbcTemplate(jdbcTemplate());
    	return contactDao;
    }
    
    @Bean
    public LoginDAO loginDAO(){
    	LoginDAOImpl loginDao = new LoginDAOImpl();
    	loginDao.setJdbcTemplate(jdbcTemplate());
    	return loginDao;
    }
    
    @Bean
    public OrdersDAO ordersDAO(){
    	OrdersDAOImpl ordersDao = new OrdersDAOImpl();
    	ordersDao.setJdbcTemplate(jdbcTemplate());
    	return ordersDao;
    }
    
    @Bean
    public ProductDAO productDAO(){
    	ProductDAOImpl productDao = new ProductDAOImpl();
    	productDao.setJdbcTemplate(jdbcTemplate());
    	return productDao;
    }
    
    @Bean
    public UserDAO userDAO(){
    	UserDAOImpl userDao = new UserDAOImpl();
    	userDao.setJdbcTemplate(jdbcTemplate());
    	return userDao;
    }
    
}
