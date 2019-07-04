package com.lexrizen.UserRegistration.web;

import com.lexrizen.UserRegistration.model.Message;
import com.lexrizen.UserRegistration.model.UserDetail;
import com.lexrizen.UserRegistration.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    private Logger log = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    UserService userService;

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello(){
        return  "Hello, World";
    }

    @RequestMapping(value = "postmessage",method = RequestMethod.POST)
    public String postMessage(@RequestBody String message){
        return "OK";
    }

    @RequestMapping(value = "registeruser",method = RequestMethod.POST,produces = "application/json",consumes = "application/json")
    @ResponseBody
    public Message registerUser(@RequestBody UserDetail user){
       log.trace("registerUser");
        return this.userService.save(user);
    }

    @RequestMapping(value = "finduser",method = RequestMethod.POST,produces = "application/json")
    public UserDetail getUser(@RequestBody String email){
        log.info(email);
       return userService.findbyEmail(email);
    }

}