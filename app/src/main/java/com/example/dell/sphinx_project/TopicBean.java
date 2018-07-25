package com.example.dell.sphinx_project;

/**
 * Created by Dell on 16-01-2018.
 */

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;

public class TopicBean {

    private int topicid,subjectid;
    private String topicname;
    public int getTopicid() {
        return topicid;
    }
    public void setTopicid(int topicid) {
        this.topicid = topicid;
    }
    public int getSubjectid() {
        return subjectid;
    }
    public void setSubjectid(int subjectid) {
        this.subjectid = subjectid;
    }
    public String getTopicname() {
        return topicname;
    }
    public void setTopicname(String topicname) {
        this.topicname = topicname;
    }
    public static List<TopicBean> getRecords(Context context,String qry)
    {
        JSONArray arr;
        List<TopicBean> list=new ArrayList<TopicBean>();
        try {
            arr = DataManager.executeQuery(context, Common.getUrl(), qry);
            if(arr!=null)
                for(int i=0;i<arr.length();i++)
                {
                    JSONObject row=arr.getJSONObject(i);
                    TopicBean object=new TopicBean();
                    object.setSubjectid(Integer.parseInt(row.getString("subjectid")));
                    object.setTopicid(Integer.parseInt(row.getString("topicid")));
                    object.setTopicname(row.getString("topicname"));
                    list.add(object);
                }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;


    }

}
