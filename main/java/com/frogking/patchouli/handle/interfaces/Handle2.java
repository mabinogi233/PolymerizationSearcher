package com.frogking.patchouli.handle.interfaces;

import com.frogking.patchouli.entry.Items.Question;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface Handle2 {
    //过滤生成的查询语句
    List<Question> handle(List<Question> questionsIn, Map<String,Object> parameter);
}
