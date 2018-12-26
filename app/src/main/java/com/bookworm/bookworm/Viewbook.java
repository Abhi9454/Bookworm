package com.bookworm.bookworm;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;

import java.util.ArrayList;

public class Viewbook extends AppCompatActivity {


    private ViewPager imageviewpager;
    private ArrayList<desclist> arrayList=new ArrayList<>();
    private TextView bookname,bookdesc,bookprice,selleradress,selleremail,sellerphone;
    private RelativeLayout progress;
    private ArrayList<String> imagearray=new ArrayList<>();
    private SpringDotsIndicator dotsIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewbook);
        Intent intes=getIntent();
        String course=intes.getStringExtra("course");
        String semester=intes.getStringExtra("semester");
        String subject=intes.getStringExtra("subject");
        String id=intes.getStringExtra("id");
        imageviewpager=(ViewPager)findViewById(R.id.imageview);
        bookname=(TextView)findViewById(R.id.bookname);
        bookdesc=(TextView)findViewById(R.id.bookdescribe);
        bookprice=(TextView)findViewById(R.id.bookprice);
        selleradress=(TextView)findViewById(R.id.selleraddress);
        dotsIndicator = (SpringDotsIndicator) findViewById(R.id.dot_indicator);
        selleremail=(TextView)findViewById(R.id.selleremail);
        sellerphone=(TextView)findViewById(R.id.sellerphone);
        progress=(RelativeLayout)findViewById(R.id.progress);
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference();
        progress.setVisibility(View.VISIBLE);
        reference.child("Books").child(course)
                .child(semester).child(subject).child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       if(dataSnapshot.exists())
                       {
                           Log.i("demo",""+dataSnapshot.child("Phone Number").getValue().toString());
                           arrayList.add(new desclist(dataSnapshot.child("image").getValue().toString(),dataSnapshot.child("image2").getValue().toString()
                           ,dataSnapshot.child("name").getValue().toString(),dataSnapshot.child("Description").getValue().toString()
                           ,dataSnapshot.child("price").getValue().toString(),dataSnapshot.child("Seller Address").getValue().toString()
                                   ,dataSnapshot.child("email").getValue().toString(),dataSnapshot.child("Phone Number").getValue().toString()));
                       }
                       updateui();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    private void updateui()
    {
        Log.i("demo",""+arrayList.get(0).sellerphone);
        imagearray.add(arrayList.get(0).image1);
        imagearray.add(arrayList.get(0).image2);
        imageviewpager.setAdapter(new viewpagerdetails(Viewbook.this,imagearray));
        dotsIndicator.setViewPager(imageviewpager);
        bookname.setText(arrayList.get(0).booknames);
        bookdesc.setText("Description : "+arrayList.get(0).bookdesc);
        bookprice.setText("Price: "+arrayList.get(0).bookprice);
        selleradress.setText("Address: "+arrayList.get(0).selleraddress);
        selleremail.setText("Email :"+arrayList.get(0).selleremail);
        sellerphone.setText("Phone Number : "+arrayList.get(0).sellerphone);
        progress.setVisibility(View.INVISIBLE);
    }
}
