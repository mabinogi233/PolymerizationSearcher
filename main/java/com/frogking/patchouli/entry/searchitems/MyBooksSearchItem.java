package com.frogking.patchouli.entry.searchitems;

import com.frogking.patchouli.entry.Items.Answer;
import com.frogking.patchouli.entry.Items.Question;
import com.frogking.patchouli.entry.Items.SearchItem;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class MyBooksSearchItem extends SearchItem {

    //格式：文件名\t路径\n
    private static String book_path_fileNamePath = "/www/wwwroot/www.frogking.cn/目录.txt";
    //格式：路径\t百度云链接\t百度云密码\n
    private static String path_url_fileNamePath = "";

    @Override
    public List<Answer> search(Question question) {
        String book_name = question.getSelectSentence();
        List<Answer> answers = new ArrayList<>();
        if(book_name!=null){
            File file=new File(book_path_fileNamePath);
            BufferedReader reader=null;
            String temp = null;
            try{
                reader=new BufferedReader(new FileReader(file));
                while((temp=reader.readLine())!=null){
                    String[] one_book = temp.split("\t");
                    if(one_book[0].contains(book_name)){
                        Answer answer = new Answer();
                        answer.setTitle(one_book[0]);
                        answer.setUrl("");
                        //复用此字段，表示百度云密码
                        answer.setIntroduction(one_book[1].replace("H:\\资源\\资源分享\\", ""));
                        answer.setSim(0.0);
                        answer.setSource("图书资源");
                        answers.add(answer);
                        System.out.println(answer.getTitle());
                        System.out.println(answer.getIntroduction());
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



    //根据路径查找url和密码
    //返回 链接\t密码
    private static String getUrlByPath(String root){
        File file=new File(path_url_fileNamePath);
        BufferedReader reader=null;
        String temp=null;
        try{
            reader=new BufferedReader(new FileReader(file));
            while((temp=reader.readLine())!=null){
                String[] one_book = temp.split("\t");
                if(one_book[0].equals(root)){
                    return one_book[1]+"\t"+one_book[2];
                }
                System.out.println(temp);
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
        return "";
    }



}
