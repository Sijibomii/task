package com.task.server.dao;

import com.task.server.entity.Users;
import com.task.server.dao.base.BaseDao;


public interface UserDao extends BaseDao<Users> {
     
    Users findByEmail(String email);
    
}
