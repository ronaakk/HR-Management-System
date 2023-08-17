package com.jrp.pma.Security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	BCryptPasswordEncoder bCryptEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
			// the ? is essentially regex, which means where a string exists
			.usersByUsernameQuery("SELECT username, password, enabled " +
					"FROM user_accounts WHERE username = ?")
			.authoritiesByUsernameQuery("SELECT username, role " +
					"FROM user_accounts WHERE username = ?")
			// this will encrypt the passwords entered for each user in our db
			.passwordEncoder(bCryptEncoder);
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			// Most protected comes on top, while everything else below (reads sequentially)
			// Only ADMIN users can create new projects/employees
//			.antMatchers("/projects/new").hasAuthority("ADMIN")
//			.antMatchers("/projects/save").hasAuthority("ADMIN")
//			.antMatchers("/employee/new").hasAuthority("ADMIN")
//			.antMatchers("/employee/save").hasAuthority("ADMIN")
			// Anyone can access this page
			.antMatchers("/","/**").permitAll().and().formLogin();
		
	}

}
