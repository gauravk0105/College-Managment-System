package com.example.dell.sphinx_project;

/**
 * Created by Dell on 16-01-2018.
 */

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;

public class SubjectBean {
    private int subjectid;
    private String subjectname;
    public int getSubjectid() {
        return subjectid;
    }
    public void setSubjectid(int subjectid) {
        this.subjectid = subjectid;
    }
    public String getSubjectname() {
        return subjectname;
    }
    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    public static List<SubjectBean> getRecords(Context context,String qry)
    {
        JSONArray arr;
        List<SubjectBean> list=new ArrayList<SubjectBean>();
        try {
            arr = DataManager.executeQuery(context, Common.getUrl(), qry);
            if(arr!=null)
                for(int i=0;i<arr.length();i++)
                {
                    JSONObject row=arr.getJSONObject(i);
                    SubjectBean object=new SubjectBean();
                    object.setSubjectid(Integer.parseInt(row.getString("subjectid")));
                    object.setSubjectname(row.getString("subjectname"));
                    list.add(object);
                }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;


    }

}
