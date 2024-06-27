package com.hit.project.service;

import com.hit.project.utils.JSONUtil;
import org.json.JSONException;

public interface BeautyService {
    JSONUtil beauty(String image, String name) throws JSONException;
}
