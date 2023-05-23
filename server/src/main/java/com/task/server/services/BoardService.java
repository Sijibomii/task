package com.task.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.server.dao.FavouriteBoardDao;
import com.task.server.entity.Boards;
import com.task.server.entity.FavouriteBoards;

@Service
public class BoardService {
    
    @Autowired
    private FavouriteBoardDao favouriteBoardDao;

    public List<? extends Boards> getFavouriteBoards(String UserId) throws Exception{
        return favouriteBoardDao.queryFavouriteBoardsByUserId(UserId);
    }

    // create favourite board
    public FavouriteBoards saveFavouriteBoard(FavouriteBoards fb) {
        return favouriteBoardDao.save(fb); 
    }
}
