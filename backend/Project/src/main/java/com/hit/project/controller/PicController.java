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
    @Autowired
    private PicColorService picColorService;
    @Autowired
    private PicClearService picClearService;
    @Autowired
    private PicDefogService picDefogService;
    @PostMapping("/generatepic")
    public JSONUtil generatePic(@RequestParam("prompt")String prompt){
        return picGenerateService.picGenerate(prompt);
    }
    @PostMapping("/picqa")
    public JSONUtil picQA(@RequestParam("image")String image,@RequestParam("question")String question) throws JSONException, IOException, InterruptedException {
        System.out.println("[INFO]:传入的question:"+question);
        picQAService.sendRequest(image,question);
        return picQAService.getResponse();
    }
    @PostMapping("/facemerge")
    public JSONUtil faceMerge(@RequestParam("target")String target,@RequestParam("template")String template,@RequestParam("name")String name) throws JSONException, IOException {
        return faceMergeService.faceMerge(target,template,name);
    }
    //prompt 只有四个选项，TO_OLD,TO_KID,TO_FEMALE,TO_MALE
    @PostMapping("/attributeedit")
    public JSONUtil attributeEdit(@RequestParam("image")String image,@RequestParam("prompt")String prompt,@RequestParam("name")String name) throws JSONException, IOException {
        return attributeEditService.AttributeEdit(image,prompt,name);
    }
    //该方法传入的base64编址格式为application/x-www-form-urlencoded
    @PostMapping("/faceanime")
    public JSONUtil faceAnime(@RequestParam("image")String image,@RequestParam("name")String name) throws JSONException, IOException {
        return faceAnimeService.faceAnime(image,name);
    }
    @PostMapping("/beauty")
    public JSONUtil beauty(@RequestParam("image")String image,@RequestParam("name")String name) throws JSONException {
        return beautyService.beauty(image,name);
    }
    @PostMapping("/piccolor")
    public JSONUtil picColor(@RequestParam("image")String image,@RequestParam("name")String name) throws JSONException, IOException {
        return picColorService.picColor(image,name);
    }
    @PostMapping("/picclear")
    public JSONUtil picClear(@RequestParam("image")String image,@RequestParam("name")String name) throws JSONException, IOException {
        return picClearService.picClear(image,name);
    }
    @PostMapping("/picdefog")
    public JSONUtil picDefog(@RequestParam("image")String image,@RequestParam("name")String name) throws JSONException, IOException {
        return picDefogService.picDefog(image,name);
    }
    @RequestMapping("/test")
    public String test(){
        return "This is for test";
    }
}
