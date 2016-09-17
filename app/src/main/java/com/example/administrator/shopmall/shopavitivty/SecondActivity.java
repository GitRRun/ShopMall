package com.example.administrator.shopmall.shopavitivty;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.shopmall.R;

public class SecondActivity extends AppCompatActivity {
    int i=0;
    WebView webView;
    ProgressBar progressBar;
    ActionBar actionBar;
    Button button1;
    Button button2;
    Button button3;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        webView = (WebView) findViewById(R.id.webview);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("详情");
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        button1= (Button) findViewById(R.id.buy);
        button2 = (Button) findViewById(R.id.store);
        button3= (Button) findViewById(R.id.shopcar);
        textView= (TextView) findViewById(R.id.count);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SecondActivity.this,"未安装支付客户端",Toast.LENGTH_SHORT).show();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SecondActivity.this,"已收藏",Toast.LENGTH_SHORT).show();

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setClickable(true);
                i=i+1;

             textView.setText(String.valueOf(i));

            }
        });
        //接受二级页面传过来的网址
        String url = getIntent().getStringExtra("pos");
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress);
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
        webView.loadUrl(url);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }



}
