package com.frogking.patchouli.workflow.interfaces;


import com.frogking.patchouli.entry.Items.Answer;
import com.frogking.patchouli.entry.Items.Question;
import com.frogking.patchouli.entry.Items.SearchItem;
import org.springframework.stereotype.Component;

import java.util.List;

//搜索器
@Component
public interface Searcher {
    //具体调用搜索元进行搜索
    List<Answer> search (List<Question> questions, List<SearchItem> searchItems);
}
