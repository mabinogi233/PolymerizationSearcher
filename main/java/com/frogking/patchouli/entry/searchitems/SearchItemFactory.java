package com.frogking.patchouli.entry.searchitems;


import com.frogking.patchouli.entry.Items.SearchItem;
import com.frogking.patchouli.entry.searchitems.BaiduSearchItem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchItemFactory {


    /**
     * 返回名称指定的搜索元
     * @param searchItemName
     * @return
     */
    public SearchItem getSearchItem(String searchItemName){
        if(searchItemName.equals("baidu")){
            return new BaiduSearchItem();
        }else {
            return null;
        }
    }

    /**
     * 返回一组名称对应的一组搜索元
     * @param searchItemNames
     * @return
     */
    public List<SearchItem> getSearchItems(String[] searchItemNames){
        List<SearchItem> searchItems= new ArrayList<SearchItem>();
        for(int i=0;i<searchItemNames.length;i++){
            searchItems.add(getSearchItem(searchItemNames[i]));
        }
        return searchItems;
    }

}
