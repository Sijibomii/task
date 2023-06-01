package com.task.server.controllers;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.task.server.controllers.base.BaseController;
import com.task.server.services.ChannelService;
import com.task.server.utils.MessageResult;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// all channels by: channels/org/_id ...
// post of the above should be creating a new channel
@SuppressWarnings({"all"})
@RestController
@Controller
public class ChannelController extends BaseController{

    @Autowired
    private ChannelService channelService;

    
    // this is for getting a particular channel and its messages. should not return messages if sending user is not a memeber


    // get all channels mapped to the org
    @RequestMapping(value = "/channels/organization/{orgId}", method = RequestMethod.GET)
    public MessageResult getChannelsOrg(HttpServletRequest request, HttpServletResponse response, @PathVariable Long orgId) throws Exception{
        String userId = (String) request.getAttribute("userId");
        if (userId.isEmpty()){
            throw new Exception("Auth error");
        }
        // get all channels assigned to this org. Note: return only channels user is a memeber of except user has see all channels perm
        List<?> result = channelService.getOrgChannelsWhereUserIsMemeber(userId, orgId.toString());
        return success(200, result);
    }

    // check if user has channel create permission mapped to this org
    @RequiresPermissions("channel:create:organization:{orgId}")
    @RequestMapping(value = "/channels/organization/{orgId}", method = RequestMethod.POST)
    public MessageResult createChannelsOrg(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String userId = (String) request.getAttribute("userId");
        if (userId.isEmpty()){
            throw new Exception("Auth error");
        }
        
        return success();
    }

    // get all channels mapped to the team of course only ones user is a member of except you are the creator
    @RequestMapping(value = "/channels/team/{id}", method = RequestMethod.GET)
    public MessageResult getChannelsTeam(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String userId = (String) request.getAttribute("userId");
        if (userId.isEmpty()){
            throw new Exception("Auth error");
        }
        
        return success();
    }

    @RequiresPermissions("channel:create:team:{teamId}")
    @RequestMapping(value = "/channels/team/{teamId}", method = RequestMethod.POST)
    public MessageResult createChannelsTeam(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String userId = (String) request.getAttribute("userId");
        if (userId.isEmpty()){
            throw new Exception("Auth error");
        }
        
        return success();
    }
 
    @RequiresPermissions("channel:create:project:{projectId}")
    @RequestMapping(value = "/channels/project/{projectId}", method = RequestMethod.POST)
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
