package com.task.server.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.task.server.controllers.base.BaseController;
import com.task.server.utils.MessageResult;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings({"all"})
@RestController
@Controller
public class OrganizationController extends BaseController{
    
    @RequestMapping(value = "/organization", method = RequestMethod.POST)
    public MessageResult createOrganization(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String userId = (String) request.getAttribute("userId");
        // assign permissions to creators
        
        return success();
    }
}
