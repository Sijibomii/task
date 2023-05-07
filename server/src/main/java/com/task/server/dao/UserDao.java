package com.task.server.dao;

import com.task.server.entity.Users;
import org.springframework.stereotype.Repository;
import com.task.server.dao.base.BaseDao;

@Repository
public interface UserDao extends BaseDao<Users> {
     
    Users findByEmail(String email);
    
}
