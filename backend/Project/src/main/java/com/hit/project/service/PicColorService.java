package com.hit.project.service;

import com.hit.project.utils.JSONUtil;
import org.json.JSONException;

import java.io.IOException;

public interface PicColorService {

    JSONUtil picColor(String image, String name) throws IOException, JSONException;
}
