package com.oriental.backend.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class newArticle {
    private String username;
    private String articletitle;
    private String articleDate;
    private int sortid;
    private String articletext;
}
