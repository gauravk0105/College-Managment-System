package com.example.dell.sphinx_project;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.ListView;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    String subjectid,topicid,givenAnswers[];
    List<QuestionBean>questionlist;
    TextView tvtotal,tvcorrect,tvwrong,tvpercentage,tvnotattempted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ListView lv=(ListView)findViewById(R.id.listView1);
        tvtotal=(TextView)findViewById(R.id.tvTotal);
        tvcorrect=(TextView)findViewById(R.id.tvCorrect);
        tvwrong=(TextView)findViewById(R.id.tvWrong);
        tvnotattempted=(TextView)findViewById(R.id.tvNotAttempted);
        tvpercentage=(TextView)findViewById(R.id.tvPercentage);

        subjectid=getIntent().getStringExtra("subjectid");
        topicid=getIntent().getStringExtra("topicid");
        givenAnswers=getIntent().getStringArrayExtra("ga");
        try {
            questionlist=QuestionBean.getRecords(getApplicationContext(), this, "select * from quiz_questionbank where subjectid="+subjectid+" and topicid="+topicid);
            SharedPreferences pref1=getSharedPreferences("MyPref",Context.MODE_PRIVATE);
            String emailid1=pref1.getString("emailid", "");
            MyDataBaseHelper dbh1=new MyDataBaseHelper(getApplicationContext());
            SQLiteDatabase db1=dbh1.getReadableDatabase();
            Cursor cursor=db1.rawQuery("select qno from questioncount where emailid='"+emailid1+"' group by qno having count(emailid)>1",null);
            List<String>flist=new ArrayList<String>();
            if(cursor!=null)
            {
                while(cursor.moveToNext())
                {
                    flist.add(cursor.getString(0));
                }
                for(int count1=0;count1<questionlist.size();count1++)
                    for(int count2=0;count2<flist.size();count2++)
                        if(flist.get(count2).equals(questionlist.get(count1).getQuestionno()+""))
                            questionlist.remove(count1);
            }

            ResultAdapter adapter=new ResultAdapter(getApplicationContext(), R.layout.result_item_view, questionlist, givenAnswers);
            lv.setAdapter(adapter);
            int total=questionlist.size();
            int correct=0,wrong=0,na=0;
            for(int i=0;i<questionlist.size();i++)
                if(givenAnswers[i]==null)
                    na++;
                else
                if(questionlist.get(i).getAnswer()==Integer.parseInt(givenAnswers[i]))
                {	correct++;
                    SharedPreferences pref=getSharedPreferences("MyPref",Context.MODE_PRIVATE);
                    String emailid=pref.getString("emailid", "");
                    MyDataBaseHelper dbh=new MyDataBaseHelper(getApplicationContext());
                    SQLiteDatabase db=dbh.getWritableDatabase();
                    db.execSQL("insert into questioncount values('"+emailid+"','"+questionlist.get(i).getQuestionno()+"')");
                }
                else
                    wrong++;

            MyDataBaseHelper dbh=new MyDataBaseHelper(getApplicationContext());

            SQLiteDatabase db=dbh.getWritableDatabase();
            long daytime=System.currentTimeMillis();
            String emailid=getSharedPreferences("MyPref", Context.MODE_PRIVATE).getString("emailid", "");
            db.execSQL("insert into resultmaster values("+subjectid+","+topicid+",'"+emailid+"',"+total+","+correct+","+wrong+","+na+","+daytime+")");
            tvtotal.setText(total+"");
            tvcorrect.setText(correct+"");
            tvwrong.setText(wrong+"");
            tvnotattempted.setText(na+"");
            tvpercentage.setText(""+((float)correct/total*100));


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }



}

