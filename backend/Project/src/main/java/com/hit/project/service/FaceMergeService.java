package com.hit.project.service;

import com.hit.project.utils.JSONUtil;
import org.json.JSONException;

import java.io.IOException;

public interface FaceMergeService {
    JSONUtil faceMerge(String target, String template) throws IOException, JSONException;
}
