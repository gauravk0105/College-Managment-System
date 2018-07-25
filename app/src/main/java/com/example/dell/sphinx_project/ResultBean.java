package com.example.dell.sphinx_project;

/**
 * Created by Dell on 16-01-2018.
 */

public class ResultBean {
    private String emailid;
    private long subjectid,topicid,total,correct,wrong,na,daytime;

    public String getEmailid() {
        return emailid;
    }
    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }
    public long getSubjectid() {
        return subjectid;
    }
    public void setSubjectid(long subjectid) {
        this.subjectid = subjectid;
    }
    public long getTopicid() {
        return topicid;
    }
    public void setTopicid(long topicid) {
        this.topicid = topicid;
    }
    public long getTotal() {
        return total;
    }
    public void setTotal(long total) {
        this.total = total;
    }
    public long getCorrect() {
        return correct;
    }
    public void setCorrect(long correct) {
        this.correct = correct;
    }
    public long getWrong() {
        return wrong;
    }
    public void setWrong(long wrong) {
        this.wrong = wrong;
    }
    public long getNa() {
        return na;
    }
    public void setNa(long na) {
        this.na = na;
    }
    public long getDaytime() {
        return daytime;
    }
    public void setDaytime(long daytime) {
        this.daytime = daytime;
    }

}
