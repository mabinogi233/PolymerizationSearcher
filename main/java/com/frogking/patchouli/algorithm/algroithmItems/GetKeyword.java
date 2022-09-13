package com.frogking.patchouli.algorithm.algroithmItems;


import com.hankcs.hanlp.HanLP;

import org.springframework.stereotype.Component;

import java.util.List;

//关键词提取
@Component
public class GetKeyword {
    /**
     * 提取n个关键词
     * @param words
     * @param n
     * @return
     */
    public List<String> getKeyword(String words,int n){

        return HanLP.extractKeyword(words,n);

    }
}
