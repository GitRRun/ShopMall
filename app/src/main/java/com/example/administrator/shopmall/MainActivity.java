package com.example.administrator.shopmall;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.example.administrator.shopmall.bean.Dress;
import com.example.administrator.shopmall.fragment.ACCFragment;
import com.example.administrator.shopmall.fragment.BeautyFragment;
import com.example.administrator.shopmall.fragment.CoatFragment;
import com.example.administrator.shopmall.fragment.DressesFragment;
import com.example.administrator.shopmall.fragment.GuideFragment;
import com.example.administrator.shopmall.fragment.HandbagsFragment;
import com.example.administrator.shopmall.fragment.MANFragment;
import com.example.administrator.shopmall.fragment.ShoesFragment;
import com.example.administrator.shopmall.fragment.TrousersFragment;
import com.example.administrator.shopmall.fragment.TshirtFragment;
import com.example.administrator.shopmall.http.IoUnits;
import com.example.administrator.shopmall.parse.Parse;

import java.util.List;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    RadioGroup radioGroup;
    FragmentManager fragmentManager;
    ACCFragment accFragment;
    BeautyFragment beautyFragment;
    CoatFragment coatFragment;
    DressesFragment dressesFragment;
    HandbagsFragment  handbagsFragment;
    MANFragment manFragment;
    ShoesFragment shoesFragment;
    TrousersFragment trousersFragment;
    TshirtFragment tshirtFragment;
    ActionBar actionBar;
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
        radioGroup= (RadioGroup) findViewById(R.id.rg);
         fragmentManager = getSupportFragmentManager();
        drawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout);
        //标题栏
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle drawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.app_name,
                R.string.app_name);
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();

        radioGroup.setOnCheckedChangeListener(this);
        //初始化碎片


        fragmentManager.beginTransaction().add(R.id.contianer,dressesFragment).commit();
        fragmentManager.beginTransaction().replace(R.id.guide,new GuideFragment()).commit();

    }


    public void initFragment(){
        if (accFragment==null){
            accFragment=new ACCFragment();
        }
        if (beautyFragment==null){
            beautyFragment=new BeautyFragment();
        }
        if (coatFragment==null){
            coatFragment=new CoatFragment();
        }
        if (dressesFragment==null){
            dressesFragment=new DressesFragment();
        }
        if (handbagsFragment==null){
            handbagsFragment=new HandbagsFragment();
        }
        if (manFragment==null){
            manFragment=new MANFragment();
        }
        if (shoesFragment==null){
            shoesFragment=new ShoesFragment();
        }
        if (trousersFragment==null){
            trousersFragment=new TrousersFragment();
        }
        if (tshirtFragment==null){
            tshirtFragment=new TshirtFragment();
        }
    }




    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
       FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            final String path="http://api-v2.mall.hichao.com/category/list?ga=%2Fcategory%2Flist";
        switch (checkedId){
            case R.id.radioButton:
                fragmentTransaction.replace(R.id.contianer,dressesFragment);
                break;
            case R.id.radioButton2:
               fragmentTransaction.replace(R.id.contianer,tshirtFragment);
                break;
            case R.id.radioButton3:
                fragmentTransaction.replace(R.id.contianer,coatFragment);
                break;
            case R.id.radioButton4:
                fragmentTransaction.replace(R.id.contianer,trousersFragment);
                break;
            case R.id.radioButton5:
                fragmentTransaction.replace(R.id.contianer,shoesFragment);
                break;
            case R.id.radioButton6:
                fragmentTransaction.replace(R.id.contianer,handbagsFragment);
                break;
            case R.id.radioButton7:
                fragmentTransaction.replace(R.id.contianer,accFragment);
                break;
            case R.id.radioButton8:
                fragmentTransaction.replace(R.id.contianer,beautyFragment);
                break;
            case R.id.radioButton9:
                fragmentTransaction.replace(R.id.contianer,manFragment);
                break;

        }
        fragmentTransaction.commit();
    }



}
