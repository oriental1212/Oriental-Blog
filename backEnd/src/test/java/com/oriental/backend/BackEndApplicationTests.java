package com.oriental.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.oriental.backend.utils.writeOnArticle;

import java.io.FileNotFoundException;
import java.io.IOException;

@SpringBootTest
class BackEndApplicationTests {
    @Autowired
    private writeOnArticle writeOnArticle;

    @Test
    void contextLoads() throws IOException {
        writeOnArticle.writerArticle("1","1");
    }

}
