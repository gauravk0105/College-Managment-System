package com.example.dell.sphinx_project;


import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    Button btn;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        pd=new ProgressDialog(ProfileActivity.this);
        pd.setMessage("connecting...");
        pd.setCancelable(true);



        btn=(Button)findViewById(R.id.button1);

//        try
//        {	String emailid=getSharedPreferences("MyPref",Context.MODE_PRIVATE).getString("emailid", "");
//            JSONArray arr=DataManager.executeQuery(getApplicationContext(), Common.getUrl(), "select * from quiz_usermaster where emailid='"+emailid+"'");
//            JSONObject row=arr.optJSONObject(0);
//            tv2.setText("Hello "+row.getString("username"));
//            tv1.setText(row.getString("college"));
//            tv3.setText(row.getString("emailid"));
//            tv4.setText(row.getString("contact"));
//        }
//        catch (Exception e) {
//            // TODO: handle exception
//        }

        btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                pd.show();
                startActivity(new Intent(getApplicationContext(),SubjectListActivity.class));
                pd.hide();
            }
        });
    }

}
