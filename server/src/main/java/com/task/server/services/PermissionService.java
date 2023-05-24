package com.task.server.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.task.server.dao.PermissionsDao;
import com.task.server.entity.Permissions;
@Service
public class PermissionService {
    
    @Autowired
    private PermissionsDao permissionsDao;

    public List<Permissions> findAll() {
        return permissionsDao.findAll();
    }

    public List<Permissions> findUserPermissions(UUID user_id) {
        return permissionsDao.findAllPermissionsByUserId(user_id);
    }
}
