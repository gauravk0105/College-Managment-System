package com.example.dell.sphinx_project;

import android.widget.BaseExpandableListAdapter;

/**
 * Created by Dell on 16-01-2018.
 */

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

public class QAListAdapter extends BaseExpandableListAdapter {
    Context context;
    Map<String,List<String>>itemlist;
    List<String> grouplist;
    public QAListAdapter(Context context,List<String> grouplist,Map<String,List<String>> itemlist) {
        this.context=context;
        this.grouplist=grouplist;
        this.itemlist=itemlist;
    }
    @Override
    public Object getChild(int arg0, int arg1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getChildId(int arg0, int arg1) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getChildView(int arg0, int arg1, boolean arg2, View arg3,
                             ViewGroup arg4) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(arg3==null)
            arg3=(View)inflater.inflate(R.layout.child_item,null);
        TextView tv=(TextView)arg3.findViewById(R.id.textView1);
        tv.setText("Ans: "+itemlist.get(grouplist.get(arg0)).get(arg1));
        return arg3;
    }

    @Override
    public int getChildrenCount(int arg0) {
        // TODO Auto-generated method stub
        return itemlist.get(grouplist.get(arg0)).size();
    }

    @Override
    public Object getGroup(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getGroupCount() {
        // TODO Auto-generated method stub
        return grouplist.size();
    }

    @Override
    public long getGroupId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getGroupView(int arg0, boolean arg1, View arg2, ViewGroup arg3) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(arg2==null)
            arg2=inflater.inflate(R.layout.group_item,null);
        TextView tv=(TextView)arg2.findViewById(R.id.textView1);
        tv.setText("Q"+(arg0+1)+": "+grouplist.get(arg0));
        tv.setTextSize(18);
        return arg2;
    }

    @Override
    public boolean hasStableIds() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isChildSelectable(int arg0, int arg1) {
        // TODO Auto-generated method stub
        return true;
    }


}

