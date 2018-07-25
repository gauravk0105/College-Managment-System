package com.example.dell.sphinx_project;

import android.widget.BaseAdapter;

/**
 * Created by Dell on 16-01-2018.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AttemptedAdapter extends BaseAdapter{

    int layout;
    Context context;
    String list[];

    public AttemptedAdapter(Context context,int layout,String list[])
    {
        this.layout=layout;
        this.list=list;
        this.context=context;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.length;
    }
    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list[position];
    }
    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(layout,parent,false);

        TextView btn=(TextView)convertView.findViewById(R.id.button1);
        LinearLayout ll=(LinearLayout)convertView.findViewById(R.id.container1);

        btn.setText(""+(pos+1));
        if(list[pos]!=null)
        {	ll.setBackgroundColor(context.getResources().getColor(android.R.color.holo_green_dark));
            btn.setBackgroundColor(context.getResources().getColor(android.R.color.holo_green_dark));
        }
        else
        {	ll.setBackgroundColor(context.getResources().getColor(android.R.color.black));
            btn.setBackgroundColor(context.getResources().getColor(android.R.color.black));
        }

        return convertView;
    }

}