package com.frogking.patchouli.algorithm;


import com.frogking.patchouli.algorithm.algroithmItems.GetKeyword;
import com.frogking.patchouli.algorithm.algroithmItems.GetSimPoint;
import com.frogking.patchouli.algorithm.algroithmItems.GetSimWord;
import com.frogking.patchouli.algorithm.algroithmItems.WordCut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//算法factory用于获取算法的一个实例
@Component
public class AlgorithmFactory {

    @Autowired
    @Qualifier("wordCut")
    private WordCut wordCut;

    @Autowired
    @Qualifier("getKeyword")
    private GetKeyword getKeyword;

    @Autowired
    @Qualifier("getSimPoint")
    private GetSimPoint getSimPoint;

    @Autowired
    @Qualifier("getSimWord")
    private GetSimWord getSimWord;


    public GetSimWord getGetSimWord() {
        return getSimWord;
    }

    public GetKeyword getGetKeyword() {
        return getKeyword;
    }

    public GetSimPoint getGetSimPoint() {
        return getSimPoint;
    }

    public WordCut getWordCut() {
        return wordCut;
    }
}
