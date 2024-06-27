package com.hit.project.mapper;

import com.hit.project.entity.Picture;

import java.util.List;

public interface PictureDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Picture record);

    List<Picture> selectAll(String name);

    int insertSelective(Picture record);

    Picture selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Picture record);

    int updateByPrimaryKeyWithBLOBs(Picture record);

    int updateByPrimaryKey(Picture record);
}