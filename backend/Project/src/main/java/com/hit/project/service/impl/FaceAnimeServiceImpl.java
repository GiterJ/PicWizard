package com.hit.project.service.impl;

import com.hit.project.service.FaceAnimeService;
import com.hit.project.utils.JSONUtil;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URLEncoder;
import java.util.Base64;

import static com.hit.project.utils.BaiduUtil.getAccessToken;
import static com.hit.project.utils.BaiduUtil.getFileContentAsBase64;
@Service
public class FaceAnimeServiceImpl implements FaceAnimeService {
    public static final String API_KEY = "mtWpzs7n33HQ0KEWdrdhZs7W";
    public static final String SECRET_KEY = "hx5dn8SWP38kNKGIig3YRopTiPzD3zZB";

    static final OkHttpClient HTTP_CLIENT = new OkHttpClient().newBuilder().build();
    @Override
    public JSONUtil faceAnime(String image) throws IOException, JSONException {
        byte[] decodedImage = Base64.getDecoder().decode(image);
        String base64Image = Base64.getEncoder().encodeToString(decodedImage);
        image = URLEncoder.encode(base64Image, "utf-8");
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "image="+image);
        Request request = new Request.Builder()
                .url("https://aip.baidubce.com/rest/2.0/image-process/v1/selfie_anime?access_token=" + getAccessToken(API_KEY,SECRET_KEY,HTTP_CLIENT))
                .method("POST", body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Accept", "application/json")
                .build();
        System.out.println("[INFO]:动漫化开始");
        Response response = HTTP_CLIENT.newCall(request).execute();
        System.out.println("[INFO]:动漫化结束");
        String res = response.body().string();
        if(res!=null){
            JSONObject jsonObject = new JSONObject(res);
            System.out.println("[INFO]:jsonObject:"+jsonObject);
            return new JSONUtil(200,jsonObject.getString("image"));
        }
        return new JSONUtil(-1,"服务器未响应");
    }
    public static void main(String []args) throws IOException, JSONException {
        String pic1 = "src/main/resources/FaceMergeTemplateTest.jpg";
        String image = getFileContentAsBase64(pic1,true);
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "image="+image);
        Request request = new Request.Builder()
                .url("https://aip.baidubce.com/rest/2.0/image-process/v1/selfie_anime?access_token=" + getAccessToken(API_KEY,SECRET_KEY,HTTP_CLIENT))
                .method("POST", body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Accept", "application/json")
                .build();
        Response response = HTTP_CLIENT.newCall(request).execute();
        System.out.println(response.body().string());
    }
}
