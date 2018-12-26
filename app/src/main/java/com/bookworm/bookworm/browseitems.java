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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class browseitems extends AppCompatActivity {

    Gridviewadapter gridviewadapter;
    private RelativeLayout progress,norecord;
    private Bundle bundle=new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browseitems);
        GridView gridView=(GridView)findViewById(R.id.gridview);
        Intent intes=getIntent();
        final ArrayList<mainlist> browselist=new ArrayList<>();
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("Books");
        String course=intes.getStringExtra("course");
        String semester=intes.getStringExtra("semester");
        String subject=intes.getStringExtra("subject");
        bundle.putString("course",course);
        bundle.putString("semester",semester);
        bundle.putString("subject",subject);
        Log.i("demo",course + semester  + subject);
        gridviewadapter=new Gridviewadapter(browselist);
        gridView.setAdapter(gridviewadapter);
        progress=(RelativeLayout)findViewById(R.id.progress);
        norecord=(RelativeLayout)findViewById(R.id.norecord);
        progress.setVisibility(View.VISIBLE);
        reference.child(course).child(semester).child(subject).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    for(DataSnapshot s:dataSnapshot.getChildren())
                    {
                        HashMap<String,String> nmap=(HashMap<String, String>) s.getValue();
                        Log.i("demo",""+nmap.get("name") + "key "+s.getKey());
                        browselist.add(new mainlist(s.getKey().toString(),nmap.get("image"),nmap.get("name"),nmap.get("price")));
                    }
                    setadapter();
                }
                else
                {
                    progress.setVisibility(View.INVISIBLE);
                    norecord.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public class Gridviewadapter extends BaseAdapter {

        public ArrayList<mainlist> mainlists;

        public Gridviewadapter(ArrayList<mainlist> mainlists)
        {
            this.mainlists=mainlists;
        }

        @Override
        public int getCount() {
            return mainlists.size();
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
            final mainlist lists=mainlists.get(position);
            final LinearLayout mainlist=(LinearLayout)view.findViewById(R.id.mainlayout);
            final ImageView image=(ImageView)view.findViewById(R.id.mainimage);
            final TextView texttitle=(TextView)view.findViewById(R.id.texttitle);
            final TextView price=(TextView)view.findViewById(R.id.price);
            Picasso.get().load(lists.imagename).resize(170,145).centerCrop().into(image);
            texttitle.setText(lists.names);
            price.setText("Price: "+lists.price);
            mainlist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bundle.putString("id",lists.itemid);
                    startActivity(new Intent(getApplicationContext(),Viewbook.class).putExtras(bundle).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                }
            });
            return view;
        }
    }

    public void setadapter()
    {
        gridviewadapter.notifyDataSetChanged();
        progress.setVisibility(View.INVISIBLE);
    }
}
