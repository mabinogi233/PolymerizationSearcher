package com.frogking.patchouli.workflow.mainutils;

import com.frogking.patchouli.algorithm.AlgorithmFactory;
import com.frogking.patchouli.algorithm.algroithmItems.GetSimPoint;
import com.frogking.patchouli.entry.Items.Answer;
import com.frogking.patchouli.entry.Items.Question;
import com.frogking.patchouli.workflow.interfaces.Sorter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class MainSorter implements Sorter {

    @Autowired
    @Qualifier("algorithmFactory")
    private AlgorithmFactory algorithmFactory;

    private double weigth_word2vec = 1;


    @Override
    public List<Answer> sort(List<Answer> answers, Question question) {
        System.out.println("开始start");
        //分词，找出关键词，认为每个answer为关键词组成的词袋
        for(int i=0;i<answers.size();i++) {
            System.out.println(answers.get(i).getTitle());
            System.out.println(question.getSelectSentence());
            //使用word2vec进行向量化
            //……可采用多种方式向量化
            //分别计算不同向量化方法的相似度
            GetSimPoint getSimPoint = algorithmFactory.getGetSimPoint();
            double word2vec_sim = getSimPoint.getSentenceSimilarity(
                    answers.get(i).getIntroduction(),question.getSelectSentence());
            answers.get(i).setSim(
                    //加权计算总的相似度
                    weigth_word2vec * word2vec_sim);
            System.out.println(i);
        }
        //按相似度由高到低排序
        answers.sort(new Comparator<Answer>() {
            @Override
            public int compare(Answer answer, Answer t1) {
                Double b = t1.getSim();
                Double a = answer.getSim();
                return b.compareTo(a);
            }
        });
        //返回结果
        return answers;
    }
}
