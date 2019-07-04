package com.lexrizen.UserRegistration.config;


import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import com.lexrizen.UserRegistration.service.CustomTokenServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    // oAuth client details are retrieved from this DataSource
    @Autowired
    private DataSource dataSource;
    
    private CustomTokenServices customTokenServices;

    @PostConstruct
    public void init() {
        this.customTokenServices = new CustomTokenServices();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // Configure the token services and authentication manager for this authorization Server
        endpoints.authenticationManager(authenticationManager).tokenServices(customTokenServices);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // Client (application) details are stored in the database.  See OAUTH_CLIENT_DETAILS table.
        clients.jdbc(this.dataSource);
    }
 
}