package com.frogking.patchouli.webSipder.baiduspider;

import com.frogking.patchouli.entry.Items.Answer;
import com.frogking.patchouli.webSipder.SpiderMemory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaiduPipeline implements Pipeline {
    @Override
    public void process(ResultItems resultItems, Task task) {

        List<String> scan_page = resultItems.get("page");

        String key = resultItems.get("wd");

        //每一条链接
        List<String> links = getSubString("href=\"([\\s\\S]*?)\"",scan_page);
        //每一条标题
        List<String> titles = getSubString(">([^<]*?)</a>",scan_page);
        //每一条简介
        List<String> commits = getSubString("<div class=\"c-abstract\">([\\s\\S]*?)</div>",scan_page);

        for(int i=0;i<Math.min(Math.min(links.size(),titles.size()),commits.size());i++){
            Answer answer = new Answer();
            answer.setIntroduction(commits.get(i)
                    //.replaceAll("<[\\s\\S]*?>","")
                    //.replaceAll("&nbsp;","")
            );
            answer.setTitle(titles.get(i));
            answer.setUrl(links.get(i));
            answer.setSource("baidu");
            SpiderMemory.add("baidu",key,answer);
        }


    }

    private List<String> getSubString(String regex, List<String> strs){
        List<String> result = new ArrayList<>();
        for(String str:strs) {
            Pattern pattern = Pattern.compile(regex);
            Matcher m = pattern.matcher(str);
            if (m.find()) {
                result.add(m.group(1));
            }
        }
        return result;
    }

}
