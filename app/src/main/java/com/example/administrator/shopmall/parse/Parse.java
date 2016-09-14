package com.example.administrator.shopmall.parse;
import android.util.Log;

import com.example.administrator.shopmall.bean.Dress;
import com.example.administrator.shopmall.bean.Info;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/11.
 */
public class Parse {
    public static List<Dress> getStr(String json,int num){
        List<Dress> stringList =new ArrayList<>();
        Gson gson=new Gson();
       Info info = gson.fromJson(json,Info.class);
        List<Info.DataBean.ItemoneBean> list =info.getData().getItemone();

        for (int i=0;i<list.size()/8;i++){
            List<Info.DataBean.ItemoneBean.ComponentoneBean.ItemtwoBean> list1 =
                    list.get(num).getComponentone().getItemtwo();
            for (int j=0;j<list1.size();j++){
                Dress dress=new Dress();
                String url = list1.get(j).getComponenttwo().getPicUrl();
                String word=list1.get(j).getComponenttwo().getWord();
                    dress.setUrl(url);
                dress.setWord(word);
              Log.e("===","url=="+url);
                Log.e("===","word=="+word);
                stringList.add(dress);
            }
        }


return stringList;

    }
}
