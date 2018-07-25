package com.example.dell.sphinx_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Dell on 18-01-2018.
 */

public class ChatAdapter extends BaseAdapter
{
    private Context context;
    private List<ChatManager> list;
    private int layout;

    public ChatAdapter(Context context,int layout,List<ChatManager> list)
    {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View arg1, ViewGroup parent)
    {
        // TODO Auto-generated method stub
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View root= inflater.inflate(this.layout, parent,false);
        TextView uname = (TextView) root.findViewById(R.id.textView9);
        TextView date = (TextView) root.findViewById(R.id.textView11);
        TextView time = (TextView) root.findViewById(R.id.textView13);
        TextView msg = (TextView) root.findViewById(R.id.textView14);
        uname.setText(list.get(position).getUname());
        date.setText(list.get(position).getDate());
        time.setText(list.get(position).getTime());
        msg.setText(list.get(position).getMsg());
        return root;
    }
}
