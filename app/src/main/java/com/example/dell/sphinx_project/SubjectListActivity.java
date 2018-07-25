package com.example.dell.sphinx_project;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class SubjectListActivity extends Activity {
    ExpandableListView lv;
    List<SubjectBean> grouplist;
    Map<SubjectBean,List<TopicBean>> itemlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_list);

        prepareData();

        lv=(ExpandableListView)findViewById(R.id.expandableListView1);
        SubjectListAdapter ad=new SubjectListAdapter(SubjectListActivity.this, grouplist, itemlist);
        lv.setAdapter(ad);
        lv.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int i, int j, long id) {
                //Toast.makeText(getApplicationContext(), grouplist.get(i).getSubjectid()+" = "+itemlist.get(grouplist.get(i)).get(j).getTopicid(), Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getApplicationContext(),StartPaperActivity.class);
                intent.putExtra("subjectid",""+grouplist.get(i).getSubjectid());
                intent.putExtra("topicid",""+itemlist.get(grouplist.get(i)).get(j).getTopicid());
                startActivity(intent);
                return false;
            }
        });

    }

    public void prepareData()
    {

        try {
            grouplist=SubjectBean.getRecords(getApplicationContext(), "select * from quiz_subjectmaster");
            itemlist=new LinkedHashMap<SubjectBean, List<TopicBean>>();



            for(int i=0;i<grouplist.size();i++)
            {

                int subjectid=grouplist.get(i).getSubjectid();

                List childlist=TopicBean.getRecords(getApplicationContext(), "select * from quiz_topicsmaster where subjectid="+subjectid);

                itemlist.put(grouplist.get(i), childlist);
            }
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



}

