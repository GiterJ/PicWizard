package com.hit.project;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static com.hit.project.utils.BaiduUtil.getFileContentAsBase64;

@SpringBootTest
class ProjectApplicationTests {

    @Test
    void contextLoads() {
    }
    public static void generateTest(String picPath,String picBasePath,Boolean b) throws IOException {
        String picBase = getFileContentAsBase64(picPath,b);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(picBasePath))) {
            writer.write(picBase);
            System.out.println("Picture_Base64 has been written to the file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An error occurred while writing to the file.");
        }

    }
    public static void main(String[] args) throws IOException {
        generateTest("src/main/resources/PicQATest.jpg","src/main/resources/PicQATest.txt",false);
        generateTest("src/main/resources/FaceMergeTargetTest.jpg","src/main/resources/FaceMergeTargetTest.txt",false);
        generateTest("src/main/resources/FaceMergeTemplateTest.jpg","src/main/resources/FaceMergeTemplateTest.txt",false);
        generateTest("src/main/resources/FaceAnimeTest.jpg","src/main/resources/FaceAnimeTest.txt",true);
        generateTest("src/main/resources/BeautyTest.jpg","src/main/resources/BeautyTest.txt",false);
        generateTest("src/main/resources/AttributeEditTest.jpg","src/main/resources/AttributeEditTest.txt",false);

    }

}
