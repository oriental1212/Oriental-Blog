package com.oriental.backend.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Article {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String TIME_ZONE = "GMT+8";

    //文章id
    private int articleid;
    //用户id
    private int userid;
    //文章标题
    private String articletitle;
    //发表日期
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern=DATETIME_FORMAT, timezone =TIME_ZONE)
    private Date articleDate;
    //分类id
    private int sortid;
}
