package com.hit.project.service.impl;
import com.hit.project.entity.User;
import com.hit.project.mapper.UserDao;
import com.hit.project.service.UserService;
import com.hit.project.utils.JSONUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Override
    public JSONUtil login(String name,String password){
        List<User> users = userDao.selectAll();
        for (User user : users) {
            if (user.getName().equals(name) && user.getPassword().equals(password)) {
                System.out.println("[INFO]:登录成功");
                return new JSONUtil(200, "登陆成功");
            }
        }
        System.out.println("[INFO]:登录失败");
        return new JSONUtil(-1, "用户名或密码错误");
    }

    @Override
    public JSONUtil regis(String name, String password) {
        List<User> users = userDao.selectAll();
        System.out.println("[INFO]:注册");
        for (User user : users) {
            if (user.getName().equals(name)) {
                System.out.println("[INFO]:注册失败");
                return new JSONUtil(-1, "用户名重复");
            }
        }
        userDao.insert(new User(name,password));
        System.out.println("[INFO]:注册成功");
        return new JSONUtil(200,"注册成功");
    }

}
