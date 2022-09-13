package com.frogking.patchouli.algorithm.algroithmItems;


import com.frogking.patchouli.algorithm.algroithmItems.models.Word2vec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

//近义词替换算法
@Component
public class GetSimWord {

    @Autowired
    private Word2vec word2vec;

    public List<Map.Entry<String, Float>> nearest(String word, int k){
        return word2vec.nearest(word,k);
    }
}
