package com.oriental.backend.dao;

import com.oriental.backend.pojo.Article;
import com.oriental.backend.pojo.newArticle;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
@Repository
public interface ArticleDao {
    List<Article> allArticle();
    int deleteByArticletitleInt(String articlename);
    String selectArticleNameById(int articleid);
    int insertArticle(int id, LocalDateTime time, newArticle newarticle);
    int selectArticleIdByDate(LocalDateTime time);
    String selectArticleTitleByTime(LocalDateTime time);
    int selectAllCount();
}
