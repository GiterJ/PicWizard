package com.hit.project.service.impl;

import com.hit.project.entity.Picture;
import com.hit.project.mapper.PictureDao;
import com.hit.project.service.AttributeEditService;
import com.hit.project.utils.JSONUtil;
import jakarta.annotation.Resource;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import okhttp3.*;

import java.io.IOException;
import java.time.LocalDateTime;

import static com.hit.project.utils.BaiduUtil.getAccessToken;


@Service
public class AttributeEditServiceImpl implements AttributeEditService {
    @Resource
    private PictureDao pictureDao;
    public static final String API_KEY = "GqiC3sGRmSBCzmUATeWk2nxq";
    public static final String SECRET_KEY = "0snn0ajGDdjCCXmcztONXwm3KnKUALnl";

    static final OkHttpClient HTTP_CLIENT = new OkHttpClient().newBuilder().build();
    @Override
    public JSONUtil AttributeEdit(String image, String prompt, String name) throws IOException, JSONException {

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"image\":\""+image+"\",\"image_type\":\"BASE64\",\"action_type\":\""+prompt+"\",\"quality_control\":\"NONE\"}");
        Request request = new Request.Builder()
                .url("https://aip.baidubce.com/rest/2.0/face/v1/editattr?access_token=" + getAccessToken(API_KEY,SECRET_KEY,HTTP_CLIENT))
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        System.out.println("[INFO]:开始人脸属性编辑");
        Response response = HTTP_CLIENT.newCall(request).execute();
        System.out.println("[INFO]:人脸属性编辑结束");
        String res = response.body().string();
        JSONObject jsonObject = new JSONObject(res);
        int code = jsonObject.getString("error_msg").equals("SUCCESS")?200:-1;
        String imageEdit;
        if (code == 200) {
            JSONObject resultObject = jsonObject.getJSONObject("result");
            imageEdit = resultObject.getString("image");
        } else {
            System.out.println("[INFO]:服务器未响应或者图片格式错误");
            imageEdit = jsonObject.getString("error_msg");
        }
        //将图片加入数据库
        pictureDao.insert(new Picture(name, LocalDateTime.now(),"属性编辑",imageEdit));
        return new JSONUtil(code,imageEdit);
    }

//    public static void main(String[] args) throws JSONException, IOException {
//        String pic1 = "src/main/resources/FaceMergeTargetTest.jpg";
//        String image = getFileContentAsBase64(pic1,false);
//        String prompt = "TO_OLD";
//        MediaType mediaType = MediaType.parse("application/json");
//        RequestBody body = RequestBody.create(mediaType, "{\"image\":\""+image+"\",\"image_type\":\"BASE64\",\"action_type\":\""+prompt+"\",\"quality_control\":\"NONE\"}");
//        Request request = new Request.Builder()
//                .url("https://aip.baidubce.com/rest/2.0/face/v1/editattr?access_token=" + getAccessToken(API_KEY,SECRET_KEY,HTTP_CLIENT))
//                .method("POST", body)
//                .addHeader("Content-Type", "application/json")
//                .build();
//        System.out.println("[INFO]:开始人脸属性编辑");
//        Response response = HTTP_CLIENT.newCall(request).execute();
//        System.out.println("[INFO]:人脸属性编辑结束");
//        String res = response.body().string();
//        JSONObject jsonObject = new JSONObject(res);
//        int code = jsonObject.getString("error_msg").equals("SUCCESS")?200:-1;
//        System.out.println(code);
//        JSONObject resultObject = jsonObject.getJSONObject("result");
//        String merge_image = code==200?resultObject.getString("image"):jsonObject.getString("error_msg");
//    }
}
