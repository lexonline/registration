package com.lexrizen.UserRegistration.model;

import java.io.Serializable;

public class Message implements Serializable {

    private int code;
    private String message;

    public Message(){
        this.code = 200;
        this.message = "Success";
    }

    public Message(int code,String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    
}