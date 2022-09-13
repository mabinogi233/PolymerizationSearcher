package com.frogking.patchouli.workflow.mainutils;

import com.frogking.patchouli.algorithm.AlgorithmFactory;
import com.frogking.patchouli.algorithm.algroithmItems.GetKeyword;
import com.frogking.patchouli.algorithm.algroithmItems.GetSimWord;
import com.frogking.patchouli.algorithm.algroithmItems.WordCut;
import com.frogking.patchouli.entry.Items.Question;
import com.frogking.patchouli.workflow.interfaces.SelectCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MainSelectCreator implements SelectCreator {

    @Autowired
    @Qualifier("algorithmFactory")
    private AlgorithmFactory algorithmFactory;

    private double pk = 0.9;

    private double pj = 0.3;
    //最多生成10句
    private int m = 10;

    //近义词个数
    private int num = 5;

    @Override
    public List<Question> createSimSentence(Question question) {

        String word = question.getSelectSentence();
        //对question进行分词
        WordCut wordCut = algorithmFactory.getWordCut();
        List<String> words = wordCut.wordCut(word);

        //进行关键字提取
        GetKeyword getKeyword = algorithmFactory.getGetKeyword();
        int k = words.size()/3;
        if(words.size() > 15){
            k = 5;
        }
        if(k==0){
            k = 1;
        }
        List<String> keyWords = getKeyword.getKeyword(word,k);
        //进行近义词替换，对于关键词，有k的概率选中，对于非关键词，有j的概率选中
        //每个词选择不一定是最近义的替换，越相似替换概率越大（类似模拟退火）
        List<Question> questions = new ArrayList<Question>();

        GetSimWord getSimWord = algorithmFactory.getGetSimWord();
        //加入原句
        questions.add(question);
        //进行近义词替换
        for(int i=0;i<words.size();i++){
            if(keyWords.contains(words.get(i))){
                //是关键词
                //获取均匀分布的随机数
                double p_k = Math.random();
                if(p_k<=pk){
                    //替换
                    List<Map.Entry<String, Float>> simWords = getSimWord.nearest(words.get(i),num);
                    for(int j=0;j<simWords.size();j++){
                        //获取均匀分布的随机数
                        double p_sim = Math.random();
                        if(p_sim<=simWords.get(j).getValue()){
                            //替换
                            StringBuilder question_replace = new StringBuilder();
                            for(int o=0;o<words.size();o++){
                                if(o==i){
                                    //替换的词
                                    question_replace.append(simWords.get(j).getKey());
                                }else {
                                    question_replace.append(words.get(o));
                                }
                            }
                            if(questions.size()<=m) {
                                Question question1 = new Question();
                                question1.setSelectSentence(question_replace.toString());
                                questions.add(question1);
                            }
                        }
                    }
                }

            }else {
                //不是关键词
                //获取均匀分布的随机数
                double p_j = Math.random();
                if(p_j<=pj){
                    //替换
                    List<Map.Entry<String, Float>> simWords = getSimWord.nearest(words.get(i),num);
                    for(int j=0;j<simWords.size();j++){
                        //获取均匀分布的随机数
                        double p_sim = Math.random();
                        if(p_sim<=simWords.get(j).getValue()){
                            //替换
                            StringBuilder question_replace = new StringBuilder();
                            for(int o=0;o<words.size();o++){
                                if(o==i){
                                    //替换的词
                                    question_replace.append(simWords.get(j).getKey());
                                }else {
                                    question_replace.append(words.get(o));
                                }
                            }
                            if(questions.size()<=m) {
                                Question question1 = new Question();
                                question1.setSelectSentence(question_replace.toString());
                                questions.add(question1);
                            }
                        }
                    }
                }

            }
        }

        //返回替换后的句子结果
        return questions;
    }
}
