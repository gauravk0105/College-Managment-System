package com.example.dell.sphinx_project;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Signin_Activity extends FragmentActivity
{
    String value;
    boolean check=false;
    StudentFragment frg2;
    TeacherFragment frg3;
    RadioGroup rg1;
    FrameLayout v;
    FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_);
        rg1=(RadioGroup) findViewById(R.id.rg1);
        //v=(LinearLayout) findViewById(R.id.v1);

        frg2=new StudentFragment();
        frg3=new TeacherFragment();
        final FragmentManager manager=getSupportFragmentManager();
        transaction=manager.beginTransaction();

        transaction.add(R.id.My_Container_2_ID, frg2, "Frag_Bottom_tag");
        transaction.commit();

        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                RadioButton rb=(RadioButton)findViewById(checkedId);

                Toast.makeText(getApplicationContext(), rb.getText(), Toast.LENGTH_SHORT).show();
                if(rb.getText().equals(("Teacher")))
                {
                    transaction=manager.beginTransaction();
                    transaction.replace(R.id.My_Container_2_ID, frg3,"Frag_Bottom_tag2");
                    transaction.addToBackStack(null);
                    transaction.commit();

                }
                else
                {
                    transaction=manager.beginTransaction();
                    transaction.replace(R.id.My_Container_2_ID, frg2,"Frag_Bottom_tag");
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            }
        });
            /*
            transaction=manager.beginTransaction();
            transaction.replace(R.id.My_Container_2_ID, frg3,"jjj");
            transaction.addToBackStack(null);

// Commit the transaction
            transaction.commit();*/

    }
}