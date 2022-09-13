package com.frogking.patchouli.handle.handlers;

import com.frogking.patchouli.entry.Items.Answer;
import com.frogking.patchouli.handle.interfaces.Handle4;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class SimpleHandle4 implements Handle4 {
    @Override
    public List<Answer> handle(List<Answer> answers, Map<String, Object> parameter) {
        return answers;
    }
}
