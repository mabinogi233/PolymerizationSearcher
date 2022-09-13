package com.frogking.patchouli.workflow.mainutils;

import com.frogking.patchouli.entry.Items.Answer;
import com.frogking.patchouli.entry.Items.Question;
import com.frogking.patchouli.entry.Items.SearchItem;
import com.frogking.patchouli.workflow.interfaces.Searcher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MainSearcher implements Searcher {


    /**
     * 调用搜索元，执行搜索过程
     * @param questions
     * @param searchItems
     * @return
     */
    @Override
    public List<Answer> search(List<Question> questions, List<SearchItem> searchItems) {
        List<Answer> answers = new ArrayList<>();
        for(SearchItem searchItem:searchItems){
            for(Question question:questions){
                List<Answer> one_answer_list = searchItem.search(question);
                answers.addAll(one_answer_list);
            }
        }
        return answers;
    }
}
