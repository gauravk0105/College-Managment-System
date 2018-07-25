package com.example.dell.sphinx_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Student_Subject_Activity extends AppCompatActivity {

    TextView tv1,tv2;
    ListView lv1;
    List<SubjectManager> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__subject_);
        SharedPreferences pref = getSharedPreferences("User", MODE_PRIVATE);

        lv1=(ListView) findViewById(R.id.subjectlist);
        tv2 = (TextView) findViewById(R.id.no);
        tv1 = (TextView) findViewById(R.id.session);
        String s = pref.getString("user", "n");
        String s1 = pref.getString("id", "n");
        String s2 = pref.getString("pwd", "n");
        List<StudentManager> lst = StudentManager.getRecords(getApplicationContext(), "SELECT * FROM studentmaster WHERE rollno='"+s1+"'");

        try{
        String batchid=lst.get(0).getBatchid();
        String courseid=lst.get(0).getCourseid();

        tv1.setText("BatchID : "+batchid+" CourseId : "+courseid);
        list = SubjectManager.getRecords(getApplicationContext(), "SELECT * FROM subjectmaster WHERE batchid='"+batchid+"' and courseid='"+courseid+"'");

        if( list == null || list.size()==0 )
        {
            tv2.setText("NO Subjects");
            return;
        }

        SubAdapter aa = new SubAdapter(getApplicationContext(), R.layout.teachersubjectview, list);
        lv1.setAdapter(aa);

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent i = new Intent(getApplicationContext(),Attendance_Status_Activity.class);
                i.putExtra("bid", list.get(position).getBatchid());
                i.putExtra("cid",list.get(position).getCourseid());
                i.putExtra("sid",list.get(position).getSubjectid());
                startActivity(i);
            }
        });
        }
        catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(),ex.toString(),Toast.LENGTH_LONG).show();
        }
    }
}
