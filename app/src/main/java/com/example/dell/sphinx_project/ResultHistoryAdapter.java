package com.example.dell.sphinx_project;

import android.widget.BaseAdapter;

/**
 * Created by Dell on 16-01-2018.
 */

import java.lang.reflect.Member;
import java.util.Calendar;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class ResultHistoryAdapter extends BaseAdapter {
    int layout;
    Context context;
    List<ResultBean> list;
    String givenanswers[];
    public ResultHistoryAdapter(Context context,int layout,List<ResultBean> list) {
        this.layout=layout;
        this.context=context;
        this.list=list;
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
        TextView tv5=(TextView)rootview.findViewById(R.id.textView5);
        TextView tv6=(TextView)rootview.findViewById(R.id.textView6);

        float per=(float)list.get(position).getCorrect()/list.get(position).getTotal()*100;
        Calendar cal=Calendar.getInstance();
        cal.setTimeInMillis(list.get(position).getDaytime());
        String datetime=cal.get(Calendar.DATE)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.YEAR)+"   "+cal.get(Calendar.HOUR)+":"+cal.get(Calendar.MINUTE);

        tv6.setText("["+datetime+"]");
        tv1.setText(""+list.get(position).getTotal());
        tv2.setText(""+list.get(position).getCorrect());
        tv3.setText(""+list.get(position).getWrong());
        tv4.setText(""+list.get(position).getNa());
        tv5.setText(""+per+"%");




        return rootview;
    }

}
