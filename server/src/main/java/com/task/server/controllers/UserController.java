package com.task.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.transaction.annotation.Transactional;

import com.mysema.commons.lang.Assert;
import com.task.server.controllers.base.BaseController;
import com.task.server.services.UserService;
import com.task.server.utils.MessageResult;
import com.task.server.utils.CaptchaUtil;
import jakarta.servlet.http.HttpServletRequest;

public class UserController extends BaseController{
    
    @Autowired
    private UserService userService;

    // @Autowired
    // private MemberEvent memberEvent;
    // username, password, captcha code on next page send code to email for validation
    @RequestMapping(value = "/login")
    @Transactional(rollbackFor = Exception.class)
    public MessageResult login(HttpServletRequest request, String username, String password, String captcha_code) {
        Assert.hasText(username,"MISSING_USERNAME");
        Assert.hasText(password, "MISSING_PASSWORD");
        Assert.hasText(captcha_code, "MISSING_CAPTCHA");
        
        // get captcha code from redis
        String session_id = request.getHeader("CAPTCHA_SESSION_ID");
        Boolean validated = CaptchaUtil.validate(session_id, captcha_code);

        if(!validated){
            // exception
        }

        //TODO: google captcha

        return success();
    }

    // picture capta


    // send token to email

  
}
