package com.example.dell.sphinx_project;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Dell on 18-01-2018.
 */

public class StudentFragment extends Fragment
{
    Button btn;
    EditText uid,pass1,conpas,contactno,uname;
    Spinner s1,s2;
    EditText id,pass,conpass,user;
    String[] batchid = {"A1","A2","B1","B2"};
    String[] courseid = {"CSE","ECE"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.fragment1,container,false);
        btn=(Button) root.findViewById(R.id.btn10);
        uid=(EditText) root.findViewById(R.id.editText1);
        pass1=(EditText) root.findViewById(R.id.editText2);
        conpas=(EditText) root.findViewById(R.id.editText3);
        contactno=(EditText) root.findViewById(R.id.editText4);
        uname=(EditText) root.findViewById(R.id.editText5);
        s1=(Spinner) root.findViewById(R.id.s1);
        s2=(Spinner) root.findViewById(R.id.s2);
        uid.setText("");
        pass1.setText("");
        conpas.setText("");
        contactno.setText("");
        uname.setText("");
        ArrayAdapter<String> aa1 = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,batchid);
        s1.setAdapter(aa1);
        aa1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        final String bid = s1.getSelectedItem().toString();

        //courseid = StudentManager.getCourseID(getActivity(),"select * from studentmaster");
        ArrayAdapter<String> aa2 = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,courseid);
        s2.setAdapter(aa2);
        aa2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        final String cid = s2.getSelectedItem().toString();

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if(uid.getText().toString().equals("") || conpas.getText().toString().equals("") || uid.getText().toString().equals("")
                        || contactno.getText().toString().equals("") || uname.getText().toString().equals("") ||
                        pass1.getText().toString().equals(""))
                {
                    Toast.makeText(getActivity(),"No Text Area Remain Empty",Toast.LENGTH_LONG).show();
                }
                else
                {
                    if(!conpas.getText().toString().equals(pass1.getText().toString()))
                    {
                        Toast.makeText(getActivity(),"Password And Confirm Password Not Matches",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        try {
                            if(!StudentManager.validate(getActivity(),uid.getText().toString(),pass1.getText().toString())) {
                                StudentManager row = new StudentManager();
                                row.setRollno(uid.getText().toString());
                                row.setBatchid(bid);
                                row.setCourseid(cid);
                                row.setSname(uname.getText().toString());
                                row.setContact(contactno.getText().toString());
                                row.setGender("male");
                                row.setFname("Papa");
                                row.setPassword(pass1.getText().toString());
                                row.insertRecord(getActivity());
                                Toast.makeText(getActivity(), "Register Successfully", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(getActivity(), login_activity.class);
                                startActivity(i);
                            }
                            else
                            {
                                Toast.makeText(getActivity(), "Already Register", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(getActivity(), login_activity.class);
                                startActivity(i);
                            }
                        }
                        catch (Exception ex)
                        {
                            Toast.makeText(getActivity(),"Fails",Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });
        return root;
    }
}
