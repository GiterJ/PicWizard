package com.hit.project.service.impl;

import com.hit.project.entity.Picture;
import com.hit.project.mapper.PictureDao;
import com.hit.project.service.SinglePicService;
import com.hit.project.utils.JSONUtil;
import jakarta.annotation.Resource;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;

import static com.hit.project.utils.BaiduUtil.getAccessToken;
@Service
public class SinglePicServiceImpl implements SinglePicService {
//    @Resource
//    private PictureDao pictureDao;
    public static final String API_KEY = "mtWpzs7n33HQ0KEWdrdhZs7W";
    public static final String SECRET_KEY = "hx5dn8SWP38kNKGIig3YRopTiPzD3zZB";

    static final OkHttpClient HTTP_CLIENT = new OkHttpClient().newBuilder().build();
    @Override
    public JSONUtil singlePicOp(String image, String url, String op, String name, PictureDao pictureDao) throws IOException, JSONException {
        byte[] decodedImage = Base64.getDecoder().decode(image);
        String base64Image = Base64.getEncoder().encodeToString(decodedImage);
        image = URLEncoder.encode(base64Image, StandardCharsets.UTF_8);
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "image=" + image);
        Request request = new Request.Builder()
                .url(url + getAccessToken(API_KEY, SECRET_KEY, HTTP_CLIENT))
                .method("POST", body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Accept", "application/json")
                .build();
        System.out.println("[INFO]:图像操作" + op + "开始");
        Response response = HTTP_CLIENT.newCall(request).execute();
        System.out.println("[INFO]:图像操作" + op + "结束");
        String res = response.body().string();
        JSONObject jsonObject = new JSONObject(res);
        if(!jsonObject.has("image")){
            System.out.println("[INFO]:服务器未响应或者图片格式错误");
            return new JSONUtil(-1,"服务器未响应或图片格式错误");
        }
        pictureDao.insert(new Picture(name, LocalDateTime.now(),op,jsonObject.getString("image")));
        return new JSONUtil(200,jsonObject.getString("image"));
    }
}
