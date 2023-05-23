package com.task.server.controllers;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.lang3.StringUtils;
import com.mysema.commons.lang.Assert;
import com.task.server.controllers.base.BaseController;
import com.task.server.entity.Boards;
import com.task.server.entity.FavouriteBoards;
import com.task.server.entity.TaskBoards;
import com.task.server.entity.Users;
import com.task.server.enums.BoardType;
import com.task.server.services.BoardService;
import com.task.server.services.TaskBoardService;
import com.task.server.services.UserService;
import com.task.server.utils.MessageResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// add a board as favourite
// add task to a board
// archive a board: decide what this means ltr
@SuppressWarnings({"all"})
@RestController
@Controller
public class BoardController extends BaseController{

    @Autowired
    private BoardService boardService;

    @Autowired
    private TaskBoardService tBoardService;

    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/boards/favourites", method = RequestMethod.GET)
    public MessageResult favouriteBoards(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String userId = (String) request.getAttribute("userId");
        if (userId.isEmpty()){
            throw new Exception("Auth error");
        }
        List<? extends Boards> fav =  boardService.getFavouriteBoards(userId);
        return success(200, fav);
    }

    @RequestMapping(value = "/boards/favourites/add", method = RequestMethod.POST)
    public MessageResult addFavouriteBoard(HttpServletRequest request, HttpServletResponse response) throws Exception{
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
        Assert.hasText(map.get("board_type"),"MISSING BOARD TYPE");
        Assert.hasText(map.get("board_id"),"MISSING BOARD ID");

        String board_type = map.get("board_type");

        switch (board_type) {
           case "TASKBOARD" :   
                String board_id = map.get("board_id");
                TaskBoards tb = tBoardService.findById(board_id);
                Users user = userService.findById((String) request.getAttribute("userId"));
                FavouriteBoards fb = new FavouriteBoards();
                fb.setBoardType(BoardType.TASKBOARD);
                fb.setCreator(user);
                fb.setTaskboard(tb);
                fb.set_private(false);
                FavouriteBoards ffb = boardService.saveFavouriteBoard(fb);
                return success(200, ffb);
            case "PROJECTBOARD":
                break;
            case "TEAMBOARD":
                break;
            case "USERBOARD":
                break;
            default: 
                return error("INVALID BOARD TYPE");

        }
        // code should normally not get here bc one swtich statement should return
        return error(500, "something went wrong");
    }

}
