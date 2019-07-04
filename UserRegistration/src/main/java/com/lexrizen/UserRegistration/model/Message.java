package com.lexrizen.UserRegistration.model;

import java.io.Serializable;

public class Message implements Serializable {

    private int code;
    private String description;

    public Message(){
        this.code = 200;
        this.description = "Success";
    }

    public Message(int code,String message){
        this.code = code;
        this.description = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

    
}