package com.task.server.dao;

import com.task.server.entity.Events;

import org.springframework.stereotype.Repository;

import com.task.server.dao.base.BaseDao;

@Repository
public interface EventDao extends BaseDao<Events> {
    
}
