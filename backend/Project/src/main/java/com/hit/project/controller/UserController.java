package com.hit.project.controller;

import com.hit.project.entity.User;
import com.hit.project.mapper.UserDao;
import com.hit.project.utils.JSONUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Resource
    private UserDao userDao;
//    @RequestMapping("/test")
//    public List<User> findAll(){
//        return userDao.selectAll();
//    }
    @RequestMapping("/login")
    public JSONUtil login(@RequestParam("name")String name,@RequestParam("password")String password){
        return null;
    }
}
