package com.task.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.server.dao.OrganizationDao;
import com.task.server.entity.Organizations;
import com.task.server.entity.Users;

@Service
public class OrganizationService {
    
    @Autowired
    private UserService userService;

    @Autowired
    private OrganizationDao orgDao;

    public Organizations createOrganization(String user_id, String description) throws Exception{
        Organizations org = new Organizations();

        Users user = userService.findById(user_id);
        user.getMemeberships().add(org);
        org.setCreator(user);
        org.setDescription(description);
        org.setMembers_count(1);
        org.getMembers().add(user);

        orgDao.save(org);
        
        return org;
    }   
}
