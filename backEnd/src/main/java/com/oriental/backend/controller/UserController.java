package com.oriental.backend.controller;
import com.oriental.backend.confirgure.Result;
import com.oriental.backend.dao.ArticleDao;
import com.oriental.backend.pojo.User;
import com.oriental.backend.service.ArticleService;
import com.oriental.backend.service.CommentService;
import com.oriental.backend.service.SortService;
import com.oriental.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentService commentService;

    //登录
    @PostMapping("/user/login")
    public Result getUser(@RequestBody User user){
        if(user.getUsername() == null || user.getUserpassword() == null){
            throw new Error("参数错误");
        }
        if(userService.getUser(user.getUsername(),user.getUserpassword()) != null){
            return Result.success();
        }
        return null;
    }
    //注册
    @PostMapping("/user/register")
    public Result Register(@RequestBody User user){
        if(user.getUsername() == null || user.getUserpassword() == null){
            throw new Error("参数错误");
        }
        if(userService.getUser(user.getUsername(),user.getUserpassword()) != null){
            return Result.error("用户已注册");
        }
        if(userService.insertUser(user.getUsername(),user.getUserpassword()) != 0){
            return Result.success();
        }
        return null;
    }
    @PutMapping("/user/update")
    public Result updateUserByID(@RequestBody User user){
        return Result.success(userService.updateUserByID(user));
    }

    @GetMapping("/user/selectuserbyname/{username}")
    public Result selectUserByName(@PathVariable String username){
        return Result.success(userService.selectUserByName(username));
    }

    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file) throws FileNotFoundException {
        String filename = file.getOriginalFilename();//获得文件原名 如：abc.txt
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        String pathStr = path.getAbsolutePath();
        File f = new File(pathStr, "static/upload");
        //将要被储存的对象
        File target = new File(f.getAbsolutePath() +'/' + filename);
        //目标文件如果已经存在说明文件重名了，需要另起一个唯一的名字
        if (target.exists()){
            int i = filename.lastIndexOf('.');
            String originName = filename.substring(0, i);//文件名字 abc
            String suffix = filename.substring(i, filename.length()); //文件后缀 .txt
            target = new File(f.getAbsolutePath() +'/' + originName + UUID.randomUUID() + suffix);
        }
        //创建目标文件夹，此时目标文件肯定不存在
        target.getParentFile().mkdir();
        try {//储存过程
            file.transferTo(target);
            //返回目标文件的绝对路径
            return Result.success(target.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.success("上传成功");
    }

    @GetMapping("/getcount")
    public Result getAllCount() {
        List<Object> list = new ArrayList<>();
        list.add(articleService.selectAllCount());
        list.add(commentService.selectAllCount());
        return Result.success(list);
    }
}
