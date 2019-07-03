package com.lexrizen.UserRegistration.web;

import com.lexrizen.UserRegistration.model.Message;
import com.lexrizen.UserRegistration.model.User;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    @RequestMapping(value = "/hello")
    public String hello(){
        return  "Hello, World";
    }

    @RequestMapping(value = "postmessage",method = RequestMethod.POST)
    public String postMessage(String message){
        return "OK";
    }

    @RequestMapping(value = "registeruser",method = RequestMethod.POST)
    public Message registerUser(@RequestBody User user){
        return new Message();
    }

}