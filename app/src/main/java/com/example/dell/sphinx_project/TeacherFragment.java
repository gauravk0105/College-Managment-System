package com.example.dell.sphinx_project;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Dell on 18-01-2018.
 */

public class TeacherFragment extends Fragment
{
    Button btn;
    EditText uid,pass1,conpas,contactno,uname;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.fragment2,container,false);
        btn=(Button) root.findViewById(R.id.btn10);
        uid=(EditText) root.findViewById(R.id.editText1);
        pass1=(EditText) root.findViewById(R.id.editText2);
        conpas=(EditText) root.findViewById(R.id.editText3);
        contactno=(EditText) root.findViewById(R.id.editText4);
        uname=(EditText) root.findViewById(R.id.editText5);
        uid.setText("");
        pass1.setText("");
        conpas.setText("");
        contactno.setText("");
        uname.setText("");
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
                            if(!TeacherManager.validate(getActivity(),uid.getText().toString(),pass1.getText().toString())) {
                                TeacherManager row = new TeacherManager();
                                row.setTeacherid(uid.getText().toString());
                                row.setTname(uname.getText().toString());
                                row.setContact(contactno.getText().toString());
                                row.setGender("male");
                                row.setPassword(pass1.getText().toString());
                                row.insertRecord(getActivity());
                                Toast.makeText(getActivity(), "Register Successfully", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(getActivity(), login_activity.class);
                                startActivity(i);
                            }
                            else
                            {
                                Toast.makeText(getActivity(),"Already Register",Toast.LENGTH_LONG).show();
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
