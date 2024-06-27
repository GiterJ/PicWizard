package com.hit.project.service.impl;

import com.hit.project.entity.Picture;
import com.hit.project.mapper.PictureDao;
import com.hit.project.service.BeautyService;
import com.hit.project.utils.JSONUtil;
import jakarta.annotation.Resource;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;

import static com.hit.project.utils.BaiduUtil.getFileContentAsBase64;

@Service
public class BeautyServiceImpl implements BeautyService {
    @Resource
    private PictureDao pictureDao;
    @Override
    public JSONUtil beauty(String image, String name) throws JSONException {
        OutputStreamWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        HttpURLConnection conn = null;
        try{
            System.out.println("[INFO]:美颜开始");
            URL url = new URL("https://openapi.mtlab.meitu.com/v1/beauty?api_key=2024fecb3adc42c68b24cbf42565bc81&api_secret=787adcb84fd545188a5c528ed19da9d8");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            conn.setDoOutput(true);
            conn.setDoInput(true);

            conn.setConnectTimeout(30000);
            conn.setReadTimeout(10000);
            conn.setRequestProperty("Content-Type", "application/json");

            out = new OutputStreamWriter(conn.getOutputStream());
            String jsonStr = "{\"parameter\":{\"beautyAlpha\":70},\"extra\":{},\"media_info_list\":[{\"media_data\":\""+ image +"\",\"media_profiles\":{\"media_data_type\":\"jpg\"}}]}";
            out.write(jsonStr);
            out.flush();
            out.close();

            if (200 == conn.getResponseCode()){
                in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            }else{
                System.out.println("ResponseCode is an error code:" + conn.getResponseCode());
                in = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
            }
            String line;
            while ((line = in.readLine()) != null){
                result.append(line);
                //System.out.println(line);
            }
            System.out.println("[INFO]:美颜完成");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(out != null){
                    out.close();
                }
                if(in != null){
                    in.close();
                }
            }catch (IOException ioe){
                ioe.printStackTrace();
            }
        }
        JSONObject jsonObject = new JSONObject(result.toString());
        if (jsonObject.has("media_info_list")) {
            JSONArray mediaInfoList  = jsonObject.getJSONArray("media_info_list");
            JSONObject mediaDataObject = mediaInfoList .getJSONObject(0);
            String mediaData = mediaDataObject.getString("media_data");
            pictureDao.insert(new Picture(name, LocalDateTime.now(),"AI美颜",mediaData));
            return new JSONUtil(200,mediaData);
        } else {
            System.out.println("[INFO]:服务器未响应或者图片格式错误");
            return new JSONUtil(-1, "服务器未响应或图片格式错误");
        }

    }

    public static void main(String[] args) throws IOException, JSONException {
        String pic1 = "src/main/resources/BeautyTest.jpg";
        String image = getFileContentAsBase64(pic1,false);
        OutputStreamWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        HttpURLConnection conn = null;
        try{
            URL url = new URL("https://openapi.mtlab.meitu.com/v1/beauty?api_key=0530ef25a32e44f5bcde664804bf4879&api_secret=bdd61ad26b3f41d6835f2e07d02d6c71");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            conn.setDoOutput(true);
            conn.setDoInput(true);

            conn.setConnectTimeout(30000);
            conn.setReadTimeout(10000);
            conn.setRequestProperty("Content-Type", "application/json");

            out = new OutputStreamWriter(conn.getOutputStream());
            String jsonStr = "{\"parameter\":{\"beautyAlpha\":70},\"extra\":{},\"media_info_list\":[{\"media_data\":\""+ image +"\",\"media_profiles\":{\"media_data_type\":\"jpg\"}}]}";
            out.write(jsonStr);
            out.flush();
            out.close();

            if (200 == conn.getResponseCode()){
                in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            }else{
                System.out.println("ResponseCode is an error code:" + conn.getResponseCode());
                in = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
            }
            String line;
            while ((line = in.readLine()) != null){
                result.append(line);
                //System.out.println(line);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(out != null){
                    out.close();
                }
                if(in != null){
                    in.close();
                }
            }catch (IOException ioe){
                ioe.printStackTrace();
            }
        }
        //System.out.println(result);
        JSONObject jsonObject = new JSONObject(result.toString());
        JSONArray mediaInfoList  = jsonObject.getJSONArray("media_info_list");
        //System.out.println(mediaInfoList );
        JSONObject mediaDataObject = mediaInfoList .getJSONObject(0);
        String mediaData = mediaDataObject.getString("media_data");
        //System.out.println(mediaData);
        //return result.toString();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/base64.txt"))) {
            writer.write(mediaData);
            System.out.println("media_data has been written to the file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An error occurred while writing to the file.");
        }
    }
}
