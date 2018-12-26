package com.bookworm.bookworm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class registration extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private SharedPreferences sharedPreferences;
    private String state;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        final EditText email=(EditText)findViewById(R.id.email);
        sharedPreferences = getSharedPreferences("bookworm.com", MODE_PRIVATE);
        mAuth = FirebaseAuth.getInstance();
        final EditText password=(EditText)findViewById(R.id.password);
        final EditText fullname=(EditText)findViewById(R.id.fullname);
        final EditText phone=(EditText)findViewById(R.id.phonenumber);
        final EditText address=(EditText)findViewById(R.id.address);
        final EditText pincode=(EditText)findViewById(R.id.pincode);
        final RelativeLayout progress=(RelativeLayout)findViewById(R.id.progress);
        Button emailsign=(Button)findViewById(R.id.email_signup);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("users");
        String[] items = new String[]{ "Andhra Pradesh","Arunachal Pradesh","Assam","Bihar","Chhattisgarh"
        ,"Goa","Gujarat","Haryana","Himachal Pradesh","Jammu and Kashmir","Jharkhand","Karnataka","Maharastra","Madhya Pradesh","Mizoram","Meghalaya",
        "Manipur","Nagaland","Odisha","Punjab","Rajasthan","Sikkim","Tripura","Tamil Nadu","Telangana","Uttrakhand",
        "Uttar Pradesh","West Bengal"};
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                state=parent.getItemAtPosition(position).toString();
                Log.i("state",state);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                state="Andhra Pradesh";
                Log.i("state",state);
            }
        });
        emailsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckInternetConnection.checkConnection(getApplicationContext()))
                {
                    if(!email.getText().toString().equals("") || !password.getText().toString().equals("")
                            || !fullname.getText().toString().equals("") || !phone.getText().toString().equals("")
                            || !address.getText().toString().equals("") || !pincode.getText().toString().equals(""))
                    {
                        if(password.getText().toString().length() >= 8 )
                        {
                            progress.setVisibility(View.VISIBLE);
                            mAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful())
                                    {
                                        myRef.child(email.getText().toString().replaceAll("[.]","_")).child("details").child("email").setValue(email.getText().toString());
                                        myRef.child(email.getText().toString().replaceAll("[.]","_")).child("details").child("fullname").setValue(fullname.getText().toString());
                                        myRef.child(email.getText().toString().replaceAll("[.]","_")).child("details").child("address").setValue(address.getText().toString());
                                        myRef.child(email.getText().toString().replaceAll("[.]","_")).child("details").child("pincode").setValue(pincode.getText().toString());
                                        myRef.child(email.getText().toString().replaceAll("[.]","_")).child("details").child("phone").setValue(phone.getText().toString());
                                        myRef.child(email.getText().toString().replaceAll("[.]","_")).child("details").child("state").setValue(state);
                                        sharedPreferences.edit().putString("handle",email.getText().toString().replaceAll("[.]","_")).apply();
                                        sharedPreferences.edit().putString("email", email.getText().toString()).apply();
                                        sharedPreferences.edit().putString("password", password.getText().toString()).apply();
                                        sharedPreferences.edit().putString("login", "1").apply();
                                        sharedPreferences.edit().putString("fullname",fullname.getText().toString()).apply();
                                        sharedPreferences.edit().putString("address",address.getText().toString()).apply();
                                        sharedPreferences.edit().putString("pincode",pincode.getText().toString()).apply();
                                        sharedPreferences.edit().putString("phone",phone.getText().toString()).apply();
                                        sharedPreferences.edit().putString("state",state).apply();
                                        progress.setVisibility(View.GONE);
                                        startActivity(new Intent(getApplicationContext(), landingpage.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                                    }
                                    else
                                    {
                                        progress.setVisibility(View.GONE);
                                        Toast.makeText(getApplicationContext(), "Server Error."+task.getException(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Password should be minimum 8 character.", Toast.LENGTH_LONG).show();
                        }
                    }
                }else
                {
                    progress.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "No Internet.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
