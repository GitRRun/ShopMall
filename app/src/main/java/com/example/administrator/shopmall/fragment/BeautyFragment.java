package com.example.administrator.shopmall.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.administrator.shopmall.R;
import com.example.administrator.shopmall.bean.Dress;
import com.example.administrator.shopmall.adapter.AdapterButify;
import com.example.administrator.shopmall.http.IoUnits;
import com.example.administrator.shopmall.parse.Parse;
import com.example.administrator.shopmall.shopavitivty.FristActivity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BeautyFragment extends Fragment {
    GridView gridView;
    String path="http://api-v2.mall.hichao.com/category/list?ga=%2Fcategory%2Flist";
    List<Dress> list;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg!=null&&msg.what==1){
                list = (List<Dress>) msg.obj;
                AdapterButify adapter=new AdapterButify(getActivity(),list);
                gridView.setAdapter(adapter);
            }
        }
    };

    public BeautyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_beauty, container, false);
        gridView = (GridView) view.findViewById(R.id.gridView);
        new Thread(new Runnable() {
            @Override
            public void run() {
                list = Parse.getStr(IoUnits.getData(path),7);
                handler.obtainMessage(1,list).sendToTarget();


            }
        }).start();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(getActivity(),.getWord(),Toast.LENGTH_SHORT).show();

                String url = list.get(position).getQuery();
                Log.e("===","3333"+url);

                try {
                    String  s = URLEncoder.encode(url,"utf-8");
                    Log.e("===","ssssssss"+s);
                    String path="http://api-v2.mall.hichao.com/search/skus?query="+s+"&sort=all&ga=%252Fsearch%252Fskus&flag=&cat=&asc=1";
                    Intent intent=new Intent(getActivity(), FristActivity.class);
                    intent.putExtra("pos",path);
                    startActivity(intent);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }





            }
        });

        // gridView.setAdapter(adapter);

        return view;
    }

}