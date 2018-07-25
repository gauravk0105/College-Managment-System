package com.example.dell.sphinx_project;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.ListView;
import android.widget.TextView;

public class ResultHistoryActivity extends AppCompatActivity {

    String paperid,givenAnswers[];
    List<ResultBean>resultlist;
    TextView tvtotal,tvcorrect,tvwrong,tvpercentage,tvnotattempted;

    String subjectid,topicid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_history);

        ListView lv=(ListView)findViewById(R.id.listView1);
        resultlist=new ArrayList<ResultBean>();

        subjectid=getIntent().getStringExtra("subjectid");
        topicid=getIntent().getStringExtra("topicid");

        try {
            MyDataBaseHelper dbh=new MyDataBaseHelper(getApplicationContext());
            SQLiteDatabase db=dbh.getReadableDatabase();
            String emailid=getSharedPreferences("MyPref", Context.MODE_PRIVATE).getString("emailid", "");
            Cursor cursor=db.rawQuery("select * from resultmaster where emailid='"+emailid+"' and subjectid="+subjectid+" and topicid="+topicid+" order by subjectid,topicid,daytime", null);

            if(cursor!=null)
            {
                while(cursor.moveToNext())
                {	ResultBean result=new ResultBean();

                    result.setSubjectid(Integer.parseInt(cursor.getString(0)));
                    result.setTopicid(Integer.parseInt(cursor.getString(1)));
                    result.setEmailid(cursor.getString(2));
                    result.setTotal(Long.parseLong(cursor.getString(3)));
                    result.setCorrect(Long.parseLong(cursor.getString(4)));
                    result.setWrong(Long.parseLong(cursor.getString(5)));
                    result.setNa(Long.parseLong(cursor.getString(6)));
                    result.setDaytime(Long.parseLong(cursor.getString(7)));

                    resultlist.add(result);
                }
            }

            ResultHistoryAdapter adapter=new ResultHistoryAdapter(getApplicationContext(), R.layout.result_history_item_view, resultlist);
            lv.setAdapter(adapter);


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }



}

