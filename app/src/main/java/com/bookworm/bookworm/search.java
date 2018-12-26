package com.bookworm.bookworm;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class search extends AppCompatActivity {


    private RelativeLayout searchprogress,norecords;
    private Gridviewadapter grid;
    private GridView grids;
    private Bundle bundles;
    private ArrayList<searchclass> searchclasses=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchprogress=(RelativeLayout)findViewById(R.id.searchprogress);
        norecords=(RelativeLayout)findViewById(R.id.norecords);
        Button send=(Button)findViewById(R.id.send_enter);
        bundles=new Bundle();
        final EditText searchtext=(EditText)findViewById(R.id.searchtext);
        grids=(GridView)findViewById(R.id.gridviewss);
        grid=new Gridviewadapter(searchclasses);
        grids.setAdapter(grid);
        final DatabaseReference database=FirebaseDatabase.getInstance().getReference().child("Books");
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchclasses.clear();
                norecords.setVisibility(View.INVISIBLE);
                if(!searchtext.getText().toString().equals("") || !CheckInternetConnection.checkConnection(getApplicationContext()))
                {
                   searchprogress.setVisibility(View.VISIBLE);
                   database.addListenerForSingleValueEvent(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                           if(dataSnapshot.exists())
                           {
                               for(DataSnapshot s:dataSnapshot.getChildren())
                               {
                                   for(DataSnapshot s1:s.getChildren())
                                   {
                                       for(DataSnapshot s2:s1.getChildren())
                                       {
                                           for(DataSnapshot s3:s2.getChildren())
                                           {
                                               HashMap<String,String> nmap=(HashMap<String, String>) s3.getValue();
                                              // Log.i("demo",""+nmap.get("name") + "key "+s3.getKey()+" subject "+
                                               //s2.getKey()+ "semester"+s1.getKey() +"course "+s.getKey());
                                               if(nmap.get("name").toLowerCase().contains(searchtext.getText().toString().toLowerCase()))
                                               {
                                                   Log.i("Yes","match");
                                                   searchclasses.add(new searchclass(nmap.get("name"),s3.getKey(),s.getKey(),s1.getKey(),s2.getKey(),nmap.get("image"),nmap.get("price")));
                                               }
                                           }
                                       }
                                   }
                               }
                               setadapterk();
                           }
                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError databaseError) {

                       }
                   });
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Empty search string or Internet connection not available",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public class Gridviewadapter extends BaseAdapter {

        public ArrayList<searchclass> mainl;

        public Gridviewadapter(ArrayList<searchclass> mainl)
        {
            this.mainl=mainl;
        }

        @Override
        public int getCount() {
            return mainl.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final LayoutInflater layoutInflater = getLayoutInflater();
            final View view = layoutInflater.inflate(R.layout.gridlayout, null);
            final searchclass lists=mainl.get(position);
            final LinearLayout mainlist=(LinearLayout)view.findViewById(R.id.mainlayout);
            final ImageView image=(ImageView)view.findViewById(R.id.mainimage);
            final TextView texttitle=(TextView)view.findViewById(R.id.texttitle);
            final TextView price=(TextView)view.findViewById(R.id.price);
            Picasso.get().load(lists.imagesearch).resize(170,145).centerCrop().into(image);
            texttitle.setText(lists.namesearch);
            price.setText("Price: "+lists.pricesearch);
            mainlist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("name",lists.namesearch);
                    bundles.putString("id",lists.idsearch);
                    bundles.putString("course",lists.coursesearch);
                    bundles.putString("semester",lists.semestersearch);
                    bundles.putString("subject",lists.subjectsearch);
                    startActivity(new Intent(getApplicationContext(),Viewbook.class).putExtras(bundles).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                }
            });
            return view;
        }
    }

    public void setadapterk()
    {
        Log.i("demo","setadapter");
      grid.notifyDataSetChanged();
      if(searchclasses.size()==0)
      {
          norecords.setVisibility(View.VISIBLE);
      }
      searchprogress.setVisibility(View.INVISIBLE);
    }
}
