package com.task.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.server.connverters.QueryUserBoardDtoConverter;
import com.task.server.dao.FavouriteBoardDao;
import com.task.server.dao.UserBoardDao;
import com.task.server.entity.FavouriteBoards;

@Service
public class BoardService {
    
    @Autowired
    private FavouriteBoardDao favouriteBoardDao;

    @Autowired
    private UserBoardDao userBoardDao;

    @Autowired
    private QueryUserBoardDtoConverter converter;

    public List<?> getFavouriteBoards(String UserId) throws Exception{
        return favouriteBoardDao.queryFavouriteBoardsByUserId(UserId);
    }

    // create favourite board
    public FavouriteBoards saveFavouriteBoard(FavouriteBoards fb) {
        return favouriteBoardDao.save(fb); 
    }

    // this gets the default user board
    // this has return type issues. dao shoukd return just one board
    public List<?> getDefaultUserBoard(String userId) throws Exception{
        List<Object[]> resultList =  userBoardDao.queryDefaultUserBoardByUserId(userId);

        return converter.convertToDtoList(resultList);
    }

    public List<?> getAllUserBoards(String userId) throws Exception{
        return userBoardDao.queryUserBoardsByUserId(userId);
    }
}
