package com.example.dell.sphinx_project;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class TutorialActivity extends Activity {

    WebView wv;
    String subjectid,topicid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        subjectid=getIntent().getStringExtra("subjectid");
        topicid=getIntent().getStringExtra("topicid");

        String doc="<iframe src='https://docs.google.com/viewer?url=http://developengineer.in/notes/"+subjectid+"_"+topicid+".pdf&embedded=true' width='100%' height='100%' style='border:none' ></iframe>";
        wv=(WebView)findViewById(R.id.webView1);
        wv.getSettings().setJavaScriptEnabled(true);
       //wv.getSettings().setPluginsEnabled(true);
        wv.getSettings().setPluginState(WebSettings.PluginState.ON);
        wv.loadData(doc,"text/html","utf-8");
    }
}

