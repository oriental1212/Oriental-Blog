package com.oriental.backend.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Sort {
    //分类id
    private int sortid;
    //分类名字
    private String sortname;
    //分类描述
    private String sortdescription;
}
