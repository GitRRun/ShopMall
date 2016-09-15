package com.example.administrator.shopmall.shopavitivty;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.example.administrator.shopmall.MainActivity;
import com.example.administrator.shopmall.R;
import com.example.administrator.shopmall.adapter.MyAdapter;
import com.example.administrator.shopmall.bean.InfoTest;
import com.example.administrator.shopmall.fragment.DressesFragment;
import com.example.administrator.shopmall.http.IoUnits;
import com.google.gson.Gson;

import java.util.List;

public class FristActivity extends AppCompatActivity {
    GridView  listView ;
    ActionBar actionBar;
    String path = "http://m.hichao.com/lib/interface.php?m=goodsdetail&sid=";
    List<InfoTest.DataBean.ItemsBean>  list=null;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg!=null&&msg.what==0){
                list= (List<InfoTest.DataBean.ItemsBean>) msg.obj;
                MyAdapter myAdapter=new MyAdapter(list,FristActivity.this );
                listView.setAdapter(myAdapter);
            }

        }
    };
    //标题箭头


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frist);
        //设置标题栏

        actionBar = getSupportActionBar();
        actionBar.setTitle("列表详情");
        actionBar.setDisplayHomeAsUpEnabled(true);
        listView = (GridView) findViewById(R.id.listView);
        Intent intent =getIntent();
    final   String url =  intent.getStringExtra("pos");
        //三级页面监听事件

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String sid = list.get(position).getComponent().getAction().getSourceId();
                String url = path + sid;
                Log.e("===", "uel===" + url);
                Intent intent1 = new Intent(FristActivity.this, SecondActivity.class);
                intent1.putExtra("pos", url);
                startActivity(intent1);

            }
        });



        new Thread(new Runnable() {
            @Override
            public void run() {
              String data=  IoUnits.getData(url);
                Gson gson=new Gson();
              InfoTest infoTest=  gson.fromJson(data, InfoTest.class);
               list =infoTest.getData().getItems();
                Log.e("===","555555"+list);
                handler.obtainMessage(0,list).sendToTarget();
            }
        }).start();
    }


}
