package com.task.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.server.dao.TaskBoardDao;
import com.task.server.dto.TaskBoardCreateDto;
import com.task.server.entity.TaskBoards;

@Service
public class TaskBoardService {

    @Autowired
    private TaskBoardDao taskBoardDao;
    
    public TaskBoards save(TaskBoards tb) {
        return taskBoardDao.save(tb); 
    }

    public TaskBoards create(TaskBoardCreateDto taskBoard) throws Exception { 
        TaskBoards tBoards = new TaskBoards();
        // tBoards.setProject(taskBoard.getProject());
        tBoards.setCreator(taskBoard.getCreator());
        tBoards.set_private(false);
        return save(tBoards);
    }

    public TaskBoards findById(String id){
        if (taskBoardDao.findById(Long.parseLong(id)).get() == null){
            return null;
        }
        return taskBoardDao.findById(Long.parseLong(id)).get();
    }
}
