package com.frogking.patchouli.webSipder.baiduspider;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;


@Component
public class BaiduSpider implements PageProcessor {
    //配置文件
    private Site site = Site.me();

    @Override
    public void process(Page page) {
        System.out.println("开始");
        //找到每一条搜索结果
        List<String> scan_page = page.getHtml().xpath(
                "/html/body/div[@id=\"wrapper\"]/div[@id=\"wrapper_wrapper\"]" +
                        "/div[@id=\"container\"]/div[@id=\"content_left\"]/div[@id]").all();

        //加入后9页
        String url_no_pn = page.getUrl().get().split("pn=")[0];
        for(int i=1;i<10;i++){
            page.addTargetRequest(url_no_pn + "pn=" + String.valueOf(i*10));
        }

        //加入缓冲区
        String key = page.getUrl().regex("wd=([^&]*?)&").get();
        page.putField("wd",key);
        page.putField("page",scan_page);
    }

    @Override
    public Site getSite() {
        return site;
    }


}
