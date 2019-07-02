package com.lexrizen.UserRegistration.model;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Attr;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
public class User implements Serializable {

    private String id;
    private String username;
    private String password;
    private String phone;
    private String address;
    private Double salary;
    private String membertype;

}