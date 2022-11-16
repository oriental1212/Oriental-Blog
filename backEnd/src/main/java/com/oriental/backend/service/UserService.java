package com.oriental.backend.service;

import com.oriental.backend.pojo.User;

public interface UserService {
    User getUser(String username, String userpassword);
    int insertUser(String username,String userpassword);
    int updateUserByID(User user);
    User selectUserByName(String username);
    int selectUsernameById(String username);
}
