package com.frogking.patchouli.workflow.interfaces;

import com.frogking.patchouli.entry.Items.Answer;
import com.frogking.patchouli.entry.Items.Question;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface Sorter {
    //查询结果排序
    List<Answer> sort(List<Answer> answers, Question question);

}
