package com.frogking.patchouli.algorithm.algroithmItems.models;

import com.baidu.aip.nlp.AipNlp;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
//使用百度API计算文本相似度
public class BaiduWord2vec extends Word2vec {

    private static final String APP_ID = "";
    private static final String API_KEY = "";
    private static final String SECRET_KEY = "";


    //计算文本相似度
    @Override
    public double getSentenceSimilarity(String a,String b){
        // 初始化一个AipNlp
        AipNlp client = new AipNlp(APP_ID, API_KEY, SECRET_KEY);
        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 传入可选参数调用接口
        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("model", "GRNN");
        // 文本相似度
        JSONObject res;
        do {
            res = client.simnet(a, b, options);
        }while (res.has("error_msg"));
        //System.out.println(res.toString(2));
        return res.getDouble("score");
    }

}
