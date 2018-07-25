package com.example.dell.sphinx_project;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;

public class QAActivity extends AppCompatActivity {
    ExpandableListView lv;
    List<QABean>qalist;
    List<String> grouplist;
    Map<String,List<String>> itemlist;

    String subjectid,topicid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qa);

        subjectid=getIntent().getStringExtra("subjectid");
        topicid=getIntent().getStringExtra("topicid");

        prepareData();

        lv=(ExpandableListView)findViewById(R.id.expandableListView1);
        QAListAdapter ad=new QAListAdapter(QAActivity.this, grouplist, itemlist);
        lv.setAdapter(ad);
//		lv.setOnChildClickListener(new OnChildClickListener() {
//
//			@Override
//			public boolean onChildClick(ExpandableListView parent, View v,
//					int i, int j, long id) {
//
//					Intent intent=new Intent(getApplicationContext(),StartPaperActivity.class);
//					intent.putExtra("subjectid",""+grouplist.get(i).getSubjectid());
//					intent.putExtra("topicid",""+itemlist.get(grouplist.get(i)).get(j).getTopicid());
//					startActivity(intent);
//				return false;
//			}
//		});

    }

    public void prepareData()
    {

        try {
            qalist=QABean.getRecords(getApplicationContext(), "select * from quiz_questionbank where subjectid="+subjectid+" and topicid="+topicid);
            grouplist=new ArrayList<String>();
            itemlist=new LinkedHashMap<String, List<String>>();



            for(int i=0;i<qalist.size();i++)
            {

                String question=qalist.get(i).getQuestion();
                grouplist.add(question);
                List<String> childlist=new ArrayList<String>();
                childlist.add(qalist.get(i).getAnswer());
                itemlist.put(grouplist.get(i), childlist);
            }
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

