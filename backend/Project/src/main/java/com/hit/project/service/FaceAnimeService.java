package com.hit.project.service;

import com.hit.project.utils.JSONUtil;
import org.json.JSONException;

import java.io.IOException;

public interface FaceAnimeService {
    JSONUtil faceAnime(String image, String name) throws IOException, JSONException;
}
