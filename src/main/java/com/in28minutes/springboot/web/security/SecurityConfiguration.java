package com.in28minutes.springboot.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	//Create User - in28Minutes/dummy
	/**
	* Configures global security settings for authentication.
	* This method sets up in-memory authentication with a single user.
	* 
	* @param auth The AuthenticationManagerBuilder to configure
	* @throws Exception If there's an error during configuration
	*/
	@Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication().withUser("in28Minutes").password("dummy")
                .roles("USER", "ADMIN");
    }
	
	/**
	 * Configures HTTP security for the application.
	 * 
	 * This method overrides the default security configuration to set up
	 * authentication and authorization rules for different URL patterns.
	 * 
	 * @param http The HttpSecurity object to be configured
	 * @throws Exception If an error occurs during configuration
	 */
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/login").permitAll()
                .antMatchers("/", "/*todo*/**").access("hasRole('USER')").and()
                .formLogin();
    }
}
