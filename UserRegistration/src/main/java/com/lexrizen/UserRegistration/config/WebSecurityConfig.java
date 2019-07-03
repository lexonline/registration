package com.lexrizen.UserRegistration.config;

import com.lexrizen.UserRegistration.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Spring configuration class for initializing user authentication. The Spring
 * AuthenticationManager will be configured to use the custom
 * <code>UserService</code> class to retrieve user details from the
 * <code>DataSource</code>.
 *
 * Created by Chris on 3/5/2015.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Users are authenticated by our custom UserDetailsService implementation. Usernames and passwords are
        // stored in the database, along with user roles/authorities
        auth.userDetailsService(this.userService);
    }
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers(HttpMethod.OPTIONS, "/oauth/token").permitAll();
    }

}