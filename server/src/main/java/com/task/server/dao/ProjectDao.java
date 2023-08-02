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
    
    @Query(value="""
        SELECT 
        u.id AS id,
        u.display_name as name,
        u.avatar_url as avatar,
        u.online as online 
        FROM projects_membership pm
        LEFT JOIN users u ON u.id = pm.users_id
        LEFT JOIN projects p ON p.id = pm.projects_id
        WHERE pm.users_id = CAST(?1 AS uuid)
            """, nativeQuery = true)
    List<Object[]> allProjectsByTeamAndUserId(String user_id);

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
        u.id AS user_id,
        u.display_name AS name,
        u.avatar_url AS avatar,
        tg.name AS tag_name,
        tg.id AS tag_id,
        ttg.tag_id AS ttg_tag_id,
        ttg.task_id AS ttg_task_id
        FROM project_boards pb
        LEFT JOIN categories c ON c.projects_board_id = pb.id
        LEFT JOIN tasks t ON t.category_id = c.id
        LEFT JOIN task_tags ttg ON ttg.task_id = t.id
        LEFT JOIN tags tg ON tg.id = ttg.tag_id
        LEFT JOIN task_assignees ta ON ta.tasks_id = t.id
        LEFT JOIN users u ON u.id = ta.users_id
        WHERE pb.id = CAST(?1 AS uuid)
            """, nativeQuery = true)
    List<Object[]> projectBoardDetails(String board_id);

    @Query(value="""
        SELECT 
        u.id AS id,
        u.display_name as name,
        u.avatar_url as avatar,
        u.online as online 
        FROM projects_membership pm
        LEFT JOIN users u ON u.id = pm.users_id
        WHERE pm.projects_id = CAST(?1 AS uuid)
            """, nativeQuery = true)
    List<Object[]> peoplePreviewList(String project_id);


    @Query(value="""
        SELECT 
        p.label AS label,
        p.id AS project_id,
        pb.id AS board_id
        FROM favourite_boards fb
        LEFT JOIN project_boards pb ON pb.id = fb.projectboard_id
        LEFT JOIN projects p ON p.id = pb.project_id
        WHERE fb.creator_id = CAST(?1 AS uuid)
            """, nativeQuery = true)
    List<Object[]> favouriteProjects(String user_id);

}
