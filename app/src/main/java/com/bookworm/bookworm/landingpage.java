package com.bookworm.bookworm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;

import java.util.ArrayList;

public class landingpage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Integer count=0;
    private ArrayList<mainlist> listshow;
    private ViewPager headviewpager;
    int images[] = {R.drawable.sider1,R.drawable.silder2};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landingpage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        sharedPreferences = getSharedPreferences("bookworm.com", MODE_PRIVATE);
        setSupportActionBar(toolbar);
        for(int i=0;i<images.length;i++)
                ImagesArray.add(images[i]);

        listshow=new ArrayList<>();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        headviewpager=(ViewPager)findViewById(R.id.headviewpager);
        headviewpager.setAdapter(new viewpageradapter(landingpage.this,ImagesArray));
        SpringDotsIndicator dotsIndicator = (SpringDotsIndicator) findViewById(R.id.dots_indicator);
        dotsIndicator.setViewPager(headviewpager);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        CardView compscience=(CardView)findViewById(R.id.compscience);
        CardView bece=(CardView)findViewById(R.id.bece);
        CardView bmech=(CardView)findViewById(R.id.bmech);
        CardView bcivil=(CardView)findViewById(R.id.bcivil);
        CardView bcommerce=(CardView)findViewById(R.id.bcommerce);
        CardView bscience=(CardView)findViewById(R.id.bscience);
        compscience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             startActivity(new Intent(getApplicationContext(),semesterview.class).putExtra("course","B TECH CSE").setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        });
        bece.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),semesterview.class).putExtra("course","B TECH Electronics").setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        });
        bmech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),semesterview.class).putExtra("course","B TECH Mechanical").setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        });
        bcivil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),semesterview.class).putExtra("course","B TECH Civil").setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        });
        bscience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),semesterview.class).putExtra("course","Bachelor of Science").setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        });
        bcommerce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),semesterview.class).putExtra("course","Bachelor of Commerce").setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        count++;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
           if(count==1)
           {
               Toast.makeText(getApplicationContext(),"Press one more time to exit.",Toast.LENGTH_SHORT).show();
           }
           else
           {
              finishAffinity();
           }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.landingpage, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            startActivity(new Intent(getApplicationContext(),sell.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
            return true;
        }else  if (id == R.id.action_search) {
            startActivity(new Intent(getApplicationContext(),search.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser()==null)
        {
            sharedPreferences.edit().putString("login","0").apply();
            startActivity(new Intent(getApplicationContext(),Start.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
            Toast.makeText(getApplicationContext(),"Session Expired. Please Login again",Toast.LENGTH_LONG).show();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

       if (id == R.id.nav_gallery) {
            startActivity(new Intent(getApplicationContext(),adsview.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
        } else if (id == R.id.nav_slideshow) {
           startActivity(new Intent(getApplicationContext(),Myprofile.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
        }
       else if (id == R.id.nav_about) {
           AlertDialog.Builder builder = new AlertDialog.Builder(landingpage.this);
           builder.setMessage(R.string.aboutus).setTitle("About Us");
           builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int which) {
                   dialog.dismiss();
               }
           });
           AlertDialog alertDialog = builder.create();
           alertDialog.show();
       }
        else if (id == R.id.nav_manage) {
            sharedPreferences.edit().putString("login","0").apply();
            startActivity(new Intent(getApplicationContext(),Start.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
