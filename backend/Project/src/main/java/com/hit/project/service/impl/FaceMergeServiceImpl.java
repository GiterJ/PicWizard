package com.hit.project.service.impl;

import com.hit.project.service.FaceMergeService;
import com.hit.project.utils.JSONUtil;
import org.json.JSONException;
import org.springframework.stereotype.Service;
import okhttp3.*;
import org.json.JSONObject;

import java.io.*;


import static com.hit.project.utils.BaiduUtil.getAccessToken;
import static com.hit.project.utils.BaiduUtil.getFileContentAsBase64;

@Service
public class FaceMergeServiceImpl implements FaceMergeService {
    public static final String API_KEY = "GqiC3sGRmSBCzmUATeWk2nxq";
    public static final String SECRET_KEY = "0snn0ajGDdjCCXmcztONXwm3KnKUALnl";

    static final OkHttpClient HTTP_CLIENT = new OkHttpClient().newBuilder().build();

    @Override
    public JSONUtil faceMerge(String target, String template) throws IOException, JSONException {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"image_target\":{\"image\":\"" + target + "\",\"image_type\":\"BASE64\",\"quality_control\":\"NONE\"},\"image_template\":{\"image\":\"" + template + "\",\"image_type\":\"BASE64\"},\"version\":\"4.0\"}");
        Request request = new Request.Builder()
                .url("https://aip.baidubce.com/rest/2.0/face/v1/merge?access_token=" + getAccessToken(API_KEY,SECRET_KEY,HTTP_CLIENT))
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        System.out.println("[INFO]:开始人脸融合");
        Response response = HTTP_CLIENT.newCall(request).execute();
        System.out.println("[INFO]:人脸融合结束");
        String res = response.body().string();
        JSONObject jsonObject = new JSONObject(res);
        int code = jsonObject.getString("error_msg").equals("SUCCESS")?200:-1;
        JSONObject resultObject = jsonObject.getJSONObject("result");
        String merge_image = code==200?resultObject.getString("merge_image"):jsonObject.getString("error_msg");
        return new JSONUtil(code,merge_image);
    }

    public static void main(String[] args) throws IOException, JSONException {
        String pic1 = "src/main/resources/FaceMergeTargetTest.jpg";
        String pic2 = "src/main/resources/FaceMergeTemplateTest.jpg";
        String target = getFileContentAsBase64(pic1,false);
        String template = getFileContentAsBase64(pic2,false);
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"image_target\":{\"image\":\"" + target + "\",\"image_type\":\"BASE64\",\"quality_control\":\"NONE\"},\"image_template\":{\"image\":\"" + template + "\",\"image_type\":\"BASE64\"},\"version\":\"4.0\"}");
        Request request = new Request.Builder()
                .url("https://aip.baidubce.com/rest/2.0/face/v1/merge?access_token=" + getAccessToken(API_KEY,SECRET_KEY,HTTP_CLIENT))
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = HTTP_CLIENT.newCall(request).execute();
        //System.out.println(response.body().string());
        String res = response.body().string();
        //System.out.println(res);
        JSONObject jsonObject = new JSONObject(res);
        int code = jsonObject.getString("error_msg").equals("SUCCESS")?200:-1;
        JSONObject resultObject = jsonObject.getJSONObject("result");
        String merge_image = code==200?resultObject.getString("merge_image"):jsonObject.getString("error_msg");
        JSONUtil jsonUtil = new JSONUtil(code,merge_image);
        System.out.println(jsonUtil);
    }


}