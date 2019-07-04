package com.lexrizen.UserRegistration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.lexrizen.UserRegistration.db.dao.UserRepository;
import com.lexrizen.UserRegistration.db.dto.User;
import com.lexrizen.UserRegistration.model.Message;
import com.lexrizen.UserRegistration.model.UserDetail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private Logger log;

    /**
     * Constructs a new UserService.
     */
    public UserService() {
        this.log = LoggerFactory.getLogger(getClass());
    }

    /**
     * Loads User details, including roles/authorities.
     *
     * @param username The unique username of the user
     * @return <code>org.springframework.security.core.userdetails.UserDetails</code>
     * @throws UsernameNotFoundException If the provided <code>username</code>
     *         does not exist in the <code>DataSource</code>
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;

        try {
            // Get the user record from the DataSource
            log.info("Retrieving user data for {}", username);
            user = this.userRepository.findByEmailAddress(username);
        } catch (Exception e) {
            log.error("Failed to locate user with username of '" + username + "'", e);
        }

        if ( user == null ) {
            log.debug("Username not found:  {}", username);
            throw new UsernameNotFoundException("Invalid username");
        }

        log.debug("Found user data for {}", username);

        Collection<GrantedAuthority> authorities = new LinkedList<GrantedAuthority>();
        try {
            
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
        } catch (Exception e) {
            log.error("Failed to load user's roles", e);
        }


        // Return the user details object required by Spring Security, containing the user's roles/authorities
        return new org.springframework.security.core.userdetails.User(
                       user.getEmailAddress(), user.getPassword(), authorities);
    }

    public UserDetail findbyEmail(String email){
        UserDetail userd = new UserDetail();

        try {
            
            User user = this.userRepository.findByEmailAddress(email);

            if(user != null){
                
               Gson gson = new Gson();
               userd = gson.fromJson(gson.toJson(user), UserDetail.class);

               if(userd != null){
                   userd.setPassword(null);
               }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return userd;
    }

    public Message save(UserDetail userdetail){
        Message ms = null;

        try {
            ms = validateUser(userdetail);

            if(ms.getCode() == 200){
                Gson gson = new Gson();
                User user = gson.fromJson(gson.toJson(userdetail), User.class);

                String membertype = getMemberType(user.getSalary());

                if(membertype.isEmpty()){
                    ms = new Message(501,"Your salary < 15000");
                }
                else {
                    user.setMembertype(membertype);
                    user.setId(generateId(user.getPhone()));
                    this.userRepository.save(user);
                    
                }
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            ms = new Message(502, e.getMessage());
        }

        return ms;
    }

    public Message validateUser(UserDetail userdetail) {
        Message ms = new Message();

        if(userdetail.getFirstName() == null || userdetail.getFirstName().isEmpty()) {
            ms = new Message(501,"First name is not empty");
        }

        if(userdetail.getLastName() == null || userdetail.getLastName().isEmpty()) {
            ms = new Message(501,"Last name is not empty");
        }

        if(userdetail.getEmailAddress() == null || userdetail.getEmailAddress().isEmpty()) {
            ms = new Message(501,"Email is not empty");
        }

        if(userdetail.getPhone() == null || userdetail.getPhone().isEmpty() || userdetail.getPhone().length() < 9) {
            ms = new Message(501,"Phone number is not empty");
        }

        return ms;
    }

    public String getMemberType(Double salary){
        String result = "";
        if(salary > 50000) {
            result = "Platinum";
        }
        else if(salary > 30000){
            result = "Gold";
        }
        else if (salary > 15000){
            result = "Silver";
        }
        return result;
    }

    public String generateId(String phoneNumber){
        String result = "";
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        int lng = phoneNumber.length();
        result = formatter.format(new Date()) + phoneNumber.substring(lng - 4);
        return result;

    }
    
}