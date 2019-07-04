package com.lexrizen.UserRegistration.config;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.annotation.PostConstruct;

/**
 * Spring configuration class for initializing the secure resource server.
 *
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    // Identifier for the protected resources
    @Value("${secure.resource.identifier}")
    private String RESOURCE_ID;

    // Key used to sign the JWT access token
    @Value("${jwt.signing.key}")
    private String jwtSigningKey;

    // Even though no tokens are being persisted, a TokenStore is still needed
    // for JWT tokens to work within the Spring oAuth framework.
    private TokenStore tokenStore;

    // Converts the access token to the JWT format
    private JwtAccessTokenConverter tokenConverter;

    @PostConstruct
    public void init() {
        // Initialize the access token converter (for JWT tokens)
        this.tokenConverter = new JwtAccessTokenConverter();
        this.tokenConverter.setSigningKey(this.jwtSigningKey);

        try {
            this.tokenConverter.afterPropertiesSet();
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize JWT access token converter");
        }

        // Initialize the JWT token store:
        this.tokenStore = new JwtTokenStore(this.tokenConverter);
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.tokenStore(this.tokenStore).resourceId(RESOURCE_ID);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/user").authenticated().
        antMatchers("/admin").hasRole("ADMIN");
    }

}
