package com.task.server.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.task.server.controllers.base.BaseController;
import com.task.server.services.ProjectService;
import com.task.server.utils.MessageResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


// note: there should be a default project board

// create new projects
//  archive a proj: decide what this means ltr
@SuppressWarnings({"all"})
@RestController
@Controller
public class ProjectController extends BaseController {

    @Autowired
    private ProjectService projectService;

    
    // should return all projects with each project's category
    @RequestMapping(value = "/projects/all", method = RequestMethod.GET)
    public MessageResult allProjectsByUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String userId = (String) request.getAttribute("userId");
        if (userId.isEmpty()){
            throw new Exception("Auth error");
        }
        List<Map<String, Object>> result = projectService.getAllProjectsByUserId(userId);

        return success(200, result);
    }

    // project boards alone
    @RequestMapping(value = "/projects/board/{id}", method = RequestMethod.GET)
    public MessageResult getProjectBoard(@PathVariable String id) throws Exception {
        // projects default board -> all categories -> task under each cat. task tags, comment number 
        if (id.isEmpty()){
            throw new Exception("url error");
        }

        List<Map<String, Object>> board = projectService.getBoardDetails(id);

        return success(200, board);
    }

}
