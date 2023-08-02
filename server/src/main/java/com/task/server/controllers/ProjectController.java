package com.task.server.controllers;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.task.server.controllers.base.BaseController;
import com.task.server.dto.ProjectCreateDto;
import com.task.server.entity.Projects;
import com.task.server.entity.Users;
import com.task.server.services.ProjectService;
import com.task.server.services.UserService;
import com.task.server.utils.MessageResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;
import com.mysema.commons.lang.Assert;


//  archive a proj: decide what this means ltr
@SuppressWarnings({"all"})
@RestController
@Controller
public class ProjectController extends BaseController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    
    // should return all projects under team with it's default project board id
    @RequestMapping(value = "/projects/all", method = RequestMethod.GET) 
    public MessageResult allProjectsByUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
        UUID userId = (UUID) request.getAttribute("userId");
        if (userId.toString().isEmpty()){
            throw new Exception("Auth error");
        }

        List<Object[]> result = projectService.getAllProjectsByUserId(userId.toString());

        return success(200, result);
    }

    // project boards shows the tasks in the project
    @RequestMapping(value = "/projects/board/{id}", method = RequestMethod.GET)
    public MessageResult getProjectBoard(@PathVariable String id) throws Exception {
        // projects default board -> all categories -> task under each cat. task tags, comment number 
        if (id.isEmpty()){
            throw new Exception("url error"); 
        }

        Object board = projectService.getBoardDetails(id);

        return success(200, board);
    }

    // get people preview list in a project
    @RequestMapping(value = "/projects/people/{id}", method = RequestMethod.GET)
    public MessageResult getPeoplePreviewForProject(@PathVariable String id) throws Exception {
        // projects default board -> all categories -> task under each cat. task tags, comment number 
        if (id.isEmpty()){
            throw new Exception("url error"); 
        }

        List<Object[]> people = projectService.getPeoplePreviewList(id);

        return success(200, people);
    }

    @RequestMapping(value = "/projects/favourites", method = RequestMethod.GET)
    public MessageResult getUsersFavouriteProjects(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UUID loggedInUserId = (UUID) request.getAttribute("userId");

        List<Object[]> favProjects = projectService.getUsersFavouriteProjects(loggedInUserId.toString());

        return success(200, favProjects);
    }

    @SuppressWarnings({"all"})
    @RequestMapping(value = "/projects", method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    public MessageResult createProject(HttpServletRequest request) throws Exception{
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
        Assert.hasText(map.get("label"),"MISSING_LABEL");
        Assert.hasText(map.get("description"),"MISSING_DESCRIPTION");

        if (map.get("label").isEmpty() || map.get("description").isEmpty()){
            throw new Error("empty input not allowed");
        }
        String user_id = (String) request.getAttribute("userId");
        Users creator = userService.findById(user_id);
        ProjectCreateDto project = new ProjectCreateDto(map.get("label"), map.get("description"), creator);
        Projects new_project = projectService.create(project);
        return success(201, new_project);
    }
}
