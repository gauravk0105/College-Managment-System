package com.example.dell.sphinx_project;

import android.widget.BaseAdapter;

/**
 * Created by Dell on 16-01-2018.
 */

import java.lang.reflect.Member;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class ResultAdapter extends BaseAdapter {
    int layout;
    Context context;
    List<QuestionBean> list;
    String givenanswers[];
    public ResultAdapter(Context context,int layout,List<QuestionBean> list,String givenAnswers[]) {
        this.layout=layout;
        this.context=context;
        this.list=list;
        this.givenanswers=givenAnswers;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int arg0) {

        return list.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {

        return arg0;
    }

    @Override
    public View getView(int position, View rootview, ViewGroup arg2) {

        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rootview=inflater.inflate(layout, null, false);

        TextView tv1=(TextView)rootview.findViewById(R.id.textView1);
        TextView tv2=(TextView)rootview.findViewById(R.id.textView2);
        TextView tv3=(TextView)rootview.findViewById(R.id.textView3);
        TextView tv4=(TextView)rootview.findViewById(R.id.textView4);

        tv1.setText("QNo "+(position+1)+": "+list.get(position).getQuestion());
        tv2.setText("Description: "+list.get(position).getDescription());
        switch(list.get(position).getAnswer())
        {
            case 1: tv3.setText("CorrectAnswer : "+list.get(position).getOption1());break;
            case 2: tv3.setText("CorrectAnswer : "+list.get(position).getOption2());break;
            case 3: tv3.setText("CorrectAnswer : "+list.get(position).getOption3());break;
            case 4: tv3.setText("CorrectAnswer : "+list.get(position).getOption4());break;
        }
        tv4.setBackgroundColor(context.getResources().getColor(android.R.color.background_dark));
        if(givenanswers[position]==null)
        {	tv4.setTextColor(context.getResources().getColor(android.R.color.darker_gray));
            tv4.setText("Your Answer:   Not Attempted");

        }
        else{
            switch(Integer.parseInt(givenanswers[position]))
            {
                case 1: tv4.setText("Your Answer:   "+list.get(position).getOption1());break;
                case 2: tv4.setText("Your Answer:   "+list.get(position).getOption2());break;
                case 3: tv4.setText("Your Answer:   "+list.get(position).getOption3());break;
                case 4: tv4.setText("Your Answer:   "+list.get(position).getOption4());break;
            }
            if(Integer.parseInt(givenanswers[position])==list.get(position).getAnswer())
                tv4.setTextColor(context.getResources().getColor(android.R.color.holo_green_dark));
            else
                tv4.setTextColor(context.getResources().getColor(android.R.color.holo_red_dark));
        }


        return rootview;
    }

}

