package com.task.server.services;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.task.server.dao.PermissionsDao;
import com.task.server.entity.Organizations;
import com.task.server.entity.Permissions;
import com.task.server.entity.Users;
@Service
public class PermissionService {
    
    @Autowired
    private PermissionsDao permissionsDao;

    public Permissions createOrgPermissions(Organizations org, Users user, com.task.server.enums.Permissions permissionType) throws Exception{

        Permissions perm = new Permissions();
        perm.setActive(true);
        perm.setCode(permissionType.getCode());
        perm.setName(permissionType.getName()+org.getId().toString());
        perm.setDescription(permissionType.getDescription());
        perm.setOrg(org);
        perm.setUser(user);
        permissionsDao.save(perm);
        return perm;
    }

    public List<Permissions> findAll() {
        return permissionsDao.findAll();
    }

    public List<Permissions> findUserPermissions(UUID user_id) {
        return permissionsDao.findAllPermissionsByUserId(user_id);
    }
}
