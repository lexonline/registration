package com.lexrizen.UserRegistration.db.dao;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

import com.lexrizen.UserRegistration.db.dto.User;

public interface UserRepository extends CrudRepository<User, String> {

    public User findById(String id);
    public User findByEmailAddress(String emailAddress);
    public User findByPhone(String phone);
    public List<User> findByFirstName(String firstName);
    public List<User> findByLastName(String lastName);

}