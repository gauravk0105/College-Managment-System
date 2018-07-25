package com.example.dell.sphinx_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class login_activity extends AppCompatActivity
{
    Button login,siginup;
    EditText id,pass;
    CheckBox chb;
    RadioButton admin,teacher,student;
    Button forget;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);
        siginup=(Button) findViewById(R.id.button3);
        login = (Button) findViewById(R.id.button1);
        pass = (EditText) findViewById(R.id.EditText2);
        id = (EditText) findViewById(R.id.editText1);
        student = (RadioButton) findViewById(R.id.student);
        forget=(Button) findViewById(R.id.button2);
        teacher = (RadioButton) findViewById(R.id.teacher);
        admin = (RadioButton) findViewById(R.id.admin);
        admin.setVisibility(View.GONE);
        chb=(CheckBox) findViewById(R.id.checkBox1);
        id.setText("");
        pass.setText("");
        chb.setChecked(false);
        SharedPreferences saveLog = getSharedPreferences("SAVE",MODE_PRIVATE);
        String saveLogin=saveLog.getString("mark","no");
        forget.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                try {
                    String emailid = id.getText().toString();
                    String password = pass.getText().toString();
                    if (emailid == null || emailid.equals("")) {
                        Toast.makeText(getApplicationContext(), "Please Enter Email Id", Toast.LENGTH_LONG).show();
                        return;
                    }
                    SharedPreferences pref = getSharedPreferences("User", MODE_PRIVATE);
                    String s = pref.getString("user", "n");
                    String s1 = pref.getString("id", "n");
                    String s2 = pref.getString("pwd", "n");
                    List<StudentManager> list = StudentManager.getRecords(getApplicationContext(), "SELECT * FROM studentmaster WHERE rollno='" + s1 + "'");
                    if (list == null) {
                        Toast.makeText(getApplicationContext(), "Email Id is Not Registered", Toast.LENGTH_LONG).show();
                        return;
                    } else {
                        SmsManager sms = SmsManager.getDefault();
                        String data = "Dear " + list.get(0).getSname() + ", Your EmailId is " + list.get(0).getRollno() + " and your Password is " + list.get(0).getPassword() + " to login quiz_app.";
                        sms.sendTextMessage(list.get(0).getContact(), null, data, null, null);
                        Toast.makeText(getApplicationContext(), "login detail sent to your registered mobile number", Toast.LENGTH_LONG).show();
                        }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                }

            }
        });

        Toast.makeText(getApplicationContext(),saveLogin,Toast.LENGTH_LONG).show();
        if(saveLogin.equals("no"))
        {
            chb.setChecked(false);
            id.setText("");
            pass.setText("");
        }
        else
        {
            chb.setChecked(true);
            SharedPreferences pref = getSharedPreferences("User", MODE_PRIVATE);
            String s = pref.getString("user", "n");
            String s1 = pref.getString("id", "n");
            String s2 = pref.getString("pwd", "n");
            id.setText(s1);
            pass.setText(s2);

        }
        siginup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                try {
                    startActivity(new Intent(getApplicationContext(),Signin_Activity.class));
                    return;
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                }

            }
        });
        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {

                // TODO Auto-generated method stub
                String eid =  id.getText().toString();
                String pwd = pass.getText().toString();
                if(eid.equals(""))
                {
                    Toast.makeText(login_activity.this,"Emai Id || Roll Number Can't Be Remain Empty",Toast.LENGTH_LONG).show();
                    return;
                }
                if(pwd.equals(""))
                {
                    Toast.makeText(login_activity.this,"Password can't Be Empty",Toast.LENGTH_LONG).show();
                    return;
                }

				/*try{
				List<AdminManager> list = AdminManager.getRecords(getApplicationContext(), "SELECT * FROM adminmaster");
				Toast.makeText(getApplicationContext(), list.get(0).getAdminname(), Toast.LENGTH_LONG).show();
				}
				catch (Exception e) {
					Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
				}
					// TODO: handle exception
				}
				*/
                if(admin.isChecked())
                {
                    // validate admin if yes tan launch its profile
                    try{
                        if(AdminManager.validate(login_activity.this, eid, pwd) && ( !StudentManager.validate(getApplicationContext(),eid,pwd)) && !TeacherManager.validate(getApplicationContext(),eid,pwd))
                        {
                            if(chb.isChecked())
                            {
                                SharedPreferences saveLog = getSharedPreferences("SAVE",MODE_PRIVATE);
                                SharedPreferences.Editor edit=saveLog.edit();
                                edit.putString("mark","yes");
                                edit.apply();
                            }
                            else
                            {
                                SharedPreferences saveLog = getSharedPreferences("SAVE",MODE_PRIVATE);
                                SharedPreferences.Editor edit=saveLog.edit();
                                edit.putString("mark","no");
                                edit.apply();
                            }
                            SharedPreferences pref = getSharedPreferences("User", MODE_PRIVATE);
                            SharedPreferences.Editor edit = pref.edit();
                            edit.putString("user", "a");
                            edit.putString("id", eid);
                            edit.putString("pwd", pwd);
                            edit.commit();
                            Toast.makeText(getApplicationContext(), "Found", Toast.LENGTH_LONG).show();
                            // validate admin if yes tan launch its profile
                            Intent i = new Intent(login_activity.this,AdminActivity.class);
                            startActivity(i);
                            finish();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Enter Valid Email ID && Password For Successfully Login For Admin", Toast.LENGTH_LONG).show();
                        }
                    }catch (Exception e) {
                        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                        // TODO: handle exception
                    }
                }
                else
                {
                    if(student.isChecked())
                    {
                        // validate student if yes tan launch its profile
                        try{
                            if(StudentManager.validate(login_activity.this,eid,pwd) && ( !TeacherManager.validate(getApplicationContext(),eid,pwd)) && !AdminManager.validate(getApplicationContext(),eid,pwd))
                            {
                                if(chb.isChecked())
                                {
                                    SharedPreferences saveLog = getSharedPreferences("SAVE",MODE_PRIVATE);
                                    SharedPreferences.Editor edit=saveLog.edit();
                                    edit.putString("mark","yes");
                                    edit.apply();
                                }
                                else
                                {
                                    SharedPreferences saveLog = getSharedPreferences("SAVE",MODE_PRIVATE);
                                    SharedPreferences.Editor edit=saveLog.edit();
                                    edit.remove("mark");
                                    edit.apply();
                                }
                                SharedPreferences pref = getSharedPreferences("User", MODE_PRIVATE);
                                SharedPreferences.Editor edit = pref.edit();
                                edit.putString("user", "s");
                                edit.putString("id", eid);
                                edit.putString("pwd", pwd);
                                edit.commit();
                                Toast.makeText(getApplicationContext(), "Found", Toast.LENGTH_LONG).show();
                                // validate student if yes tan launch its profile
                                Intent i = new Intent(login_activity.this,studentActivity.class);
                                startActivity(i);
                                finish();
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "Enter Valid Roll Number && Password For Successfully Login As Student", Toast.LENGTH_LONG).show();
                            }
                        }catch (Exception e) {
                            Toast.makeText(getApplicationContext(),"Student"+ e.toString(), Toast.LENGTH_LONG).show();
                            // TODO: handle exception
                        }
                    }
                    else
                    {
                        if(teacher.isChecked())
                        {
                            //Toast.makeText(getApplicationContext(),eid+" "+pwd,Toast.LENGTH_LONG ).show();
                            // validate teacher if yes tan launch its profile
                            try{

                                //if(TeacherManager.validate(login_activity.this, eid, pwd))
                                //{
                                  //  Toast.makeText(getApplicationContext(),"TRUE",Toast.LENGTH_LONG ).show();
                                //}
                                if(TeacherManager.validate(login_activity.this, eid, pwd)  && ( !StudentManager.validate(getApplicationContext(),eid,pwd)) && !AdminManager.validate(getApplicationContext(),eid,pwd))
                                {
                                    if(chb.isChecked())
                                    {
                                        SharedPreferences saveLog = getSharedPreferences("SAVE",MODE_PRIVATE);
                                        SharedPreferences.Editor edit=saveLog.edit();
                                        edit.putString("mark","yes");
                                        edit.apply();
                                    }
                                    else
                                    {
                                        SharedPreferences saveLog = getSharedPreferences("SAVE",MODE_PRIVATE);
                                        SharedPreferences.Editor edit=saveLog.edit();
                                        edit.remove("mark");
                                        edit.apply();
                                    }
                                    SharedPreferences pref = getSharedPreferences("User", MODE_PRIVATE);
                                    SharedPreferences.Editor edit = pref.edit();
                                    edit.putString("user", "t");
                                    edit.putString("id", eid);
                                    edit.putString("pwd", pwd);
                                    edit.commit();

                                    Toast.makeText(getApplicationContext(), "Found", Toast.LENGTH_LONG).show();
                                    // validate teacher if yes tan launch its profile
                                    Intent i = new Intent(login_activity.this,teacherActivity.class);
                                    startActivity(i);
                                    finish();
                                }
                                else
                                {

                                    Toast.makeText(getApplicationContext(), "Enter Valid Email ID && Password For Successfully Login For Teacher", Toast.LENGTH_LONG).show();
                                }
                            }catch (Exception e)
                            {
                                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                                // TODO: handle exception
                            }

                        }
                    }
                }
            }
        });

    }
}
