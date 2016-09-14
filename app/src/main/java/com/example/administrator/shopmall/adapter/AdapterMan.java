package com.example.administrator.shopmall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.shopmall.R;
import com.example.administrator.shopmall.bean.Dress;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2016/9/13.
 */
public class AdapterMan extends BaseAdapter {
    Context context;
    List<Dress> stringList;

    public AdapterMan(Context context, List<Dress> stringList) {
        this.context = context;
        this.stringList = stringList;
    }

    @Override
    public int getCount() {
        return stringList.size();
    }

    @Override
    public Object getItem(int position) {
        return stringList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_dresses,null,false);
            viewHolder=new ViewHolder();
            viewHolder.imageView= (ImageView) convertView.findViewById(R.id.imageView);
            viewHolder.textView= (TextView) convertView.findViewById(R.id.textView);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        viewHolder.textView.setText(stringList.get(position).getWord());
        String path = stringList.get(position).getUrl();
        Picasso.with(context).load(path).into(viewHolder.imageView);


        return convertView;
    }


    class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
}
