package com.task.server.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.task.server.dao.ProjectDao;

@Service
public class ProjectService {

    @Autowired
    private ProjectDao projectDao;
    
    // get all projects by user id left join the categories to
    public List<Map<String, Object>> getAllProjectsByUserId(String user_id) throws Exception { 
       return projectDao.allProjectsByCatergoryUserId(user_id);
    }

    public List<Map<String, Object>> getBoardDetails(String project_id) throws Exception {
        return projectDao.projectBoardDetails(project_id);
    }
}
