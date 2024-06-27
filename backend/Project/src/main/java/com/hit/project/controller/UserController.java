package com.hit.project.controller;

import com.hit.project.entity.Picture;
import com.hit.project.entity.User;
import com.hit.project.mapper.UserDao;
import com.hit.project.service.ShowPicService;
import com.hit.project.service.UserService;
import com.hit.project.utils.JSONUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
    @Resource
    private UserDao userDao;
    @Autowired
    private UserService loginService;
    @Autowired
    private ShowPicService showPicService;
    @RequestMapping("/testforlogin")
    public List<User> findAll(){
        return userDao.selectAll();
    }
    //实现了登录功能
    @PostMapping("/login")
    public JSONUtil login(@RequestParam("name")String name,@RequestParam("password")String password) {
        return loginService.login(name, password);
    }
    @PostMapping("/regis")
    public JSONUtil regis(@RequestParam("name")String name, @RequestParam("password")String password) {
        return loginService.regis(name,password);
    }

    @PostMapping("/showinfo")
    public List<Picture> showInfo(@RequestParam("name")String name){
        return showPicService.showInfo(name);
    }

}
