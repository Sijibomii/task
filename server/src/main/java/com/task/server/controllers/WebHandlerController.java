package com.task.server.controllers;

import java.util.HashMap;
import java.util.Map;

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
public class WebHandlerController extends BaseController{
    
    @RequestMapping(value = "/webhandlers", method = RequestMethod.GET)
    public MessageResult getWebHandlers(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        // logic to return best 2 webhandler ips for client to connect to
        Map<String,String> map = new HashMap<>();

        map.put("server-1", "127.0.0.1:8081");
        map.put("server-2", "127.0.0.1:8081");
        return success(200, map);
    }

}
