package com.oriental.backend.utils;

import com.oriental.backend.pojo.Article;
import lombok.Data;

@Data
public class allArticleUtils {
    private Article article;
    private String sortname;

    public static allArticleUtils getAll(Article article,String sortname){
        allArticleUtils articleUtils = new allArticleUtils();
        articleUtils.setArticle(article);
        articleUtils.setSortname(sortname);
        return articleUtils;
    }
}
