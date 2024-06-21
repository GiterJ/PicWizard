package com.hit.project.controller;

import com.hit.project.entity.User;
import com.hit.project.mapper.UserDao;
import com.hit.project.service.UserService;
import com.hit.project.utils.JSONUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Resource
    private UserDao userDao;
    @Autowired
    private UserService loginService;
    @RequestMapping("/testforlogin")
    public List<User> findAll(){
        return userDao.selectAll();
    }
    @PostMapping("/login")
    public JSONUtil login(@RequestParam("name")String name,@RequestParam("password")String password) {
        return loginService.login(name, password);
    }
    @PostMapping("/regis")
    public JSONUtil regis(@RequestParam("name")String name, @RequestParam("password")String password) {
        return loginService.regis(name,password);
    }

}
