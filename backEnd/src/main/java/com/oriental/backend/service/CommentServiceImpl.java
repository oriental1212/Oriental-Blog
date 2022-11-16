package com.oriental.backend.service;

import com.oriental.backend.dao.CommentDao;
import com.oriental.backend.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentDao commentDao;

    @Override
    public List<Comment> selectAllComment() {
        return commentDao.selectAllComment();
    }
    @Override
    public int deleteByCommentidInt(int commentid){
        return commentDao.deleteByCommentidInt(commentid);
    }

    @Override
    public List<Comment> selectCommentByName(String commentcontent) {
        return commentDao.selectCommentByName(commentcontent);
    }

    @Override
    public int selectAllCount(){
        return commentDao.selectAllCount();
    }
}
