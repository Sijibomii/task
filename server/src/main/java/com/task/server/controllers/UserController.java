package com.task.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.transaction.annotation.Transactional;

import com.mysema.commons.lang.Assert;
import com.task.server.controllers.base.BaseController;
import com.task.server.services.UserService;
import com.task.server.utils.MessageResult;

import jakarta.servlet.http.HttpServletRequest;

public class UserController extends BaseController{
    
    @Autowired
    private UserService userService;

    // @Autowired
    // private MemberEvent memberEvent;

    @RequestMapping(value = "/login")
    @Transactional(rollbackFor = Exception.class)
    public MessageResult login(HttpServletRequest request, String username, String password) {
        Assert.hasText(username,"MISSING_USERNAME");
        Assert.hasText(password, "MISSING_PASSWORD");

        return MessageResult()
    }


  
}
