package com.task.server.dao;

import com.task.server.entity.User;
import com.task.server.dao.base.BaseDao;


public interface UserDao extends BaseDao<User> {
    
    User findByEmail(String email);
    
}
