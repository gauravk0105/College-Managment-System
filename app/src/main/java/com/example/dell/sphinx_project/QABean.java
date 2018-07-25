package com.example.dell.sphinx_project;

/**
 * Created by Dell on 16-01-2018.
 */

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

public class QABean {
    private int questionno,subjectid,topicid;
    private String question,answer;
    public int getQuestionno() {
        return questionno;
    }
    public void setQuestionno(int questionno) {
        this.questionno = questionno;
    }
    public int getSubjectid() {
        return subjectid;
    }
    public void setSubjectid(int subjectid) {
        this.subjectid = subjectid;
    }
    public int getTopicid() {
        return topicid;
    }
    public void setTopicid(int topicid) {
        this.topicid = topicid;
    }
    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public String getAnswer() {
        return answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    public static List<QABean> getRecords(Context context,String qry)
    {
        JSONArray arr;
        List<QABean> list=new ArrayList<QABean>();
        try {
            arr = DataManager.executeQuery(context, Common.getUrl(), qry);
            if(arr!=null)
                for(int i=0;i<arr.length();i++)
                {
                    JSONObject row=arr.getJSONObject(i);
                    QABean object=new QABean();
                    object.setSubjectid(Integer.parseInt(row.getString("subjectid")));
                    object.setTopicid(Integer.parseInt(row.getString("topicid")));
                    object.setQuestionno(Integer.parseInt(row.getString("questionno")));
                    object.setQuestion(row.getString("question"));
                    switch(Integer.parseInt(row.getString("answer")))
                    {
                        case 1:object.setAnswer(row.getString("option1"));break;
                        case 2:object.setAnswer(row.getString("option2"));break;
                        case 3:object.setAnswer(row.getString("option3"));break;
                        case 4:object.setAnswer(row.getString("option4"));break;
                    }


                    list.add(object);
                }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            //Log.e("ERROR::QABEan",e.toString());
        }
        return list;


    }

}

