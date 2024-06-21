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
                return new JSONUtil(200, "登陆成功");
            }
        }
        return new JSONUtil(-1, "用户名或密码错误");
    }

    @Override
    public JSONUtil regis(String name, String password) {
        List<User> users = userDao.selectAll();
        for (User user : users) {
            if (user.getName().equals(name)) {
                return new JSONUtil(-1, "用户名重复");
            }
        }
        userDao.insert(new User(name,password));

        return new JSONUtil(200,"注册成功");
    }

}
