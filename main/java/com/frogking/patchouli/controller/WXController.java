package com.frogking.patchouli.controller;


import com.alibaba.fastjson.JSONObject;
import com.frogking.patchouli.entry.Items.Answer;
import com.frogking.patchouli.entry.Items.Question;
import com.frogking.patchouli.entry.searchitems.MyBooksSearchItem;
import com.frogking.patchouli.entry.searchitems.MyResourcesSearchItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/wx")
@CrossOrigin
public class WXController {

    @Autowired
    private MyBooksSearchItem myBooksSearchItem;

    @Autowired
    private MyResourcesSearchItems myResourcesSearchItems;

    //搜索函数
    @RequestMapping("/search")
    @ResponseBody
    public String search(@RequestParam(value="name",required=false) String name){
        try {
            Question question = new Question();
            List<Answer> answers = new ArrayList<>();
            if (name != null && !name.equals("")) {
                question.setSelectSentence(name);
                answers = myBooksSearchItem.search(question);
            }
            Map<String, Object> resultMap = new HashMap<>();
            /*
            //微信审核
            answers.clear();
            Answer answer1 = new Answer();
            answer1.setIntroduction("/大学/高等数学/习题.txt");
            answer1.setTitle("高等数学习题");
            answers.add(answer1);
            Answer answer2 = new Answer();
            answer2.setIntroduction("/大学/大物/31254.txt");
            answer2.setTitle("大学物理习题");
            answers.add(answer2);
            Answer answer3 = new Answer();
            answer3.setIntroduction("/大学/计算机/21540.txt");
            answer3.setTitle("设计模式");
            answers.add(answer3);
            Answer answer4 = new Answer();
            answer4.setIntroduction("/大学/线代/12548.txt");
            answer4.setTitle("线性代数");
            answers.add(answer4);
            Answer answer5 = new Answer();
            answer5.setIntroduction("/大学/概率论/12587.txt");
            answer5.setTitle("概率论");
            answers.add(answer5);
            */
            resultMap.put("answers", answers);
            resultMap.put("code", "success");
            return JSONObject.toJSONString(resultMap);
        }catch (Exception e){
            e.printStackTrace();
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("answers", "");
            resultMap.put("code", "error");
            return JSONObject.toJSONString(resultMap);
        }
    }


    //其他资源搜索
    @RequestMapping("/searchOther")
    @ResponseBody
    public String searchOther(@RequestParam(value="name",required=false) String name){
        try {
            List<Answer> answers = new ArrayList<>();
            boolean isSearch = false;
            if (name != null && !name.equals("")) {
                Question question = new Question();
                question.setSelectSentence(name);
                answers = myResourcesSearchItems.search(question);
                isSearch = true;
            }
            /*
            //微信审核

            answers.clear();
            Answer answer1 = new Answer();
            answer1.setIntroduction("/大学/高等数学/习题.txt");
            answer1.setTitle("高等数学习题");
            answers.add(answer1);
            Answer answer2 = new Answer();
            answer2.setIntroduction("/大学/大物/31254.txt");
            answer2.setTitle("大学物理习题");
            answers.add(answer2);
            Answer answer3 = new Answer();
            answer3.setIntroduction("/大学/计算机/21540.txt");
            answer3.setTitle("设计模式");
            answers.add(answer3);
            Answer answer4 = new Answer();
            answer4.setIntroduction("/大学/线代/12548.txt");
            answer4.setTitle("线性代数");
            answers.add(answer4);
            Answer answer5 = new Answer();
            answer5.setIntroduction("/大学/概率论/12587.txt");
            answer5.setTitle("概率论");
            answers.add(answer5);
            */
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("answers", answers);
            resultMap.put("code", "success");
            resultMap.put("isSearch", isSearch);
            return JSONObject.toJSONString(resultMap);
        }catch (Exception e){
            e.printStackTrace();
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("answers", "");
            resultMap.put("code", "error");
            resultMap.put("isSearch", false);
            return JSONObject.toJSONString(resultMap);
        }
    }

    @ResponseBody
    @RequestMapping("/message")
    public String getUploadMessage(){
        String message = "关注公众号“frogKingPDF资源”并回复“下载方式”获取更多资源下载方式";
        String messageWX = "当前暂未开放浏览";
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("message", message);
        return JSONObject.toJSONString(resultMap);
    }
}
