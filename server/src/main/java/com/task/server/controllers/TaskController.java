package com.task.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.task.server.services.TaskService;
import com.task.server.utils.MessageResult;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TaskController {

    @Autowired
    private TaskService taskService;

    // fetch task from backend
    // @RequestMapping(value = "/tasks/{taskId}", method = RequestMethod.GET)
    // public MessageResult favouriteBoards(HttpServletRequest request, HttpServletResponse response) throws Exception{

    //     String userId = (String) request.getAttribute("userId");

    //     if (userId.isEmpty()){
    //         throw new Exception("Auth error");
    //     }

    //     Object fav = taskService.getTaskAndCommentByTaskId(userId);

    //     return success(200, fav);
    // }
    // - swtich task category from backend
    // - add task comment to backend and connect with frontend
}
