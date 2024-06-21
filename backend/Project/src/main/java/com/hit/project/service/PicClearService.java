package com.hit.project.service;

import com.hit.project.utils.JSONUtil;
import org.json.JSONException;

import java.io.IOException;

public interface PicClearService {
    public JSONUtil picClear(String image) throws IOException, JSONException;
}
