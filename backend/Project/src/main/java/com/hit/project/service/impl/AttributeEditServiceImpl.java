package com.hit.project.service.impl;

import com.hit.project.service.AttributeEditService;
import com.hit.project.utils.JSONUtil;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import okhttp3.*;

import java.io.IOException;

import static com.hit.project.utils.BaiduUtil.getAccessToken;
import static com.hit.project.utils.BaiduUtil.getFileContentAsBase64;


@Service
public class AttributeEditServiceImpl implements AttributeEditService {
    public static final String API_KEY = "GqiC3sGRmSBCzmUATeWk2nxq";
    public static final String SECRET_KEY = "0snn0ajGDdjCCXmcztONXwm3KnKUALnl";

    static final OkHttpClient HTTP_CLIENT = new OkHttpClient().newBuilder().build();
    @Override
    public JSONUtil AttributeEdit(String image,String prompt) throws IOException, JSONException {
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
        System.out.println("[INFO]:result:"+jsonObject);
        int code = jsonObject.getString("error_msg").equals("SUCCESS")?200:-1;
        JSONObject resultObject = jsonObject.getJSONObject("result");
        String imageEdit = code==200?resultObject.getString("image"):jsonObject.getString("error_msg");
        return new JSONUtil(code,imageEdit);
    }

    public static void main(String[] args) throws JSONException, IOException {
        String pic1 = "src/main/resources/FaceMergeTargetTest.jpg";
        String image = getFileContentAsBase64(pic1,false);
        String prompt = "TO_OLD";
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
        System.out.println(code);
        JSONObject resultObject = jsonObject.getJSONObject("result");
        String merge_image = code==200?resultObject.getString("image"):jsonObject.getString("error_msg");
    }
}
