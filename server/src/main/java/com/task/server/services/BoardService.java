package com.task.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.server.dao.FavouriteBoardDao;
import com.task.server.entity.Boards;

@Service
public class BoardService {
    
    @Autowired
    private FavouriteBoardDao favouriteBoardDao;

    public List<? extends Boards> getFavouriteBoards(String UserId) throws Exception{
        return favouriteBoardDao.queryFavouriteBoardsByUserId(UserId);
    }
}
