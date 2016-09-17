package com.example.administrator.shopmall.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.administrator.shopmall.R;
import com.example.administrator.shopmall.callback.CallBack;

/**
 * A simple {@link Fragment} subclass.
 */
public class GuideFragment extends Fragment  {
    CallBack callBack;
    Button button;



    public GuideFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CallBack) {
            callBack = (CallBack) context;
        }


    }



    @Override
    public View onCreateView(LayoutInflater inflater,final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guide, container, false);
       button= (Button) view.findViewById(R.id.btn_login);
       TextView textView = (TextView) view.findViewById(R.id.store);
        TextView textView2 = (TextView) view.findViewById(R.id.shopcar);
        TextView textView1 = (TextView) view.findViewById(R.id.shophome);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack!=null){
                    callBack.setFragmentChange(0);
                    callBack.setFragmentClose();
                }

            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack!=null){
                    callBack.setFragmentChange(1);
                    callBack.setFragmentClose();
                }
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack!=null){
                    callBack.setFragmentChange(2);
                    callBack.setFragmentClose();
                }
            }
        });

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack!=null){
                    callBack.setFragmentChange(3);
                    callBack.setFragmentClose();
                }
            }
        });

        return view;
    }





}
