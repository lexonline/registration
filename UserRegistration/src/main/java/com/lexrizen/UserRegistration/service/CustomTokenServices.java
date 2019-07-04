package com.lexrizen.UserRegistration.service;



import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.stereotype.Component;

@Component
public class CustomTokenServices extends DefaultTokenServices {
    
    // Key used to sign the JWT access token
    @Value("${jwt.signing.key}")
    private String jwtSigningKey;
    
    private Logger log;
    
    /**
     * Constructs a new <code>CustomTokenServices</code> object.
     * 
     */
    public CustomTokenServices() {
        super();
        this.log = LoggerFactory.getLogger(getClass());
    }

    /**
     * Initializes the bean in the Spring context.
     * 
     */
    @PostConstruct
    public void init() {
        // Initialize the access token converter (for JWT tokens)/
        // Converts the access token to the JWT format.
        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
        tokenConverter.setSigningKey(this.jwtSigningKey);

        try {
            tokenConverter.afterPropertiesSet();
        } catch (Exception e) {
            log.error("Failed to initialize JWT access token converter", e);
        }

        // Initialize the JWT token store.
        // Even though no tokens are being persisted, a TokenStore is still
        // needed for JWT tokens to work within the Spring oAuth framework.
        JwtTokenStore tokenStore = new JwtTokenStore(tokenConverter);
        
        // Set access token expiration time to 16 hours (57600 seconds)
        this.setAccessTokenValiditySeconds(57600);
        
        // Disable refresh tokens
        this.setReuseRefreshToken(false);
        this.setSupportRefreshToken(false);

        this.setTokenEnhancer(tokenConverter);
        this.setTokenStore(tokenStore);
        
        // Finalize the token services
        try {
            this.afterPropertiesSet();
        } catch (Exception e) {
            log.error("Failed to initialize token services", e);
        }
    }
    

    
}
