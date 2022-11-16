package com.oriental.backend.service;

import com.oriental.backend.pojo.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> selectAllComment();
    int deleteByCommentidInt(int commentid);

    List<Comment> selectCommentByName(String commentcontent);

    int selectAllCount();
}
