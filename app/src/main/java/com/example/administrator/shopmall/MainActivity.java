package com.example.administrator.shopmall;

import android.app.Dialog;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.RadioGroup;
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
    Dialog dialog;
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
        actionBar.setTitle("首页");
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //标题判断
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    drawerLayout.closeDrawer(Gravity.LEFT);
                } else {
                    drawerLayout.openDrawer(Gravity.LEFT);
                }
            case R.id.share:
                show();
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    //创造对话框
    private void show() {
        dialog = new Dialog(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_item, null);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
       /* AbsListView.LayoutParams parms=new AbsListView.LayoutParams(150,100);
        view.setLayoutParams(parms);*/
        dialog.setContentView(R.layout.dialog_item);
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        p.height = (int) (d.getHeight() * 0.3); // 高度设置为屏幕的0.6
        p.width = (int) (d.getWidth()); // 宽度设置为屏幕的0.65
        dialogWindow.setAttributes(p);
        dialog.show();


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
