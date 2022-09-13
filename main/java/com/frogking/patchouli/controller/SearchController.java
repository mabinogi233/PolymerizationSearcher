package com.frogking.patchouli.controller;


import com.frogking.patchouli.entry.Items.Answer;
import com.frogking.patchouli.entry.Items.Question;
import com.frogking.patchouli.entry.Items.SearchItem;
import com.frogking.patchouli.entry.searchitems.SearchItemFactory;
import com.frogking.patchouli.workflow.RunMain;
import com.frogking.patchouli.workflow.interfaces.Searcher;
import com.frogking.patchouli.workflow.interfaces.SelectCreator;
import com.frogking.patchouli.workflow.interfaces.Sorter;
import com.frogking.patchouli.workflow.mainutils.MainSelectCreator;
import com.frogking.patchouli.workflow.mainutils.MainSorter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    @Qualifier("mainSelectCreator")
    private MainSelectCreator mainSelectCreator;

    @Autowired
    @Qualifier("mainSorter")
    private MainSorter mainSorter;

    //查询语句生成
    @Autowired
    @Qualifier("mainSelectCreator")
    private SelectCreator selectCreator;

    //搜索器
    @Autowired
    @Qualifier("mainSearcher")
    private Searcher searcher;

    //排序器
    @Autowired
    @Qualifier("mainSorter")
    private Sorter sorter;

    //搜索元工厂
    @Autowired
    @Qualifier("searchItemFactory")
    private SearchItemFactory searchItemFactory;

    @Autowired
    private RunMain runMain;

    @ResponseBody
    @RequestMapping("/test")
    public String test(){
        Question question = new Question();
        question.setSelectSentence("水是维持生命最重要的因素之一，科学家们在寻找系外宜居星球时，也总是把“是否存在液态水”作为重要的依据标准，那么问题来了，宇宙中这么多星球，为什么只有地球上有水？而且地球已经诞生了46亿年，为什么地球上的水还没有被用完");
        if(mainSelectCreator.createSimSentence(question)==null){
            System.out.println("不存在");
        };

        for (Question question1:mainSelectCreator.createSimSentence(question)){
            System.out.println(question1.getSelectSentence());
        }

        return "finish";
    }

    @ResponseBody
    @RequestMapping("/sort")
    public String testSOrt(){
        Question question = new Question();
        question.setSelectSentence("中文同义句例子");
        Answer answer1 = new Answer();
        answer1.setIntroduction("举例的同义词");
        Answer answer2 = new Answer();
        Answer answer3 = new Answer();
        Answer answer4 = new Answer();
        Answer answer5 = new Answer();
        answer2.setIntroduction("什么是同义句?具体说明一下同义句是什么,该怎样转换");
        answer3.setIntroduction("同义句例子");
        answer4.setIntroduction("同义句怎么学");
        answer5.setIntroduction("同义句怎么改,英语同义句怎么改");
        List<Answer> answers = new ArrayList<>();
        answers.add(answer1);
        answers.add(answer2);
        answers.add(answer3);
        answers.add(answer4);
        answers.add(answer5);
        for (Answer answer:mainSorter.sort(answers,question)){
            System.out.println("**************************************************************");
            System.out.println(answer.getIntroduction());
            System.out.println(answer.getSim());
        }
        return "finish";
    }
    @ResponseBody
    @RequestMapping("/main")
    public String maintest(){
        Question question = new Question();
        question.setSelectSentence("java多线程");
        Map<String,Object> options = new HashMap<>();
        String[] searchItemNames = new String[]{"baidu"};
        options.put("searchItemNames",searchItemNames);
        List<Answer> answers = runMain.run(question,options);
        /*
        List<Question> questions = mainSelectCreator.createSimSentence(question);

        //获取搜索元
        String[] searchItemNames = new String[]{"baidu"};
        List<SearchItem> searchItems = searchItemFactory.getSearchItems(searchItemNames);

        //执行查询过程
        List<Answer> unSortAnswers = searcher.search(questions,searchItems);

        System.out.println("开始排序");
        //排序
        List<Answer> answers = sorter.sort(unSortAnswers,question);
        */

        for(Answer answer:answers){
            System.out.println("++++++++++++++++++++++++++++++++++++");
            System.out.println(answer.getTitle());
            System.out.println(answer.getIntroduction());
            System.out.println(answer.getUrl());
            System.out.println(answer.getSim());
        }

        return "finish";
    }


}
