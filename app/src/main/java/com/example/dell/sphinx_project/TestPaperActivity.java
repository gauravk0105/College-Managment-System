package com.example.dell.sphinx_project;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class TestPaperActivity extends AppCompatActivity {

    List<QuestionBean>questionlist;
    int attempted=0;
    String givenAnswers[];
    WebView wv;
    Button btnprev,btnnext,btnend;
    int position=0;
    String subjectid,topicid;
    TextView tv1,tv2;
    CountDownTimer cdt;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_paper);

        //Toast.makeText(getApplicationContext(),"NO QUIZ ON THIS TOPIC YET",Toast.LENGTH_LONG).show();

        subjectid = getIntent().getStringExtra("subjectid");
        topicid = getIntent().getStringExtra("topicid");

        try {
            List<QuestionBean> lst = QuestionBean.getRecords(getApplicationContext(), TestPaperActivity.this, "select * from quiz_questionbank where subjectid=" + subjectid + " and topicid=" + topicid);


            wv = (WebView) findViewById(R.id.WebView1);
            WebSettings ws = wv.getSettings();
            ws.setJavaScriptEnabled(true);
            wv.addJavascriptInterface(new Object() {
                @JavascriptInterface
                public void performClick(String str1) {
                    givenAnswers[position] = str1;
                    //Toast.makeText(getApplicationContext(), str1,Toast.LENGTH_LONG).show();
                }
            }, "btn");

            tv1 = (TextView) findViewById(R.id.textView1);
            tv2 = (TextView) findViewById(R.id.textView2);
            btnprev = (Button) findViewById(R.id.button1);
            btnnext = (Button) findViewById(R.id.button2);
            btnend = (Button) findViewById(R.id.button3);

            btnprev.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    position = position > 0 ? position - 1 : 0;

                    loadQuestion();
                }
            });
            btnnext.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    position++;
                    position = position == questionlist.size() ? position - 1 : position;
                    loadQuestion();
                }
            });
            btnend.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    cdt.cancel();
                    Intent i = new Intent(getApplicationContext(), ResultActivity.class);
                    i.putExtra("ga", givenAnswers);
                    i.putExtra("subjectid", subjectid);
                    i.putExtra("topicid", topicid);
                    startActivity(i);
                    finish();
                }
            });
            try {
                questionlist = QuestionBean.getRecords(getApplicationContext(), this, "select * from quiz_questionbank where subjectid=" + subjectid + " and topicid=" + topicid);
                SharedPreferences pref = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
                String emailid = pref.getString("emailid", "");
                MyDataBaseHelper dbh = new MyDataBaseHelper(getApplicationContext());
                SQLiteDatabase db = dbh.getReadableDatabase();
                Cursor cursor = db.rawQuery("select qno from questioncount where emailid='" + emailid + "' group by qno having count(emailid)>1", null);
                List<String> flist = new ArrayList<String>();
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        flist.add(cursor.getString(0));
                    }
                    for (int count1 = 0; count1 < questionlist.size(); count1++)
                        for (int count2 = 0; count2 < flist.size(); count2++)
                            if (flist.get(count2).equals(questionlist.get(count1).getQuestionno() + ""))
                                questionlist.remove(count1);
                }
                givenAnswers = new String[questionlist.size()];

                loadQuestion();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            lv = (ListView) findViewById(R.id.listView1);
            lv.setAdapter(new AttemptedAdapter(getApplicationContext(), R.layout.attempted_view, givenAnswers));

            lv.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1,
                                        int arg2, long arg3) {
                    Toast.makeText(getApplicationContext(), "Question " + arg2 + " selected", Toast.LENGTH_LONG).show();
                    position = arg2;
                    loadQuestion();

                }
            });

            cdt = new CountDownTimer(questionlist.size() * 1000 * 60, 1000) {

                @Override
                public void onTick(long msleft) {
                    msleft /= 1000;
                    tv2.setText("Time Left: " + msleft / 60 + ":" + msleft % 60);

                }

                @Override
                public void onFinish() {
                    btnend.performClick();
                }
            }.start();
        }
        catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(),"NO Question Avialable",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(getApplicationContext(),SubjectListActivity.class);
            intent.putExtra("subjectid",subjectid);
            intent.putExtra("topicid",topicid);
            startActivity(intent);
            finish();
        }
        }

    public void loadQuestion() {
        tv1.setText((position + 1) + "/" + questionlist.size());
        if (givenAnswers[position] == null)
            attempted = 0;
        else
            attempted = Integer.parseInt(givenAnswers[position]);
        String s1 = "", s2 = "", s3 = "", s4 = "";
        switch (attempted) {
            case 1:
                s1 = "checked='checked'";
                break;
            case 2:
                s2 = "checked='checked'";
                break;
            case 3:
                s3 = "checked='checked'";
                break;
            case 4:
                s4 = "checked='checked'";
                break;
        }
        //wv.loadData("","text/html", "utf-8");
        String data = "";
        data += "<html>";
        data += "<head>";
        data += "<style>";
        data += "input {width:25px;height:25px} td {padding-top:10px;vertical-align:top}";
        data += "</style>";
        data += "</head>";
        data += "<body>";
        data += "<h3>Qno" + (position + 1) + ": " + questionlist.get(position).getQuestion();
        data += "</h3>";
        data += "<table>";
        data += "<tr><td><input type=radio name=qid" + position + " value='1' onclick='btn.performClick(this.value)' " + s1 + " ></td><td> 1." + questionlist.get(position).getOption1() + "</td></tr>";
        data += "<tr><td><input type=radio name=qid" + position + " value='2' onclick='btn.performClick(this.value)' " + s2 + " ></td><td> 2." + questionlist.get(position).getOption2() + "</td></tr>";
        data += "<tr><td><input type=radio name=qid" + position + " value='3' onclick='btn.performClick(this.value)' " + s3 + " ></td><td> 3." + questionlist.get(position).getOption3() + "</td></tr>";
        data += "<tr><td><input type=radio name=qid" + position + " value='4' onclick='btn.performClick(this.value)' " + s4 + " ></td><td> 4." + questionlist.get(position).getOption4() + "</td></tr>";
        data += "</table>";
        data += "</body> ";
        data += "</html>";

        wv.loadData(data, "text/html", "utf-8");

        if (position == questionlist.size() - 1) {
            btnnext.setVisibility(View.INVISIBLE);
            btnprev.setVisibility(View.VISIBLE);
        } else if (position == 0) {
            btnnext.setVisibility(View.VISIBLE);
            btnprev.setVisibility(View.INVISIBLE);
        } else {
            btnnext.setVisibility(View.VISIBLE);
            btnprev.setVisibility(View.VISIBLE);
        }
        ((AttemptedAdapter) lv.getAdapter()).notifyDataSetChanged();
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "press End Test button to exit", Toast.LENGTH_LONG).show();
    }
}

