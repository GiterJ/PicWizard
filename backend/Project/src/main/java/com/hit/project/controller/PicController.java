package com.hit.project.controller;

import com.alibaba.dashscope.aigc.imagesynthesis.ImageSynthesisResult;
import com.hit.project.service.*;
import com.hit.project.utils.JSONUtil;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
public class PicController {
    @Autowired
    private PicGenerateService picGenerateService;
    @Autowired
    private PicQAService picQAService;
    @Autowired
    private FaceMergeService faceMergeService;
    @Autowired
    private AttributeEditService attributeEditService;
    @Autowired
    private FaceAnimeService faceAnimeService;
    @Autowired
    private BeautyService beautyService;
    @PostMapping("/generatepic")
    public JSONUtil generatePic(@RequestParam("prompt")String prompt){
        return picGenerateService.picGenerate(prompt);
    }
    @PostMapping("/picqa")
    public JSONUtil picQA(@RequestParam("image")String image,@RequestParam("question")String question) throws JSONException, IOException, InterruptedException {
        System.out.println("[INFO]:传入的question:"+question);
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,DELETE");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        response.setHeader("Access-Control-Allow-Headers", "*");
//        response.setHeader("Access-Control-Allow-Credentials", "true");
        picQAService.sendRequest(image,question);
        JSONUtil jsonUtil = picQAService.getResponse();
        //System.out.println(jsonUtil);
//        return picQAService.getResponse();
        return jsonUtil;
    }
    @PostMapping("/facemerge")
    public JSONUtil faceMerge(@RequestParam("target")String target,@RequestParam("template")String template) throws JSONException, IOException {
        return faceMergeService.faceMerge(target,template);
    }
    //prompt 只有四个选项，TO_OLD,TO_KID,TO_FEMALE,TO_MALE
    @PostMapping("/attributeedit")
    public JSONUtil attributeEdit(@RequestParam("image")String image,@RequestParam("prompt")String prompt) throws JSONException, IOException {
        return attributeEditService.AttributeEdit(image,prompt);
    }
    //该方法传入的base64编址格式为application/x-www-form-urlencoded
    @PostMapping("/faceanime")
    public JSONUtil faceAnime(@RequestParam("image")String image) throws JSONException, IOException {
        return faceAnimeService.faceAnime(image);
    }
    @PostMapping("/beauty")
    public JSONUtil beauty(@RequestParam("image")String image) throws JSONException {
        return beautyService.beauty(image);
    }
    @RequestMapping("/test")
    public String test(){
        return "This is for test";
    }
}
