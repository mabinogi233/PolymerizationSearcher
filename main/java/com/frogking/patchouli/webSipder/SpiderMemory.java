package com.frogking.patchouli.webSipder;

import com.frogking.patchouli.entry.Items.Answer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 线程存储的结果，以查询句子为key
 */
public class SpiderMemory {
    static private Map<String,Map<String,List<Answer>>> memory;

    static {
        memory = new HashMap<>();
    }
    //添加key的搜索结果
    public static synchronized void add(String spiderName,String key,Answer value){
        if(memory.get(spiderName)==null){
            memory.put(spiderName,new HashMap<>());
        }
        if(memory.get(spiderName).get(key)==null){
            memory.get(spiderName).put(key,new ArrayList<>());
        }

        memory.get(spiderName).get(key).add(value);

    }
    //删除key的搜索结果
    public static synchronized void delete(String spiderName,String key){
        if(memory.get(spiderName)==null){
            memory.put(spiderName,new HashMap<>());
        }
        if(memory.get(spiderName).get(key)==null){
            memory.get(spiderName).put(key,new ArrayList<>());
        }
        memory.get(spiderName).remove(key);
        if(memory.get(spiderName).size()==0){
            memory.replace(spiderName,null);
        }
    }

    //查找key的搜索结果
    public static synchronized List<Answer> get(String spiderName,String key){
        if(memory.get(spiderName)==null){
            memory.put(spiderName,new HashMap<>());
        }
        if(memory.get(spiderName).get(key)==null){
            memory.get(spiderName).put(key,new ArrayList<>());
        }
        return memory.get(spiderName).get(key);
    }

    //判断key的搜索结果是否为空
    public static synchronized boolean hasKey(String spiderName,String key){
        if(memory.get(spiderName)==null){
            return true;
        }else if(memory.get(spiderName).get(key)==null) {
            return true;
        }else {
            return memory.get(spiderName).containsKey(key);
        }
    }
}
