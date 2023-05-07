package com.task.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.server.dao.UserDao;
import com.task.server.entity.Users;
import com.task.server.exception.AuthenticationException;
import com.task.server.utils.Md5;

@Service
public class UserService {
    
    @Autowired
    private UserDao userDao;

    public Users save(Users user) {
        return userDao.save(user);
    }

    public Users login(String email, String password) throws Exception { 
        Users user = userDao.findByEmail(email);
        if (user == null) {
            throw new AuthenticationException("Incorrect username or password");
        } else if (!Md5.md5Digest(password + user.getSalt()).toLowerCase().equals(user.getPassword())) {
            throw new AuthenticationException("Incorrect username or password");
        } 

        if (user.getIsBlocked()) {
            throw new AuthenticationException("The account is has been blocked");
        }
        return user;
    }

    public Users findByEmail(String Email) {
        return userDao.findByEmail(Email);
    }

    
}
