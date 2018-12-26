package com.bookworm.bookworm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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

import java.util.ArrayList;
import java.util.HashMap;

public class adsview extends AppCompatActivity {


    private Gridviewadapters gridviewadapter;
    private RelativeLayout progress,norecords;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adsview);
        sharedPreferences = getSharedPreferences("bookworm.com", MODE_PRIVATE);
        GridView gridView=(GridView)findViewById(R.id.gridad);
        final ArrayList<removelist> browselists=new ArrayList<>();
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("users");
        progress=(RelativeLayout)findViewById(R.id.progress);
        norecords=(RelativeLayout)findViewById(R.id.norecords);
        progress.setVisibility(View.VISIBLE);
        gridviewadapter= new Gridviewadapters(browselists);
        gridView.setAdapter(gridviewadapter);
        reference.child(sharedPreferences.getString("handle","")).child("Myad").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    for(DataSnapshot s:dataSnapshot.getChildren())
                    {
                        HashMap<String,String> nmap=(HashMap<String, String>) s.getValue();
                        Log.i("demo",""+nmap.get("name") + "key "+s.getKey());
                        browselists.add(new removelist(s.getKey(),nmap.get("image"),nmap.get("name"),nmap.get("price"),nmap.get("course"),nmap.get("semester"),nmap.get("subjects")));
                    }
                    setadapter();
                }
                else
                {
                    progress.setVisibility(View.INVISIBLE);
                    norecords.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public class Gridviewadapters extends BaseAdapter {

        public ArrayList<removelist> mainlists;

        public Gridviewadapters(ArrayList<removelist> mainlists)
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
            final removelist lists=mainlists.get(position);
            final LinearLayout mainlist=(LinearLayout)view.findViewById(R.id.mainlayout);
            final ImageView image=(ImageView)view.findViewById(R.id.mainimage);
            final TextView texttitle=(TextView)view.findViewById(R.id.texttitle);
            final TextView price=(TextView)view.findViewById(R.id.price);
            Picasso.get().load(lists.imagename).resize(170,145).centerCrop().into(image);
            texttitle.setText(lists.names);
            price.setText("Price: "+lists.removeprice);
            mainlist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(adsview.this);
                    builder.setMessage("Are you sure you want to remove this ad?").setTitle("Important Notification");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            progress.setVisibility(View.VISIBLE);
                            FirebaseDatabase.getInstance().getReference().child("Books").child(lists.course).child(lists.semester).child(lists.subject).child(lists.itemid).removeValue();
                            FirebaseDatabase.getInstance().getReference().child("users").child(sharedPreferences.getString("handle","")).child("Myad").child(lists.itemid).removeValue();
                            Toast.makeText(getApplicationContext(),"Ad removed",Toast.LENGTH_LONG).show();
                            setadapter();
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
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
