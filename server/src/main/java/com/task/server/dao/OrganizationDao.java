package com.task.server.dao;

import org.springframework.stereotype.Repository;

import com.task.server.dao.base.BaseDao;
import com.task.server.entity.Organizations;

@Repository
public interface OrganizationDao extends BaseDao<Organizations> {
    
}
