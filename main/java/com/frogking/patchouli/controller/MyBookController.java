package com.frogking.patchouli.controller;

import com.frogking.patchouli.entry.Items.Answer;
import com.frogking.patchouli.entry.Items.Question;
import com.frogking.patchouli.entry.searchitems.MyBooksSearchItem;
import com.frogking.patchouli.entry.searchitems.MyResourcesSearchItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/book")
public class MyBookController {

    @Autowired
    private MyBooksSearchItem myBooksSearchItem;

    @Autowired
    private MyResourcesSearchItems myResourcesSearchItems;

    //搜索函数
    @RequestMapping("/search")
    public String search(ModelMap map,@RequestParam(value="name",required=false) String name){
        Question question = new Question();
        if(name==null || name.equals("")) {
            question.setSelectSentence("");
            map.addAttribute("answers",null);
        }else{
            question.setSelectSentence(name);
            List<Answer> answers = myBooksSearchItem.search(question);
            map.addAttribute("answers",answers);
        }
        return "searchList";
    }

    @RequestMapping("")
    public String getIndex(){
        return "index";
    }


    //获取下载说明
    @RequestMapping("/getDownloadExplain")
    public String getDownloadExplain(){
        return "explain";
    }

    //其他资源搜索
    @RequestMapping("/searchOther")
    public String searchOther(ModelMap map,@RequestParam(value="name",required=false) String name){
        List<Answer> answers = new ArrayList<>();
        boolean isSearch = false;
        if(name!=null && !name.equals("")) {
            Question question = new Question();
            question.setSelectSentence(name);
            answers = myResourcesSearchItems.search(question);
            isSearch = true;
        }
        map.addAttribute("isSearch",isSearch);
        map.addAttribute("answers",answers);
        return "searchOther";
    }

    @RequestMapping("/getOtherExplain")
    public String getOtherExplain(){
        return "explainOther";
    }

    @RequestMapping("/getResourceSearchIndex")
    public String getResourceSearchIndex(){
        return "resourceSearch";
    }

}
