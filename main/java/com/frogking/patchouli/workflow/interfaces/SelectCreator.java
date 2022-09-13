package com.frogking.patchouli.workflow.interfaces;


import com.frogking.patchouli.entry.Items.Question;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SelectCreator {
    //生成相似查询
    List<Question> createSimSentence(Question question);
}
