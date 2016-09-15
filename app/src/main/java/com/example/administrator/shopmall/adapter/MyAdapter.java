package com.example.administrator.shopmall.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.shopmall.R;
import com.example.administrator.shopmall.bean.InfoTest;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2016/9/13.
 */
public class MyAdapter extends BaseAdapter {
    List<InfoTest.DataBean.ItemsBean> list;
    Context context;
    public MyAdapter(List<InfoTest.DataBean.ItemsBean> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item,null,false);
            viewHolder=new ViewHolder();
            viewHolder.imageView= (ImageView) convertView.findViewById(R.id.imageView);
            viewHolder.description= (TextView) convertView.findViewById(R.id.description);
            viewHolder.price= (TextView) convertView.findViewById(R.id.price);
            viewHolder.origin_price= (TextView) convertView.findViewById(R.id.origin_price);
            viewHolder.sales= (TextView) convertView.findViewById(R.id.sales);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.description.setText(list.get(position).getComponent().getDescription());
        viewHolder.price.setText("¥"+list.get(position).getComponent().getPrice());
        viewHolder.sales.setText("销量"+list.get(position).getComponent().getSales());
        //设置下划线
        viewHolder.origin_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        viewHolder.origin_price.setText("原¥"+list.get(position).getComponent().getOrigin_price());
        Picasso.with(context).load(list.get(position).getComponent().getPicUrl()).resize(300, 400).centerCrop().placeholder(R.mipmap.ic_launcher).into(viewHolder.imageView);

        return convertView;
    }

    class ViewHolder{
        ImageView imageView;
        TextView description;
        TextView price;
        TextView origin_price;
        TextView sales;

    }
}
