package com.example.dell.sphinx_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Attendance_Status_Activity extends AppCompatActivity {

    int total,present,leave,absent;
    TextView tot,pre,ab,lea,pt;
    double per;
    String bid,cid,sid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance__status_);
        SharedPreferences pref = getSharedPreferences("User", MODE_PRIVATE);

        tot=(TextView) findViewById(R.id.tvTotal);
        pre=(TextView) findViewById(R.id.tvCorrect);
        ab=(TextView) findViewById(R.id.tvWrong);
        lea=(TextView) findViewById(R.id.tvNotAttempted);
        pt=(TextView) findViewById(R.id.tvPercentage);

        String s = pref.getString("user", "n");
        String s1 = pref.getString("id", "n");
        String s2 = pref.getString("pwd", "n");

        Intent i = getIntent();
        bid = i.getStringExtra("bid");
        cid = i.getStringExtra("cid");
        sid = i.getStringExtra("sid");

        try {
            List<AttendanceManager> lst = AttendanceManager.getRecords(getApplicationContext(), "select * from attendancemaster where rollno='" + s1 + "' and subjectid='" + sid + "'");
            if(lst==null)
            {
                total=present=absent=0;
                per=0.0;
                tot.setText(Integer.toString(total));
                pre.setText(Integer.toString(present));
                ab.setText(Integer.toString(absent));
                lea.setText(Integer.toString(leave));
                pt.setText(Double.toString(per)+"%");
            }
            else
            {
                // total class occur
                total= lst.size();
                List<AttendanceManager> lst1 = AttendanceManager.getRecords(getApplicationContext(), "select * from attendancemaster where rollno='" + s1 + "' and subjectid='" + sid + "' and att_status='P'");
                if(lst1==null)
                {
                    present=0;
                }
                else {
                    present = lst1.size();
                }
                List<AttendanceManager> lst2 = AttendanceManager.getRecords(getApplicationContext(), "select * from attendancemaster where rollno='" + s1 + "' and subjectid='" + sid + "' and att_status='A'");
                if(lst2==null)
                {
                    absent=0;
                }
                else {
                    absent = lst2.size();
                }
                List<AttendanceManager> lst3 = AttendanceManager.getRecords(getApplicationContext(), "select * from attendancemaster where rollno='" + s1 + "' and subjectid='" + sid + "' and att_status='L'");
                if(lst3==null)
                {
                    leave=0;
                }
                else {
                    leave = lst3.size();
                }
                per=((double) (present)/(total))*100;
                tot.setText(Integer.toString(total));
                pre.setText(Integer.toString(present));
                ab.setText(Integer.toString(absent));
                lea.setText(Integer.toString(leave));
                pt.setText(Double.toString(per)+"%");
            }

        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(),ex.toString(),Toast.LENGTH_LONG).show();
        }
    }
}
