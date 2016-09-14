package com.example.administrator.shopmall.parse;

import com.example.administrator.shopmall.bean.Dress;
import com.example.administrator.shopmall.bean.InfoDome;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/11.
 */
public class Parse {
    public static List<Dress> getStr(String json,int num){
        List<Dress> stringList =new ArrayList<>();

        Gson gson = new Gson();
        InfoDome infoDome = gson.fromJson(json, InfoDome.class);
        List<InfoDome.DataBean.ItemsoneBean> list = infoDome.getData().getItems();
        for (int i=0;i<list.size()/8;i++){
            List<InfoDome.DataBean.ItemsoneBean.ComponentoneBean.ItemstwoBean> list1 = list.get(num).getComponent().getItems();
            for (int j=0;j<list1.size();j++){
                Dress dress=new Dress();
                String url = list1.get(j).getComponent().getPicUrl();
                String word = list1.get(j).getComponent().getWord();
                String query = list1.get(j).getComponent().getAction().getQuery();
                dress.setUrl(url);
                dress.setWord(word);
                dress.setQuery(query);
                stringList.add(dress);
            }
        }
        return stringList;
    }


}
