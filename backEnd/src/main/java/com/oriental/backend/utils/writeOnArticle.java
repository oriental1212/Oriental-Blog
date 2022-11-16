package com.oriental.backend.utils;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.IOException;

import java.io.*;

@Component
public class writeOnArticle {
    @javax.annotation.Resource
    ResourceLoader resourceLoader;

    public void writerArticle(String text, String date) throws IOException {
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        String pathStr = path.getAbsolutePath();
        File file = new File(pathStr, "static/text/" + date + ".md");
        //file.createNewFile();
        String ResourcePath = file.getAbsolutePath();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ResourcePath)));
        StringBuffer buffer = new StringBuffer();
        buffer.append(text);
        writer.write(buffer.toString());
        writer.flush();
        writer.close();
    }
    //读文件
    public String readArticle(String date) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:/static/text/" + date + ".md");
        InputStream is = resource.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String text;
        while((text = br.readLine()) != null) {
            return text;
        }
        br.close();
        isr.close();
        is.close();
        return null;
    }

}
