package com.frogking.patchouli.entry.searchitems;

import com.frogking.patchouli.entry.Items.Answer;
import com.frogking.patchouli.entry.Items.Question;
import com.frogking.patchouli.entry.Items.SearchItem;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class MyResourcesSearchItems extends SearchItem {

    @Override
    public List<Answer> search(Question question) {
        String book_name = question.getSelectSentence();
        List<Answer> answers = new ArrayList<>();
        if(book_name!=null){
            File file=new File("/www/wwwroot/www.frogking.cn/index.txt");
            BufferedReader reader=null;
            String temp = null;
            try{
                reader=new BufferedReader(new FileReader(file));
                while((temp=reader.readLine())!=null){
                    String ss = temp.replace("(", "");
                    ss = ss.replace(")", "");
                    ss = ss.replace("\"","");
                    ss = ss.replace("[", "");
                    ss = ss.replace("]", "");
                    ss = ss.replace("D.p","");
                    String[] sss = ss.split(",");
                    String one_resource = sss[0].replace("01资源网","").replace("01资源","");
                    if(one_resource.contains(question.getSelectSentence())) {
                        String[] filePNames = one_resource.split("/");
                        for(int j = filePNames.length-1;j>=0;j--){
                            if(filePNames[j].contains(question.getSelectSentence())){
                                Answer answer = new Answer();
                                answer.setIntroduction(one_resource);
                                answer.setSource("resource");
                                answer.setUrl("");
                                answer.setTitle(filePNames[j]);
                                answer.setSim(0.0);
                                answers.add(answer);
                                break;
                            }
                        }
                    }
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
            finally{
                if(reader!=null) {
                    try {
                        reader.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return answers;
    }



}
