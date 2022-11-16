package com.oriental.backend.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Comment {
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String TIME_ZONE = "GMT+8";

    private int commentid;
    private int articleid;
    private String commentcontent;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern=DATETIME_FORMAT, timezone =TIME_ZONE)
    private Date commentDate;
}
