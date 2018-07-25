package com.example.dell.sphinx_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class studentActivity extends AppCompatActivity
{
    TextView profile;
    Button attend,logout,quiz;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        profile=(TextView) findViewById(R.id.profile);
        attend=(Button) findViewById(R.id.button);
        quiz=(Button) findViewById(R.id.button2);
        logout=(Button) findViewById(R.id.logout);
        SharedPreferences pref = getSharedPreferences("User", MODE_PRIVATE);
        String s = pref.getString("user", "n");
        String s1 = pref.getString("id", "n");
        String s2 = pref.getString("pwd", "n");
        List<StudentManager> list = StudentManager.getRecords(getApplicationContext(), "SELECT * FROM studentmaster WHERE rollno='"+s1+"'");
        profile.setText(list.get(0).getSname());
        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(studentActivity.this,ProfileActivity.class);
                startActivity(i);
            }
        });

        attend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(studentActivity.this,Student_Subject_Activity.class);
                startActivity(i);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SharedPreferences pref = getSharedPreferences("User", MODE_PRIVATE);
                SharedPreferences.Editor edit = pref.edit();
                edit.clear();
                edit.commit();
                Intent i =new Intent(getApplicationContext(),login_activity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
