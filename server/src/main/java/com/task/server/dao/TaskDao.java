package com.task.server.dao;
import com.task.server.entity.Tasks;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.task.server.dao.base.BaseDao;

@Repository
public interface TaskDao extends BaseDao<Tasks> {
    Tasks findById(String id);

    // find task and comments 
    // private int comment_count; private int assignee_count; private TaskStatus status; private Boolean supervised; creator; 
    // todo write join statement through task_categories confirm if comma is supposed to be there
    @Query(value="""
        SELECT 
        t.id AS id,
        t.description AS task_description,
        t.heading AS task_heading,
        c.id AS category_id, 
        c.title AS category_title,
        t.comment_count AS no_of_comment,
        t.assignee_count AS no_of_assignee,
        c.id AS creator_id,
        c.avatarUrl AS creator_avatar,
        c.displayName AS creator_display_name,
        u.displayName AS display_name,
        u.avatarUrl AS avatar_url,
        tg.name AS tag_name,
        tg.id AS tag_id
        FROM task t 
        LEFT JOIN users c on c.id = t.creator
        LEFT JOIN task_assignees ta on ta.task_id = t.id
        LEFT JOIN users u on u.id = ta.user_id
        LEFT JOIN task_categories tc ON tc.task_id = t.id
        LEFT JOIN category c ON c.id = tc.catergory_id
        LEFT JOIN task_tags ttg on ttg.task_id = t.id
        LEFT JOIN tags tg on tg.id = ttg.tag_id
        WHERE t.id = ?1
            """, nativeQuery = true)
    Object queryTaskByTaskIdForTaskAndComments(String taskId);
}
