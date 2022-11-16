package com.oriental.backend.service;

import com.oriental.backend.dao.UserDao;
import com.oriental.backend.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class userServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    public User getUser(String username, String userpassword){
        return userDao.getUser(username,userpassword);
    }

    public int insertUser(String username,String userpassword){
        return  userDao.insertUser(username,userpassword);
    };

    public int updateUserByID(User user){return userDao.updateUserByID(user);};

    public User selectUserByName(String username){return userDao.selectUserByName(username);}

    @Override
    public int selectUsernameById(String username) {
        return userDao.selectUsernameById(username);
    }
}
