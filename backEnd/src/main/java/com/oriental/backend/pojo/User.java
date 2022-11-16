package com.oriental.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    private int userid;
    private String username;
    private String userpassword;
    private String useremail;
}
