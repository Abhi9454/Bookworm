package com.bookworm.bookworm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Myprofile extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private ArrayList<profile> profilelist;
    private EditText emails,fullnames,phones,addresss,pincodes;
    private RelativeLayout progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);
        sharedPreferences = getSharedPreferences("bookworm.com", MODE_PRIVATE);
        profilelist=new ArrayList<>();
        emails=(EditText)findViewById(R.id.emails);
        fullnames=(EditText)findViewById(R.id.fullnames);
        phones=(EditText)findViewById(R.id.phonenumbers);
        addresss=(EditText)findViewById(R.id.addresss);
        pincodes=(EditText)findViewById(R.id.pincodes);
        progress=(RelativeLayout)findViewById(R.id.progressed);
        Button emailsign=(Button)findViewById(R.id.email_update);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("users");
        progress.setVisibility(View.VISIBLE);
        FirebaseDatabase.getInstance().getReference().child("users").child(sharedPreferences.getString("handle","")).child("details").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                  profilelist.add(new profile(dataSnapshot.child("email").getValue().toString(),dataSnapshot.child("fullname").getValue().toString(),
                          dataSnapshot.child("phone").getValue().toString(),dataSnapshot.child("address").getValue().toString(),
                          dataSnapshot.child("pincode").getValue().toString()));
                }
                updateui();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        emailsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setVisibility(View.VISIBLE);
                myRef.child(emails.getText().toString().replaceAll("[.]","_")).child("details").child("email").setValue(emails.getText().toString());
                myRef.child(emails.getText().toString().replaceAll("[.]","_")).child("details").child("fullname").setValue(fullnames.getText().toString());
                myRef.child(emails.getText().toString().replaceAll("[.]","_")).child("details").child("address").setValue(addresss.getText().toString());
                myRef.child(emails.getText().toString().replaceAll("[.]","_")).child("details").child("pincode").setValue(pincodes.getText().toString());
                myRef.child(emails.getText().toString().replaceAll("[.]","_")).child("details").child("phone").setValue(phones.getText().toString());
                sharedPreferences.edit().putString("handle",emails.getText().toString().replaceAll("[.]","_")).apply();
                sharedPreferences.edit().putString("email", emails.getText().toString()).apply();
                sharedPreferences.edit().putString("login", "1").apply();
                sharedPreferences.edit().putString("fullname",fullnames.getText().toString()).apply();
                sharedPreferences.edit().putString("address",addresss.getText().toString()).apply();
                sharedPreferences.edit().putString("pincode",pincodes.getText().toString()).apply();
                sharedPreferences.edit().putString("phone",phones.getText().toString()).apply();
                progress.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(),"Profile Updated",Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), landingpage.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                progress.setVisibility(View.INVISIBLE);

            }
        });

    }

    private void updateui()
    {
        emails.setText(profilelist.get(0).profemail);
        fullnames.setText(profilelist.get(0).proffullname);
        phones.setText(profilelist.get(0).profphone);
        addresss.setText(profilelist.get(0).profaddress);
        pincodes.setText(profilelist.get(0).profpincode);
        progress.setVisibility(View.INVISIBLE);
    }
}
