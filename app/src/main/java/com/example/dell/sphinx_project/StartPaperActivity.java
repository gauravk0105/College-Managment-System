package com.example.dell.sphinx_project;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class StartPaperActivity extends AppCompatActivity {

    String username;
    Button btn5;
    WebView wv;
    Button btn1,btn2,btn4;
    String subjectid,topicid;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_paper);
        pd=new ProgressDialog(StartPaperActivity.this);
        btn5=(Button) findViewById(R.id.button5);
        pd.setCancelable(true);
        subjectid=getIntent().getStringExtra("subjectid");
        topicid=getIntent().getStringExtra("topicid");
        SharedPreferences pref = getSharedPreferences("User", MODE_PRIVATE);
        String s = pref.getString("user", "n");
        String s1 = pref.getString("id", "n");
        String s2 = pref.getString("pwd", "n");
        List<StudentManager> list = StudentManager.getRecords(getApplicationContext(), "SELECT * FROM studentmaster WHERE rollno='"+s1+"'");
        username=list.get(0).getSname();

//		TextView tv=(TextView)findViewById(R.id.textView1);
//		tv.setText("Paper ID: "+paperid);
        wv=(WebView) findViewById(R.id.textView2);
        wv.loadUrl("file:///android_asset/paper_int.html");

        btn1=(Button)findViewById(R.id.button1);
        btn1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent=new Intent(getApplicationContext(),TestPaperActivity.class);
                intent.putExtra("subjectid",subjectid);
                intent.putExtra("topicid",topicid);
                startActivity(intent);

            }
        });
        btn2=(Button)findViewById(R.id.button2);
        btn2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                pd.show();
                Intent intent=new Intent(getApplicationContext(),TutorialActivity.class);
                intent.putExtra("subjectid",subjectid);
                intent.putExtra("topicid",topicid);
                startActivity(intent);
                pd.hide();
            }
        });

        btn4=(Button)findViewById(R.id.button4);
        btn4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent=new Intent(getApplicationContext(),ResultHistoryActivity.class);
                intent.putExtra("subjectid",subjectid);
                intent.putExtra("topicid",topicid);
                startActivity(intent);

            }
        });


        // username topicid datetime msg
        btn5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent=new Intent(getApplicationContext(),Post_ACtivity.class);
                intent.putExtra("subjectid",subjectid);
                intent.putExtra("topicid",topicid);
                startActivity(intent);
            }
        });


    }
}
