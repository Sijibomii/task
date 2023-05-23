package com.task.server.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.task.server.dao.ProjectDao;
import com.task.server.dto.ProjectCreateDto;
import com.task.server.dto.TaskBoardCreateDto;
import com.task.server.entity.Projects;
import com.task.server.enums.BoardType;

@Service
public class ProjectService {

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private TaskBoardService tBoardService;
    
    // get all projects by user id left join the categories to
    public List<Map<String, Object>> getAllProjectsByUserId(String user_id) throws Exception { 
       return projectDao.allProjectsByCatergoryUserId(user_id);
    }

    public List<Map<String, Object>> getBoardDetails(String project_id) throws Exception {
        return projectDao.projectBoardDetails(project_id);
    }

    public Projects create(ProjectCreateDto project) throws Exception { 
        Projects new_project = new Projects();
        new_project.setCreator(project.getCreator());
        new_project.setDescription(project.getDescription());
        new_project.setLabel(project.getLabel());
        Projects saved = save(new_project);

        // create a default board task board for the project
        TaskBoardCreateDto new_tboard = new TaskBoardCreateDto(project.getCreator(), false, saved, BoardType.TASKBOARD);
        tBoardService.create(new_tboard);

        return saved; 
    }

    public Projects save(Projects project) {
        return projectDao.save(project); 
    }
}
