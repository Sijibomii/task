package com.task.server.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.task.server.controllers.base.BaseController;
import com.task.server.utils.MessageResult;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// favourite boards
// add a board as favourite
// add task to a board
// archive a board: decide what this means ltr
@SuppressWarnings({"all"})
@RestController
@Controller
public class BoardController extends BaseController{
    
    @RequestMapping(value = "/boards/favourites", method = RequestMethod.GET)
    public MessageResult favouriteBoards(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String userId = (String) request.getAttribute("userId");
        if (userId.isEmpty()){
            throw new Exception("Auth error");
        }

        
      
        return success();
    }
}
