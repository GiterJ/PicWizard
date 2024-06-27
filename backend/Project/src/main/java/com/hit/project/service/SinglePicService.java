package com.hit.project.service;

import com.hit.project.mapper.PictureDao;
import com.hit.project.utils.JSONUtil;
import org.json.JSONException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public interface SinglePicService {

    public JSONUtil singlePicOp(String image, String url, String op, String name, PictureDao pictureDao) throws IOException, JSONException;
}
