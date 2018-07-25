package com.example.dell.sphinx_project;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 18-01-2018.
 */

public class ChatManager
{
    private int topicid;
    private int subjectid;
    private String date;
    private String time;
    private String uname;
    private String msg;


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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public boolean insertRecord(Context context) throws Exception
    {
        String qry = "INSERT INTO conmaster VALUES( "+topicid+" , "+subjectid+"  , '"+uname+"' , '"+msg+"' , '"+time+"' , '"+date+"' )";
        if(DataManager.executeUpdate(context, Common.getUrl(), qry))
            return true;
        else
            return false;
    }

    public static List<ChatManager> getRecords(Context context, String qry)
    {
        List<ChatManager> list = new ArrayList<ChatManager>();
        try
        {
            JSONArray arr =  DataManager.executeQuery(context, Common.getUrl(),qry);
            if( arr != null&&arr.length()!=0 )
            {
                for(int i=0;i<arr.length();i++)
                {
                    JSONObject object = arr.getJSONObject(i);
                    ChatManager row = new ChatManager();
                    row.setTopicid(object.getInt("topicid"));
                    row.setSubjectid(object.getInt("subjectid"));
                    row.setMsg(object.getString("msg"));
                    row.setUname(object.getString("uname"));
                    row.setDate(object.getString("date"));
                    row.setTime(object.getString("time"));
                    list.add(row);
                }
                return list;
            }
            else{
                return null;
            }
        }
        catch(Exception ex)
        {
            Toast.makeText(context,"Unable To Retrive Data || No Data",Toast.LENGTH_LONG).show();
            return null;
        }
    }

}
