package com.hit.project.service;
import com.hit.project.utils.JSONUtil;

public interface UserService {
    JSONUtil login(String name, String password);
    JSONUtil regis(String name, String password);
}
