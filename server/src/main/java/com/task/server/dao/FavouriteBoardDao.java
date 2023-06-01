package com.task.server.dao;

import org.springframework.stereotype.Repository;
import com.task.server.dao.base.BaseDao;
import com.task.server.entity.FavouriteBoards;
import org.springframework.data.repository.query.Param;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

@Repository
public interface FavouriteBoardDao extends BaseDao<FavouriteBoards>{
    
    @Query(value = "select fb from FavouriteBoards fb where fb.creator.id = :userId") 
    List<FavouriteBoards> queryFavouriteBoardsByUserId(@Param("userId") String userId);
}
