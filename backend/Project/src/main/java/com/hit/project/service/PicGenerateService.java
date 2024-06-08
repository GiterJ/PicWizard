package com.hit.project.service;

import com.alibaba.dashscope.aigc.imagesynthesis.ImageSynthesisResult;
import com.hit.project.utils.JSONUtil;

public interface PicGenerateService {


    JSONUtil picGenerate(String prompt);
}
