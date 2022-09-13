package com.frogking.patchouli.entry.searchitems;

import com.frogking.patchouli.entry.Items.Answer;
import com.frogking.patchouli.entry.Items.Question;
import com.frogking.patchouli.entry.Items.SearchItem;
import com.frogking.patchouli.webSipder.baiduspider.BaiduPipeline;
import com.frogking.patchouli.webSipder.baiduspider.BaiduSpider;
import com.frogking.patchouli.webSipder.SpiderMemory;
import us.codecraft.webmagic.Spider;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class BaiduSearchItem extends SearchItem {

    private String name = "baidu";

    @Override
    public List<Answer> search(Question question) {
        try {
            String wd = URLEncoder.encode(question.getSelectSentence(),"utf-8");
            String url = "http://www.baidu.com/s?wd="+wd+"&pn=0";
            Spider.create(new BaiduSpider())
                    .addPipeline(new BaiduPipeline())
                    .addUrl(url).thread(10).run();
            List<Answer> answers = SpiderMemory.get("baidu",wd);
            SpiderMemory.delete("baidu",wd);
            return answers;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
