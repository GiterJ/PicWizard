package com.hit.project.service;

import com.hit.project.utils.JSONUtil;
import org.json.JSONException;

import java.io.IOException;

public interface PicQAService {
    void sendRequest(String image, String question) throws IOException, JSONException;

    JSONUtil getResponse() throws IOException, JSONException, InterruptedException;
}
