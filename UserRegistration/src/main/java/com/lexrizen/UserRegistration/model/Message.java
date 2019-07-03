package com.lexrizen.UserRegistration.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Message implements Serializable {

    private int code;
    private String message;


}