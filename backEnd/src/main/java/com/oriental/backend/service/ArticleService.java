package com.oriental.backend.service;

import com.oriental.backend.pojo.Article;
import com.oriental.backend.pojo.newArticle;

import java.time.LocalDateTime;
import java.util.List;

public interface ArticleService {
    List<Article> allArticle();
    int deleteByArticletitleInt(String articlename);
    String selectArticleNameById(int articleid);
    int insertArticle(int id, LocalDateTime time, newArticle newarticle);
    int selectArticleIdByDate(LocalDateTime time);
    String selectArticleTitleByTime(LocalDateTime time);

    int selectAllCount();
}
