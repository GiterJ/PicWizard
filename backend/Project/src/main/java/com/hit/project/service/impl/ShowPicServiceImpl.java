package com.hit.project.service.impl;

import com.hit.project.entity.Picture;
import com.hit.project.mapper.PictureDao;
import com.hit.project.service.ShowPicService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ShowPicServiceImpl implements ShowPicService {
    @Resource
    private PictureDao pictureDao;
    @Override
    public List<Picture> showInfo(String name) {
        System.out.println("[INFO]:用户名为 " + name);
        System.out.println("[INFO]:展示页面");
        return pictureDao.selectAll(name);
    }
}
