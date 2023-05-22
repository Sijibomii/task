package com.task.server.dao;

import com.task.server.entity.Projects;

import java.util.List;
import java.util.Map;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.stereotype.Repository;
import com.task.server.dao.base.BaseDao;

@Repository
public interface ProjectDao extends BaseDao<Projects> {
    
    // wrote the sql myself to avoid qurerydsl problems
    @Query("SELECT p.id AS project_id, p.name AS project_name, c.id AS category_id, c.title AS category_title FROM project p LEFT JOIN project_categories pc ON pc.project_id = p.id LEFT JOIN category c ON c.id = pc.category_id WHERE p.user_id=?1")
    List<Map<String, Object>> allProjectsByCatergoryUserId(String user_id);

    @Query("")
    List<Map<String, Object>> projectBoardDetails(String board_id);
}
