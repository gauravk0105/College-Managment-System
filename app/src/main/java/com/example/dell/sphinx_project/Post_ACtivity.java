package com.example.dell.sphinx_project;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class Post_ACtivity extends AppCompatActivity
{
    ListView lv;
    EditText msg;
    Button post;
    TextView cmnt;
    int subjectid,topicid;
    String sid,tid,username;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post__activity);
        msg=(EditText) findViewById(R.id.msg);
        lv=(ListView) findViewById(R.id.lv1);
        cmnt=(TextView) findViewById(R.id.comment);
        post=(Button) findViewById(R.id.post);
        sid=getIntent().getStringExtra("subjectid");
        tid=getIntent().getStringExtra("topicid");
        subjectid=Integer.parseInt(sid);
        topicid=Integer.parseInt(tid);
        SharedPreferences pref = getSharedPreferences("User", MODE_PRIVATE);
        String s = pref.getString("user", "n");
        String s1 = pref.getString("id", "n");
        String s2 = pref.getString("pwd", "n");
        List<StudentManager> list = StudentManager.getRecords(getApplicationContext(), "SELECT * FROM studentmaster WHERE rollno='"+s1+"'");
        username=list.get(0).getSname();
        msg.setText("");

        try {

            List<ChatManager> lst = ChatManager.getRecords(getApplicationContext(), "select * from conmaster where subjectid=" + subjectid + " and topicid=" + topicid);


            //adapter to show them in list view
            if (lst == null) {
                cmnt.setText("NO COMMENT YET");
                Toast.makeText(getApplicationContext(), "No Comment Yet", Toast.LENGTH_LONG).show();
            }
            else {
                ChatAdapter aa = new ChatAdapter(getApplicationContext(), R.layout.post_list, lst);
                lv.setAdapter(aa);
                lv.smoothScrollToPosition(list.size());
            }
        }catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_LONG).show();
        }
        post.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if(msg.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"You have not enter any message",Toast.LENGTH_LONG).show();
                    return;
                }
                String msgPost=msg.getText().toString();
                try {
                    Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC +5:30"));
                    Date currentLocalTime = cal.getTime();
                    DateFormat date = new SimpleDateFormat("HH:mm a");
// you can get seconds by adding  "...:ss" to it
                    date.setTimeZone(TimeZone.getTimeZone("UTC +5:30"));

                    String localTime = date.format(currentLocalTime);
                    ChatManager row = new ChatManager();
                    row.setTopicid(topicid);
                    row.setMsg(msgPost);
                    row.setSubjectid(subjectid);
                    row.setUname(username);
                    row.setDate(Common.getYyyymmdd(new Date()));
                    row.setTime(localTime);
                    row.insertRecord(getApplicationContext());
                    Toast.makeText(getApplicationContext(),"Message Send",Toast.LENGTH_LONG).show();
                    msg.setText("");
                    Intent i = new Intent(getApplicationContext(),Post_ACtivity.class);
                    i.putExtra("subjectid",sid);
                    i.putExtra("topicid",tid);
                    startActivity(i);
                    finish();
                    //Intent i = new Intent()
                }
                catch (Exception ex)
                {
                    Toast.makeText(getApplicationContext(),ex.toString(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
