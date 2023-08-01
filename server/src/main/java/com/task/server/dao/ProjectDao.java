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
    List<Object[]> allProjectsByCatergoryUserId(String user_id);

    @Query(value="""
        SELECT 
        pb.id AS board_id,
        c.id AS category_id, 
        c.label AS category_label,
        t.id AS task_id,
        t.description AS task_description,
        t.heading AS task_heading,
        t.comment_count AS no_of_comment,
        t.assignee_count AS no_of_assignee,
        tg.name AS tag_name,
        tg.id AS tag_id,
        ttg.tag_id AS ttg_tag_id,
        ttg.task_id AS ttg_task_id
        FROM project_boards pb
        LEFT JOIN categories c ON c.projects_board_id = pb.id
        LEFT JOIN tasks t ON t.category_id = c.id
        LEFT JOIN task_tags ttg ON ttg.task_id = t.id
        LEFT JOIN tags tg ON tg.id = ttg.tag_id
        WHERE pb.id = CAST(?1 AS uuid)
            """, nativeQuery = true)
    List<Object[]> projectBoardDetails(String board_id);

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
    List<Object[]> peoplePreviewList(String project_id);
}
