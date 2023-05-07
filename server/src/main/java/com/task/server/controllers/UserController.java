package com.task.server.controllers;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.transaction.annotation.Transactional;

import com.mysema.commons.lang.Assert;
import com.task.server.controllers.base.BaseController;
import com.task.server.entity.Users;
import com.task.server.services.JwtService;
import com.task.server.services.UserService;
import com.task.server.utils.MessageResult;
import com.task.server.utils.CaptchaUtil;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class UserController extends BaseController{
    
    @Autowired
    private UserService userService;

    @Autowired 
    private JwtService jwtService;

    // @Autowired
    // private MemberEvent memberEvent;
    // username, password, captcha code on next page send code to email for validation
    @RequestMapping(value = "/login")
    @Transactional(rollbackFor = Exception.class)
    public MessageResult login(HttpServletRequest request) throws Exception{
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
        sb.append(line);
        }
        String requestBody = sb.toString();
        String value = StringUtils.substringBetween(requestBody, "{", "}"); //requestBody.substring(1, requestBody.length()-1);           //remove curly brackets
        String[] keyValuePairs = value.split(",");              //split the string to creat key-value pairs
        Map<String,String> map = new HashMap<>();               

        for(String pair : keyValuePairs)                        //iterate over the pairs
        {
            String[] entry = pair.split(":");    
            String _key = entry[0].trim(); 
            String key = _key.substring(1, _key.length() - 1);

            String _val = entry[1].trim();
            String val = _val.substring(1, _val.length() - 1);

            map.put(key, val);          //add them to the hashmap and trim whitespaces   
        }  
        Assert.hasText(map.get("email"),"MISSING_USERNAME");
        Assert.hasText(map.get("password"), "MISSING_PASSWORD");
        Assert.hasText(map.get("captcha"), "MISSING_CAPTCHA");

        Boolean validated = CaptchaUtil.validate(request.getSession(), "", map.get("captcha"));

        if(!validated){
            // exception 
        }
         //TODO: google captcha

        //  return tokens

        Users user = userService.login(map.get("email"), map.get("password"));

        if(user == null){
            //throw
        }
        // send mail 


        // jwt tokens
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        return success();
    }

    // picture capta


    // send token to email

  
}
