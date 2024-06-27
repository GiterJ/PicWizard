package com.hit.project.service;

import com.hit.project.utils.JSONUtil;
import org.json.JSONException;

import java.io.IOException;

public interface AttributeEditService {
    JSONUtil AttributeEdit(String image, String prompt, String name) throws IOException, JSONException;
}
