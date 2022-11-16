package com.oriental.backend.dao;

import com.oriental.backend.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {
    User getUser(String username, String userpassword);
    int insertUser(String username,String userpassword);
    int updateUserByID(@Param("user")User user);
    User selectUserByName(String username);
    int selectUsernameById(String username);
}
