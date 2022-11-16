package com.oriental.backend.service;

import com.oriental.backend.dao.ArticleDao;
import com.oriental.backend.pojo.Article;
import com.oriental.backend.pojo.newArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService{
    @Autowired
    private ArticleDao articleDao;

    @Override
    public List<Article> allArticle() {
        return articleDao.allArticle();
    }
    @Override
    public int deleteByArticletitleInt(String articlename){
        return articleDao.deleteByArticletitleInt(articlename);
    };

    @Override
    public String selectArticleNameById(int articleid) {
        return articleDao.selectArticleNameById(articleid);
    }

    @Override
    public int insertArticle(int id, LocalDateTime time, newArticle newarticle) {
        return articleDao.insertArticle(id,time,newarticle);
    }

    @Override
    public int selectArticleIdByDate(LocalDateTime time) {
        return articleDao.selectArticleIdByDate(time);
    }

    @Override
    public String selectArticleTitleByTime(LocalDateTime time){
        return articleDao.selectArticleTitleByTime(time);
    }

    @Override
    public int selectAllCount(){
        return articleDao.selectAllCount();
    }
}
