package com.task.server.dao;

import com.task.server.entity.Projects;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.task.server.dao.base.BaseDao;

@Repository
public interface ProjectDao extends BaseDao<Projects> {
    
    // consider using jpql queries

    // wrote the sql myself to avoid qurerydsl problems
    @Query(value="SELECT p.id AS project_id, p.name AS project_name, c.id AS category_id, c.title AS category_title FROM project p LEFT JOIN project_categories pc ON pc.project_id = p.id LEFT JOIN category c ON c.id = pc.category_id WHERE p.user_id=?1", nativeQuery = true)
    List<Map<String, Object>> allProjectsByCatergoryUserId(String user_id);

    
    
    // i need a way where each tags assigned to each task should be in its obj
    // {
        // ... other task fields
    //     tags:[
    //         {
    //             ....
    //         }
    //     ]
    // }
    @Query(value="""
        SELECT 
        p.name AS name,
        c.id AS category_id, 
        c.title AS category_title,
        t.id AS task_id,
        t.description AS task_description,
        t.heading AS task_heading,
        t.comment_count AS no_of_comment,
        t.assignee_count AS no_of_assignee,
        tg.name AS tag_name,
        tg.id AS tag_id
        FROM project p
        LEFT JOIN project_categories pc ON pc.project_id = p.id
        LEFT JOIN category c ON c.id = pc.category_id
        LEFT JOIN task_categories tc ON tc.category_id = c.id
        LEFT JOIN tasks t on t.id = tc.id
        LEFT JOIN task_tags ttg on ttg.task_id = t.id
        LEFT JOIN tags tg on tg.id = ttg.tag_id
        WHERE p.id = ?1
            """, nativeQuery = true)
    List<Map<String, Object>> projectBoardDetails(String board_id);

    @Query(value="""
        SELECT 
        u.id AS id,
        u.displayName as displayName,
        u.avatarUrl as avatarUrl,
        u.online as online
        FROM project_memeberships pm
        LEFT JOIN users u ON u.id = pm.users_id
        WHERE pm.projects_id = ?1
            """, nativeQuery = true)
    List<Map<String, Object>> peoplePreviewList(String project_id);
}
