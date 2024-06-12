package com.hit.project.service.impl;

import com.hit.project.service.PicQAService;
import com.hit.project.utils.JSONUtil;
import org.json.JSONException;
import org.springframework.stereotype.Service;
import okhttp3.*;
import org.json.JSONObject;

import java.io.*;

import static com.hit.project.utils.BaiduUtil.getAccessToken;
import static com.hit.project.utils.BaiduUtil.getFileContentAsBase64;
import static java.lang.Thread.sleep;

@Service
public class PicQAServiceImpl implements PicQAService {
    private String taskId;
    public static final String API_KEY = "qWvYogr7Gcvy8ncbIjOhPPsZ";
    public static final String SECRET_KEY = "eAXgxOzgk3x3KhiU6qXgqVq1JzMZWjiq";

    static final OkHttpClient HTTP_CLIENT = new OkHttpClient().newBuilder().build();

    @Override
    public void sendRequest(String image, String question) throws IOException, JSONException {
        //String baseImage = getFileContentAsBase64(image,false);
        //image = image.replace(" ","+");
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/test.txt"))) {
//            writer.write(image);
//            System.out.println("Picture_Base64 has been written to the file successfully.");
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("An error occurred while writing to the file.");
//        }

//        String test = getFileContentAsBase64("src/main/resources/PicQATest.jpg",false);
//        System.out.println("[INFO]:传入的参数和正确参数对比结果:"+test.equals(image));

        //image = getFileContentAsBase64("src/main/resources/PicQATest.jpg",false);

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(
                mediaType, "{\"image\":"+"\""+image+"\""+
                ",\"question\":"+"\""+question+"\""+
                ",\"output_CHN\":true}"
        );
        Request request = new Request.Builder()
                .url("https://aip.baidubce.com/rest/2.0/image-classify/v1/image-understanding/request?access_token=" + getAccessToken(API_KEY,SECRET_KEY,HTTP_CLIENT))
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = HTTP_CLIENT.newCall(request).execute();
        String res = response.body().string();
        if(res!=null){
            JSONObject jsonObject = new JSONObject(res);
            // 获取result对象
            JSONObject resultObject = jsonObject.getJSONObject("result");
            // 获取task_id的值
            this.taskId = resultObject.getString("task_id");
            System.out.println("[INFO]:当前处理的任务的id为:"+this.taskId);
        }else {
            this.taskId = "error";
        }
    }
    @Override
    public JSONUtil getResponse() throws IOException, JSONException, InterruptedException {
        if(this.taskId.equals("error")){
            return new JSONUtil(-1,"服务器未响应或图片格式不正确");
        }
        String description;
        int code;
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"task_id\":\""+ this.taskId +"\"}");
        Request request = new Request.Builder()
                .url("https://aip.baidubce.com/rest/2.0/image-classify/v1/image-understanding/get-result?access_token=" + getAccessToken(API_KEY,SECRET_KEY,HTTP_CLIENT))
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        while(true){
            Response response = HTTP_CLIENT.newCall(request).execute();
            String res = response.body().string();
            JSONObject jsonObject = new JSONObject(res);
            JSONObject resultObject = jsonObject.getJSONObject("result");
            if(resultObject.getString("ret_msg").equals("processing")){
                System.out.println("[INFO]:processing...");
                sleep(5000);
            }
            else if (resultObject.getString("ret_msg").equals("success")){
                code = 200;
                System.out.println("[INFO]:success");
                description = resultObject.getString("description");
                System.out.println("[INFO]:图片描述:"+description);
                break;
            }
            else{
                code = -1;
                description = "服务器未响应";
            }
        }
        JSONUtil jsonUtil = new JSONUtil(code,description);
        return jsonUtil;
    }
    public static void main(String[] args) throws JSONException, IOException, InterruptedException {
        //PicQAServiceImpl picQAService = new PicQAServiceImpl();
        //picQAService.sendRequest("src/main/resources/PicQATest.jpg","图中的人物在干啥");
//        String base = getFileContentAsBase64("src/main/resources/PicQATest.jpg",false);
//        System.out.println(base);
        //picQAService.getResponse();


    }
}
