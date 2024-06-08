package com.hit.project.service.impl;

import com.alibaba.dashscope.aigc.imagesynthesis.ImageSynthesis;
import com.alibaba.dashscope.aigc.imagesynthesis.ImageSynthesisListResult;
import com.alibaba.dashscope.aigc.imagesynthesis.ImageSynthesisParam;
import com.alibaba.dashscope.aigc.imagesynthesis.ImageSynthesisResult;
import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.alibaba.dashscope.task.AsyncTaskListParam;
import com.alibaba.dashscope.utils.Constants;
import com.hit.project.service.PicGenerateService;
import com.hit.project.utils.JSONUtil;
import org.springframework.stereotype.Service;

@Service
public class PicGenerateServiceImpl implements PicGenerateService {

    @Override
    public JSONUtil picGenerate(String prompt){
        ImageSynthesisResult result = null;
        try{
            System.out.println("[INFO]:开始生成图片");
            result = basicCall(prompt);
        }catch(ApiException|NoApiKeyException e){
            System.out.println("[INFO]:图片生成错误");
            System.out.println(e.getMessage());
        }
        System.out.println("[INFO]:成功生成图片");
        String url = result.getOutput().getResults().get(0).get("url");
        System.out.println("[INFO]:图片链接为:"+url);
        int code = result.getOutput().getTaskStatus().equals("SUCCEEDED")?200:-1;
        return new JSONUtil(code,url);
    }

    public static void main(String[] args) throws NoApiKeyException {
        ImageSynthesisResult result = null;
        try{
            System.out.println("[INFO]:开始生成图片");
            result = basicCall("生成一个雄壮的男人");
            System.out.println(result);
            System.out.println(result.getOutput().getResults());
            String res = result.getOutput().getResults().get(0).get("url");
            String success = result.getOutput().getTaskStatus();

        }catch(ApiException|NoApiKeyException e){
            System.out.println(e.getMessage());
        }
    }
    public static ImageSynthesisResult basicCall(String prompt) throws ApiException, NoApiKeyException {
        Constants.apiKey="sk-51975fe6151141bf8d138aedc25825af"; //设置api-key
        ImageSynthesis is = new ImageSynthesis();
        ImageSynthesisParam param =
                ImageSynthesisParam.builder()
                        .model(ImageSynthesis.Models.WANX_V1)
                        .n(1)
                        .size("1024*1024")
                        .prompt(prompt)
                        .build();


        ImageSynthesisResult result = is.call(param);
        return result;
    }
    public static void listTask() throws ApiException, NoApiKeyException {
        ImageSynthesis is = new ImageSynthesis();
        AsyncTaskListParam param = AsyncTaskListParam.builder().build();
        ImageSynthesisListResult result = is.list(param);
        System.out.println(result);
    }

    public void fetchTask() throws ApiException, NoApiKeyException {
        String taskId = "your task id";
        ImageSynthesis is = new ImageSynthesis();
        // If set DASHSCOPE_API_KEY environment variable, apiKey can null.
        ImageSynthesisResult result = is.fetch(taskId, null);
        System.out.println(result.getOutput());
        System.out.println(result.getUsage());
    }
}
