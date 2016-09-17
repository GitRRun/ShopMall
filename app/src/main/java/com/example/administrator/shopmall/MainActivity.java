package com.example.administrator.shopmall;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.example.administrator.shopmall.callback.CallBack;
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
import com.example.administrator.shopmall.shopavitivty.LoginActivity;
import com.example.administrator.shopmall.shopavitivty.ShopActivity;
import com.example.administrator.shopmall.shopavitivty.StoreActivity;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener,CallBack{
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
    View view;
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
                AlertDialog alertDialog =  show();
                alertDialog.show();

                break;


        }
        return super.onOptionsItemSelected(item);
    }

    //创造对话框
    private AlertDialog  show() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        view = LayoutInflater.from(this).inflate(R.layout.dialog_item,null);
        //给控件设置监听事件
        RadioGroup   radioGroup = (RadioGroup) view.findViewById(R.id.rg);
        RadioGroup   radioGroup1 = (RadioGroup) view.findViewById(R.id.rg1);
       final RadioButton radioButton1 = (RadioButton) view.findViewById(R.id.rb1);
        final RadioButton radioButton2 = (RadioButton) view.findViewById(R.id.rb2);
        final RadioButton radioButton3 = (RadioButton) view.findViewById(R.id.rb3);
        final RadioButton radioButton4 = (RadioButton) view.findViewById(R.id.rb4);
        final RadioButton radioButton5 = (RadioButton) view.findViewById(R.id.rb5);
        final RadioButton radioButton6 = (RadioButton) view.findViewById(R.id.rb6);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==radioButton1.getId()){
                    Toast.makeText(MainActivity.this,"未安装QQ客户端",Toast.LENGTH_SHORT).show();
                }
                if (checkedId==radioButton2.getId()){
                    Toast.makeText(MainActivity.this,"未安装QQ空间客户端",Toast.LENGTH_SHORT).show();
                }
                if (checkedId==radioButton3.getId()){
                    Toast.makeText(MainActivity.this,"未安装新浪客户端",Toast.LENGTH_SHORT).show();
                }
                if (checkedId==radioButton4.getId()){
                    Toast.makeText(MainActivity.this,"未安装腾讯微博客户端",Toast.LENGTH_SHORT).show();
                }
            }
        });

        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==radioButton5.getId()){
                    Toast.makeText(MainActivity.this,"未安装微信客户端",Toast.LENGTH_SHORT).show();
                }
                if (checkedId==radioButton6.getId()){
                    Toast.makeText(MainActivity.this,"未安装微信客户端",Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setView(view);
        AlertDialog alertDialog=builder.create();
        DisplayMetrics displayMetrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        //设置对话框的动画
        Window window = alertDialog.getWindow();
        //设置对话框的背景
        window.setBackgroundDrawableResource(android.R.color.white);
        //设置对话框的窗口与屏幕之间的缝隙
        window.getDecorView().setPadding(0,0,0,0);
        //给窗口设置动画
        window.setWindowAnimations(R.style.dialogAnimation);
        //获取window布局的默认参数
        WindowManager.LayoutParams  layoutParams=window.getAttributes();
        //修改默认布局
        layoutParams.gravity = Gravity.BOTTOM;
        layoutParams.width  =displayMetrics.widthPixels;
       layoutParams.height=displayMetrics.heightPixels/4;

        window.setAttributes(layoutParams);

return alertDialog;

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


    @Override
    public void setFragmentChange(int pos) {
        if (pos==0){
            Intent intent=new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        if (pos==1){
            Intent intent=new Intent(this, StoreActivity.class);
            startActivity(intent);
        }

        if (pos==2){
            Intent intent=new Intent(this, ShopActivity.class);
            startActivity(intent);
        }
        if (pos==3){
            Intent intent=new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void setFragmentClose() {
        drawerLayout.closeDrawer(Gravity.LEFT);
    }


}
