package com.oriental.backend.utils;

import com.oriental.backend.pojo.Article;
import com.oriental.backend.pojo.Comment;
import lombok.Data;

@Data
public class allCommentUtils {
    private Comment comment;
    private String articlename;

    public static allCommentUtils getAll(Comment comment,String articlename){
        allCommentUtils allCommentUtils = new allCommentUtils();
        allCommentUtils.setComment(comment);
        allCommentUtils.setArticlename(articlename);
        return allCommentUtils;
    }
}
