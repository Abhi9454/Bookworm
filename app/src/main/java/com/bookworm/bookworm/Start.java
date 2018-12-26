package com.bookworm.bookworm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Start extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private SharedPreferences sharedPreferences;
    private Integer count=0;
    private String name,address,phone;
    private RelativeLayout progress;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        sharedPreferences = getSharedPreferences("bookworm.com", MODE_PRIVATE);
        if (sharedPreferences.getString("login","").equals("1")) {
            startActivity(new Intent(getApplicationContext(), landingpage.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
        } else {
            progress=(RelativeLayout)findViewById(R.id.progress);
            final EditText email = (EditText) findViewById(R.id.email);
            mAuth = FirebaseAuth.getInstance();
            final EditText password = (EditText) findViewById(R.id.password);
            Button email_signup = (Button) findViewById(R.id.email_signup);
            final Button get_started = (Button) findViewById(R.id.get_started);
            email_signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(CheckInternetConnection.checkConnection(getApplicationContext()))
                    {
                        if (!email.getText().toString().equals("") || !password.getText().toString().equals("")) {
                            progress.setVisibility(View.VISIBLE);
                            mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseDatabase.getInstance().getReference().child("users").child(email.getText().toString().replaceAll("[.]","_")).child("details").addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                if(dataSnapshot.exists())
                                                {
                                                    name=dataSnapshot.child("fullname").getValue().toString();
                                                    address=dataSnapshot.child("address").getValue().toString()+","+dataSnapshot.child("state").getValue().toString()+","+dataSnapshot.child("pincode").getValue().toString();
                                                    phone=dataSnapshot.child("phone").getValue().toString();
                                                    updatedetails(email.getText().toString(),password.getText().toString());
                                                }
                                            }
                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });
                                    } else {
                                        progress.setVisibility(View.GONE);
                                        Toast.makeText(getApplicationContext(), "Invalid Email or Password", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        } else {
                            progress.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), "Fields cannot be blank.", Toast.LENGTH_LONG).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "No Internet.", Toast.LENGTH_LONG).show();
                    }
                }
            });
            get_started.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), registration.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                }
            });
        }
    }

    private void updatedetails(String em,String pass)
    {
        Log.i("demo name",address);
        sharedPreferences.edit().putString("email", em).apply();
        sharedPreferences.edit().putString("password", pass).apply();
        sharedPreferences.edit().putString("handle",em.replaceAll("[.]","_")).apply();
        sharedPreferences.edit().putString("login", "1").apply();
        sharedPreferences.edit().putString("name", name).apply();
        sharedPreferences.edit().putString("address",address).apply();
        sharedPreferences.edit().putString("phone", phone).apply();
        progress.setVisibility(View.GONE);
        startActivity(new Intent(getApplicationContext(), landingpage.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
    }

    @Override
    public void onBackPressed() {
        count++;
        if(count==1)
        {
            Toast.makeText(getApplicationContext(),"Press one more time to exit.",Toast.LENGTH_LONG).show();
        }
        else if(count==2)
        {
            finishAffinity();
        }
    }
}
