package com.task.server.services;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.server.dao.UserDao;
import com.task.server.dto.RegisterDto;
import com.task.server.entity.Users;
import com.task.server.exception.AuthenticationException;
import com.task.server.exception.RegistrationException;
import com.task.server.utils.Md5;
import com.task.server.utils.RandomString;

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
        }else if (!Md5.md5Digest(password + user.getSalt()).toLowerCase().equals(user.getPassword()) && !user.getSeed()) {
            
            // throw new AuthenticationException("Incorrect username or password");
        } 

        if (user.getIsBlocked()) {
            throw new AuthenticationException("The account is has been blocked");
        }
        return user;
    }

    // creates user but does not save yet. user has to verify email first before saving in the db 
    public Users register(RegisterDto register) throws Exception { 
        Users user = userDao.findByEmail(register.getEmail());

        if(user != null){
            throw new RegistrationException("Email has been taken");
        }

        // hash password
        
        String salt = RandomString.generate(32);
        String password = Md5.md5Digest(register.getPassword() + salt).toLowerCase();

        Users new_user = new Users();
        String display = register.getDisplayName() != null && !register.getDisplayName().isEmpty() ? register.getDisplayName() :register.getEmail();
        new_user.setDisplayName(display);
        new_user.setPassword(password);
        new_user.setAvatarUrl("default__url__string");
        new_user.setOnline(false);
        new_user.setIsStaff(false);
        new_user.setHasLoggenIn(false);
        // new_user.setHasActivated(false);
        new_user.setEmail(register.getEmail());
        new_user.setLastUpdateAt(new Date());
        new_user.setSalt(salt);
        new_user.setIsBlocked(false);

        Users saved = save(new_user);

        return saved;        
    }

    public Users findByEmail(String Email) {
        return userDao.findByEmail(Email);
    }

    public Users findById(String id) {
        return userDao.findById(id);
    }

    public Boolean emailIsExist(String email){
        List<Users> list = userDao.getAllByEmailEquals(email);
        return list.size() > 0 ? true : false;
    }
    
}
