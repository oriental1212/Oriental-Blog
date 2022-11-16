package com.oriental.backend.dao;

import com.oriental.backend.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommentDao {
    List<Comment> selectAllComment();
    int deleteByCommentidInt(int commentid);
    List<Comment> selectCommentByName(String commentcontent);
    int selectAllCount();
}
