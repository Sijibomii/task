package com.task.server.controllers;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.task.server.controllers.base.BaseController;
import com.task.server.utils.MessageResult;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// all channels by: channels/org/_id ...
// post of the above should be creating a new channel
@SuppressWarnings({"all"})
@RestController
@Controller
public class ChannelController extends BaseController{
    
    // get all channels assigned to this org. Note: return only channels user is a memeber of except user has see all channels perm
    @RequiresPermissions("channel:create:organization:{orgId}")
    @RequestMapping(value = "/channels/organization/{id}", method = RequestMethod.GET)
    public MessageResult getChannelsOrg(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String userId = (String) request.getAttribute("userId");
        if (userId.isEmpty()){
            throw new Exception("Auth error");
        }
        
        return success();
    }

    // check if user has channel create permission
    @RequestMapping(value = "/channels/organization/{id}", method = RequestMethod.POST)
    public MessageResult createChannelsOrg(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String userId = (String) request.getAttribute("userId");
        if (userId.isEmpty()){
            throw new Exception("Auth error");
        }
        
        return success();
    }

    @RequestMapping(value = "/channels/team/{id}", method = RequestMethod.GET)
    public MessageResult getChannelsTeam(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String userId = (String) request.getAttribute("userId");
        if (userId.isEmpty()){
            throw new Exception("Auth error");
        }
        
        return success();
    }

    @RequestMapping(value = "/channels/team/{id}", method = RequestMethod.POST)
    public MessageResult createChannelsTeam(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String userId = (String) request.getAttribute("userId");
        if (userId.isEmpty()){
            throw new Exception("Auth error");
        }
        
        return success();
    }

    @RequestMapping(value = "/channels/project/{id}", method = RequestMethod.POST)
    public MessageResult createChannelsProject(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String userId = (String) request.getAttribute("userId");
        if (userId.isEmpty()){
            throw new Exception("Auth error");
        }
        
        return success();
    }

    @RequestMapping(value = "/channels/project/{id}", method = RequestMethod.GET)
    public MessageResult getChannelsProject(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String userId = (String) request.getAttribute("userId");
        if (userId.isEmpty()){
            throw new Exception("Auth error");
        }
        
        return success();
    }
}
