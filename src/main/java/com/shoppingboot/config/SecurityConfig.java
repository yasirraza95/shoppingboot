package com.shoppingboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import com.shoppingboot.service.AdminAuthenticationService;
import com.shoppingboot.service.UserAuthenticationService;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "shopping")
public class SecurityConfig {
	
	@Component
//	@Order(1)
	public static class AdminConfiguration extends WebSecurityConfigurerAdapter {
		
		public AdminConfiguration() {
			super();
		}

		@Autowired
		AdminAuthenticationService adminAuthService;

		@Autowired
		public void configure(AuthenticationManagerBuilder auth) throws Exception {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			auth.userDetailsService(adminAuthService).passwordEncoder(encoder);
		}
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.antMatcher("/admin/**").authorizeRequests().antMatchers("/admin/login").permitAll()
					.antMatchers("/admin/**").authenticated().antMatchers("/admin/**").hasRole("ADMIN")

					.and().formLogin().loginPage("/admin/login").loginProcessingUrl("/admin/spring_security_login")
					.defaultSuccessUrl("/admin/adminproviders").failureUrl("/admin/login?error=true")

					.and().logout().logoutUrl("/admin/logout").logoutSuccessUrl("/admin/login").invalidateHttpSession(true)

					.and().csrf().disable();
		}

		@Override
		public void configure(WebSecurity web) {
			web.ignoring().antMatchers("/resources/**", "/static/**");
		}

	}

	@Component
	@Order(2)
	public static class UserConfiguration extends WebSecurityConfigurerAdapter {
		
		@Autowired
		UserAuthenticationService userAuthService;

		@Autowired
		public void configure(AuthenticationManagerBuilder auth) throws Exception {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			auth.userDetailsService(userAuthService).passwordEncoder(encoder);
		}
		

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.antMatcher("/user/*").authorizeRequests().antMatchers("/user/login", "/user/signup").permitAll()
					.antMatchers("/user/", "/user/about", "/user/contact", "/user/products").permitAll()
					.antMatchers("/user/**").authenticated()
					.antMatchers("/user/**").hasRole("USER")

					.and().
					formLogin().loginPage("/user/login").loginProcessingUrl("/user/spring_security_login")
					.defaultSuccessUrl("/user/").failureUrl("/user/login?error=true")

					.and().logout().logoutUrl("/user/logout").logoutSuccessUrl("/user/login").invalidateHttpSession(true)

					.and().csrf().disable();
		}

		@Override
		public void configure(WebSecurity web) {
			web.ignoring().antMatchers("/resources/**", "/static/**");
		}
		
	}

}