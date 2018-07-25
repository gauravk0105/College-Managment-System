package com.example.dell.sphinx_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class serverviewactivity extends AppCompatActivity {
    RadioButton rb1,rb2,rb3,rb4;
    RadioGroup rg;
    WebView wv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serverviewactivity);
            rg=(RadioGroup) findViewById(R.id.radioGroup1);
            rb1=(RadioButton)findViewById(R.id.radio0);
            rb2=(RadioButton)findViewById(R.id.radio1);
            rb3=(RadioButton)findViewById(R.id.radio2);
            rb4=(RadioButton)findViewById(R.id.radio3);
            wv=(WebView) findViewById(R.id.webView1);
            wv.getSettings().setJavaScriptEnabled(true);
            wv.setWebViewClient(new WebViewClient());
            wv.loadUrl("http://www.zine.co.in/sphinix/subject.php");

            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if(checkedId==R.id.radio0)
                        wv.loadUrl("http://www.zine.co.in/sphinix/subject.php");
                    if(checkedId==R.id.radio1)
                        wv.loadUrl("http://www.zine.co.in/sphinix/topic.php");
                    if(checkedId==R.id.radio2)
                        wv.loadUrl("http://www.zine.co.in/sphinix/questionbank.php");
                    if(checkedId==R.id.radio3)
                        wv.loadUrl("http://www.zine.co.in/sphinix/uploadnotes.php");
                }
            });
    }
}