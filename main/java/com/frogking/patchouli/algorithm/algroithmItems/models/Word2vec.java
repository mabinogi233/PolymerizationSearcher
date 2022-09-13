package com.frogking.patchouli.algorithm.algroithmItems.models;


import com.hankcs.hanlp.mining.word2vec.DocVectorModel;
import com.hankcs.hanlp.mining.word2vec.Vector;
import com.hankcs.hanlp.mining.word2vec.Word2VecTrainer;
import com.hankcs.hanlp.mining.word2vec.WordVectorModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

//word2vec算法
@Component
public class Word2vec {

    private WordVectorModel word2VectorModel;

    private String modelFilePath = "D:\\codes\\codes\\patchouli\\src\\main\\resources\\data\\mrs.txt";

    /**
     * 模型训练
     * @param trainFileName 训练文件地址
     * @param modelFileName 模型存储地址
     * @return
     */
    public WordVectorModel train(String trainFileName, String modelFileName){
        Word2VecTrainer trainerBuilder = new Word2VecTrainer();
        WordVectorModel wordVectorModel = trainerBuilder.train(trainFileName, modelFileName);
        return wordVectorModel;
    }

    public WordVectorModel getWord2VectorModel() {
        return word2VectorModel;
    }

    public void setWord2VectorModel(WordVectorModel word2VectorModel) {
        this.word2VectorModel = word2VectorModel;
    }

    public String getModelFilePath() {
        return modelFilePath;
    }

    public void setModelFilePath(String modelFilePath) {
        this.modelFilePath = modelFilePath;
    }

    public void loadWord2VectorModel(){
        try {
            word2VectorModel = new WordVectorModel(modelFilePath);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void loadWord2VectorModel(String modelFilePath){
        try {
            word2VectorModel = new WordVectorModel(modelFilePath);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //计算两个词之间的相似度
    public double getWordSimilarity(String a,String b){
        if(word2VectorModel==null){
            this.loadWord2VectorModel();
        }
        return word2VectorModel.similarity(a,b);
    }

    //获取词的k个相似的词
    public List<Map.Entry<String, Float>> nearest(String word, int k){
        if(word2VectorModel==null){
            this.loadWord2VectorModel();
        }
        return word2VectorModel.nearest(word,k);
    }

    //计算文本相似度
    public double getSentenceSimilarity(String a,String b){
        try {
            DocVectorModel docVectorModel = new DocVectorModel(new WordVectorModel(modelFilePath));
            return docVectorModel.similarity(a, b);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
}
