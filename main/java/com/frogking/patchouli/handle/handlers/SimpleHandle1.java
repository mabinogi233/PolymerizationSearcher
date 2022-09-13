package com.frogking.patchouli.handle.handlers;

import com.frogking.patchouli.entry.Items.Question;
import com.frogking.patchouli.handle.interfaces.Handle1;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SimpleHandle1 implements Handle1 {

    @Override
    public Question handle(Question question, Map<String, Object> parameter) {
        return question;
    }
}
