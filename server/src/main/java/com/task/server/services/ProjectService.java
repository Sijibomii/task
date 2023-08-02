package com.task.server.services;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Arrays;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.task.server.connverters.QueryProjectBoardDtoConverter;
import com.task.server.dao.ProjectDao;
import com.task.server.dto.ProjectCreateDto;
import com.task.server.dto.QueryProjectBoardDto;
import com.task.server.dto.TaskBoardCreateDto;
import com.task.server.entity.Projects;
import com.task.server.enums.BoardType;
import com.task.server.dto.FavoutieProjectsDto;

@Service
public class ProjectService {

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private TaskBoardService tBoardService;

    @Autowired
    private QueryProjectBoardDtoConverter qpbConverter;
    
    // get all projects by user id left join the categories to
    public List<FavoutieProjectsDto> getAllProjectsByUserId(String user_id) throws Exception { 
        List<Object[]> resultList = projectDao.allProjectsByTeamAndUserId(user_id);

        List<FavoutieProjectsDto> ll= resultList.stream().map((Object[] o) -> {

            UUID projectId = (o[0] != null) ? (UUID) o[0] : null;
            String label = (o[1] != null) ? (String) o[1] : ""; 
            UUID boardId = (o[2] != null) ? (UUID) o[2] : null;
           
            return new FavoutieProjectsDto(label, projectId,boardId);
        }).collect(Collectors.toList());

        return ll;
       
    }
    // QueryProjectBoardDto
    public QueryProjectBoardDto getBoardDetails(String project_id) throws Exception {

        // return projectDao.projectBoardDetails(project_id);
        List<Object[]> resultList = projectDao.projectBoardDetails(project_id);

        return qpbConverter.convertToDtoList(resultList);
    }

    public List<Object[]> getPeoplePreviewList(String project_id) throws Exception {
        return projectDao.peoplePreviewList(project_id);
    }

    public List<FavoutieProjectsDto> getUsersFavouriteProjects(String user_id) throws Exception {

        List<Object[]> resultList = projectDao.favouriteProjects(user_id);

        List<FavoutieProjectsDto> ll= resultList.stream().map((Object[] o) -> {

            UUID projectId = (o[1] != null) ? (UUID) o[1] : null;
            UUID boardId = (o[2] != null) ? (UUID) o[2] : null;
            String label = (o[0] != null) ? (String) o[0] : ""; 
            return new FavoutieProjectsDto(label, projectId,boardId);
        }).collect(Collectors.toList());

        return ll;
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
