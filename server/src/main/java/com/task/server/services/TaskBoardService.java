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
    
    public TaskBoards save(TaskBoards project) {
        return taskBoardDao.save(project); 
    }

    public TaskBoards create(TaskBoardCreateDto taskBoard) throws Exception { 
        TaskBoards tBoards = new TaskBoards();
        tBoards.setProject(taskBoard.getProject());
        tBoards.setCreator(taskBoard.getCreator());
        tBoards.set_private(false);
        tBoards.setBoardType(taskBoard.getBoardType());
        return save(tBoards);
    }
}
