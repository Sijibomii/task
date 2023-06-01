package com.task.server.controllers;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.mysema.commons.lang.Assert;
import com.task.server.controllers.base.BaseController;
import com.task.server.entity.Organizations;
import com.task.server.services.OrganizationService;
import com.task.server.services.PermissionService;
import com.task.server.utils.MessageResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings({"all"})
@RestController
@Controller
public class OrganizationController extends BaseController{

    @Autowired
    private OrganizationService orgService;

    @Autowired
    private PermissionService permissionService;
    
    @RequestMapping(value = "/organization", method = RequestMethod.POST)
    public MessageResult createOrganization(HttpServletRequest request, HttpServletResponse response) throws Exception{
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
        sb.append(line);
        }
        String requestBody = sb.toString();
        String value = StringUtils.substringBetween(requestBody, "{", "}"); 
        String[] keyValuePairs = value.split(",");         
        Map<String,String> map = new HashMap<>();               

        for(String pair : keyValuePairs)    
        {
            String[] entry = pair.split(":");    
            String _key = entry[0].trim(); 
            String key = _key.substring(1, _key.length() - 1);
            String _val = entry[1].trim();
            String val = _val.substring(1, _val.length() - 1);
            map.put(key, val);        
        }  
        // taskboard id. use board type to check for id
        Assert.hasText(map.get("description"),"MISSING BOARD TYPE");

        String userId = (String) request.getAttribute("userId");

        Organizations org = orgService.createOrganization(userId, map.get("description"));

        // assign permissions to creators


    

        
        return success();
    }

    // add user
}
