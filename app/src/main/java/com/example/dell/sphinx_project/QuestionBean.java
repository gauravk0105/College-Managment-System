package com.example.dell.sphinx_project;

/**
 * Created by Dell on 16-01-2018.
 */

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;



public class QuestionBean {
    Context ctx;
    private int questionno,answer,subjectid,topicid;
    private String question,option1,option2,option3,option4,description;


    public static List<QuestionBean> getRecords(final Context context,final Activity activity,String qry) throws Exception
    {
        try
        {

            JSONArray data=DataManager.executeQuery(context,Common.getUrl(), qry);
            if (data == null)
                return null;
            List<QuestionBean> memberlist=new ArrayList<QuestionBean>();
            for(int i=0;i<data.length();i++)
            {
                QuestionBean member=new QuestionBean();
                member.fillData(context,data.getJSONObject(i));
                memberlist.add(member);
            }
            return memberlist;
        }
        catch(Exception ex)
        {
            Log.e("Member::GetRecord",ex.toString());
        }
        return null;
    }
    private void fillData(Context context,JSONObject object)
    {	ctx=context;

        try {

            if(object.has("questionno"))
                this.questionno=object.getInt("questionno");
            if(object.has("question"))
                this.question=object.getString("question");
            if(object.has("option1"))
                this.option1 =object.getString("option1");
            if(object.has("option2"))
                this.option2=object.getString("option2");
            if(object.has("option3"))
                this.option3 =object.getString("option3");
            if(object.has("option4"))
                this.option4=object.getString("option4");
            if(object.has("answer"))
                this.answer=object.getInt("answer");
            if(object.has("description"))
                this.description =object.getString("description");
            if(object.has("subjectid"))
                this.subjectid=object.getInt("subjectid");
            if(object.has("topicid"))
                this.topicid=object.getInt("topicid");
        } catch (JSONException e) {
           // Log.d(":::::::NEWS CLASS:::::::",e.toString());
            //e.printStackTrace();
        }

    }
    public int getQuestionno() {
        return questionno;
    }
    public void setQuestionno(int questionno) {
        this.questionno = questionno;
    }
    public int getAnswer() {
        return answer;
    }
    public void setAnswer(int answer) {
        this.answer = answer;
    }
    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public String getOption1() {
        return option1;
    }
    public void setOption1(String option1) {
        this.option1 = option1;
    }
    public String getOption2() {
        return option2;
    }
    public void setOption2(String option2) {
        this.option2 = option2;
    }
    public String getOption3() {
        return option3;
    }
    public void setOption3(String option3) {
        this.option3 = option3;
    }
    public String getOption4() {
        return option4;
    }
    public void setOption4(String option4) {
        this.option4 = option4;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
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



}

