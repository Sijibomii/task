package com.task.server.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.task.server.dao.TaskDao;

@Service
public class TaskService {
    
    @Autowired
    private TaskDao taskDao;

    // find task by task id: add comments too
    public Object getTaskAndCommentByTaskId(String task_id) throws Exception { 
        return taskDao.queryTaskByTaskIdForTaskAndComments(task_id);
     }
}
