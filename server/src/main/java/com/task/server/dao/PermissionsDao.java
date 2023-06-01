package com.task.server.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.stereotype.Repository;

import com.task.server.dao.base.BaseDao;
import com.task.server.entity.Permissions;

@Repository
public interface PermissionsDao extends BaseDao<Permissions> {
    
    @Query("SELECT p FROM Permissions u WHERE p.user.id = :userId")
    List<Permissions> findAllPermissionsByUserId(UUID userId);
}
