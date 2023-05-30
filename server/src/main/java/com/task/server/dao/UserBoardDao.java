package com.task.server.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.task.server.dao.base.BaseDao;
import com.task.server.entity.UserBoards;


@Repository
public interface UserBoardDao extends BaseDao<UserBoards> {
    
    @Query(value="""
        SELECT 
        ub.id AS id,
        c.id AS category_id, 
        c.title AS category_title
        t.id AS task_id
        t.description AS task_description
        t.heading AS task_heading
        t.comment_count AS no_of_comment
        t.assignee_count AS no_of_assignee
        tg.name AS tag_name
        tg.id AS tag_id
        FROM user_boards ub
        LEFT JOIN category c ON c.user_board_id = ub.id
        LEFT JOIN task_categories tc ON tc.category_id = c.id
        LEFT JOIN tasks t on t.id = tc.id
        LEFT JOIN task_tags ttg on ttg.task_id = t.id
        LEFT JOIN tags tg on tg.id = ttg.tag_id
        WHERE ub.user_id = ?1 AND ub.is_default = true
            """, nativeQuery = true)
    List<UserBoards> queryDefaultUserBoardByUserId(String userId);

    @Query(value="""
        SELECT 
        ub.id AS id,
        c.id AS category_id, 
        c.title AS category_title
        t.id AS task_id
        t.description AS task_description
        t.heading AS task_heading
        t.comment_count AS no_of_comment
        t.assignee_count AS no_of_assignee
        tg.name AS tag_name
        tg.id AS tag_id
        FROM user_boards ub
        LEFT JOIN category c ON c.user_board_id = ub.id
        LEFT JOIN task_categories tc ON tc.category_id = c.id
        LEFT JOIN tasks t on t.id = tc.id
        LEFT JOIN task_tags ttg on ttg.task_id = t.id
        LEFT JOIN tags tg on tg.id = ttg.tag_id
        WHERE ub.user_id = ?1
            """, nativeQuery = true)
    List<UserBoards> queryUserBoardsByUserId(String userId);
}
