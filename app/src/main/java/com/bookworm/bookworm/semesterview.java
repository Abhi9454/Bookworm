package com.bookworm.bookworm;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class semesterview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semesterview);
        final Intent intent=getIntent();
        final Bundle excess=new Bundle();
        final String intenttext=intent.getStringExtra("course");
        final ListView semesterlist=(ListView)findViewById(R.id.semesterlist);
        ImageView imageshow=(ImageView)findViewById(R.id.imageshow);
        String[] values = new String[] { "Semester 1", "Semester 2", "Semester 3", "Semester 4", "Semester 5", "Semester 6" , "Semester 7" , "Semester 8" };
        final String[] value1 = new String[] { "Semester 1", "Semester 2", "Semester 3", "Semester 4", "Semester 5", "Semester 6"};
        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }
        final ArrayList<String> list1 = new ArrayList<String>();
        for (int i = 0; i < value1.length; ++i) {
            list1.add(value1[i]);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.semesterlist, R.id.semstertext, list);
        final ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this, R.layout.semesterlist, R.id.semstertext, list1);
        if(intenttext.equals("B TECH CSE"))
        {
            imageshow.setImageResource(R.drawable.computer);
            semesterlist.setAdapter(arrayAdapter);
        }
        else if(intenttext.equals("B TECH Mechanical"))
        {
            imageshow.setImageResource(R.drawable.machine);
            semesterlist.setAdapter(arrayAdapter);
        }
        else if(intenttext.equals("B TECH Civil"))
        {
            imageshow.setImageResource(R.drawable.civic);
            semesterlist.setAdapter(arrayAdapter);
        }
        else if(intenttext.equals("B TECH Electronics"))
        {
            imageshow.setImageResource(R.drawable.elec);
            semesterlist.setAdapter(arrayAdapter);
        }
        else if(intenttext.equals("Bachelor of Commerce"))
        {
            imageshow.setImageResource(R.drawable.commerce);
            semesterlist.setAdapter(arrayAdapter1);
        }
        else if(intenttext.equals("Bachelor of Science"))
        {
            imageshow.setImageResource(R.drawable.science);
            semesterlist.setAdapter(arrayAdapter1);
        }

        semesterlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                excess.putString("course",intenttext);
               if(intenttext.equals("Bachelor of Commerce") || intenttext.equals("Bachelor of Science"))
               {
                   excess.putString("semester",list1.get(position));
               }
               else
               {
                   excess.putString("semester",list.get(position));
               }
               startActivity(new Intent(getApplicationContext(),subjectview.class).putExtras(excess).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        });
    }
}
