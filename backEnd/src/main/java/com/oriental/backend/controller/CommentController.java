package com.oriental.backend.controller;

import com.oriental.backend.confirgure.Result;
import com.oriental.backend.pojo.Comment;
import com.oriental.backend.service.ArticleService;
import com.oriental.backend.service.CommentService;
import com.oriental.backend.utils.allCommentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private ArticleService articleService;

    @GetMapping("/comment/selectAllComment")
    public Result selectAllComment(){
        List<Comment> comments = commentService.selectAllComment();
        List<Object> list = new ArrayList<>();
        for (Comment comment : comments) {
            String s = articleService.selectArticleNameById(comment.getArticleid());
            allCommentUtils all = allCommentUtils.getAll(comment, s);
            list.add(all);
        }
        return Result.success(list);
    }

    @DeleteMapping("/comment/deletebycommentidint/{commentid}")
    public Result deleteByCommentidInt(@PathVariable int commentid){
        return Result.success(commentService.deleteByCommentidInt(commentid));
    }

    @GetMapping("/comment/selectcommentbyname/{commentcontent}")
    public Result selectCommentByName(@PathVariable String commentcontent){
        List<Comment> comments = commentService.selectCommentByName(commentcontent);
        List<Object> list = new ArrayList<>();
        for (Comment comment : comments) {
            String s = articleService.selectArticleNameById(comment.getArticleid());
            allCommentUtils all = allCommentUtils.getAll(comment, s);
            list.add(all);
        }
        return Result.success(list);
    }
}
