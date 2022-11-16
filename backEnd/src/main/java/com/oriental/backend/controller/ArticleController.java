package com.oriental.backend.controller;

import com.oriental.backend.confirgure.Result;
import com.oriental.backend.pojo.Article;
import com.oriental.backend.pojo.newArticle;
import com.oriental.backend.service.ArticleService;
import com.oriental.backend.service.SortService;
import com.oriental.backend.service.UserService;
import com.oriental.backend.utils.writeOnArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.oriental.backend.utils.allArticleUtils;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private SortService sortService;
    @Autowired
    private UserService userService;
    @Autowired
    private writeOnArticle writeOnArticle;

    @GetMapping("/article/allarticle")
    public Result allarticle(){
        return Result.success(articleService.allArticle());
    }
    @GetMapping("/article/findallarticle")
    public Result findAllArticle(){
        List<Article> articles = articleService.allArticle();
        List<Object> list = new ArrayList<>();
        for (Article article : articles) {
            String sortNameById = sortService.findSortNameById(article.getSortid());
            allArticleUtils all = allArticleUtils.getAll(article, sortNameById);
            list.add(all);
        }
        return Result.success(list);
    }
    @DeleteMapping("/article/deletebyarticletitleint/{articlename}")
    public Result deleteByArticletitleInt(@PathVariable String articlename){
        if(articleService.deleteByArticletitleInt(articlename) == 1){
            return Result.success();
        }
        return Result.error("删除失败");
    }
    //写入文章
    @PutMapping("/article/newarticle")
    public Result newWriteOnArticle (@RequestBody newArticle newarticle) throws ParseException, IOException {
        System.out.println(newarticle);
        //用户ID
        int userid = userService.selectUsernameById(newarticle.getUsername());
        //时间
        LocalDateTime time = LocalDateTime.parse(newarticle.getArticleDate(),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        articleService.insertArticle(userid,time,newarticle);
        //获取articleid
        int articleid = articleService.selectArticleIdByDate(time);
        writeOnArticle.writerArticle(newarticle.getArticletext(),newarticle.getArticleDate().replace(":","-"));
        return Result.success();
    }
    //读出文章
    @GetMapping("/article/getatricle/{date}")
    public Result selectArticleByDate(@PathVariable String date) throws IOException {
        List<Object> list = new ArrayList<>();
        list.add(writeOnArticle.readArticle(date.replace(":","-")));
        LocalDateTime time = LocalDateTime.parse(date,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        list.add(articleService.selectArticleTitleByTime(time));
        return Result.success(list);
    }
}
