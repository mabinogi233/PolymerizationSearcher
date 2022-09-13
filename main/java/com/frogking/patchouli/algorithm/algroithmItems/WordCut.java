package com.frogking.patchouli.algorithm.algroithmItems;


import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//分词算法
@Component
public class WordCut {
    /**
     * 分词
     * @param sentence
     * @return
     */
    public List<String> wordCut(String sentence){
        //调用hanLP的NLP分词
        List<Term> wordsTerm = NLPTokenizer.segment(sentence);
        //格式转换
        List<String> words = new ArrayList<String>();
        for(Term word:wordsTerm){
            words.add(word.word);
        }
        return words;
    }
}
