package com.example.atozcom;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewFlipper image_slide_vf;
    private DrawerLayout home_drawerlayout;
    private ActionBarDrawerToggle home_actionBarDrawerToggle;
    private Button home_buy_bt, home_sell_bt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        home_buy_bt = findViewById(R.id.home_buy_bt_id);
        home_sell_bt = findViewById(R.id.home_sell_bt_id);

        home_sell_bt.setOnClickListener(this);
        home_buy_bt.setOnClickListener(this);

        // imageFlipper
        int images[] = {R.drawable.home_slide_image_b, R.drawable.home_slide_image_c};

        image_slide_vf = findViewById(R.id.home_imageslide_vf_id);

        for (int image : images) {
            flipperimage(image);

        }
        //end imageFlipper
        //NavigationBar

        home_drawerlayout = findViewById(R.id.home_drawer_id);
        home_actionBarDrawerToggle = new ActionBarDrawerToggle(this, home_drawerlayout, R.string.Open, R.string.Close);
        home_actionBarDrawerToggle.setDrawerIndicatorEnabled(true);

        home_drawerlayout.addDrawerListener(home_actionBarDrawerToggle);
        home_actionBarDrawerToggle.syncState();
        getActionBar();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.home_nav_id);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id = menuItem.getItemId();
                if (id == R.id.home_nav_profile_id) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_id, new profile_fragment()).commit();
                    Toast.makeText(MainActivity.this, "profile is selected", Toast.LENGTH_SHORT).show();
                } else if (id == R.id.home_nav_Favorite_id) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_id, new FavoriteFragment()).commit();
                    Toast.makeText(MainActivity.this, "favorite is selected", Toast.LENGTH_SHORT).show();
                } else if (id == R.id.home_nav_settings_id) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_id, new SettingsFragment()).commit();
                    Toast.makeText(MainActivity.this, "settings is selected", Toast.LENGTH_SHORT).show();
                } else if (id == R.id.home_nav_logout_id) {
                    Toast.makeText(MainActivity.this, "logout is selected", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
        //end nav


    }

    // imageFlipper method
    public void flipperimage(int image) {

        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        image_slide_vf.addView(imageView);
        image_slide_vf.setFlipInterval(3000);
        image_slide_vf.setAutoStart(true);

        image_slide_vf.setInAnimation(this, android.R.anim.slide_in_left);
        image_slide_vf.setOutAnimation(this, android.R.anim.slide_out_right);

    }
    //end imageFlipper method

    // navigation Bar method
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return home_actionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (home_drawerlayout.isDrawerOpen(Gravity.START)) {
            home_drawerlayout.closeDrawer(Gravity.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.home_sell_bt_id) {
            Intent intent = new Intent(getApplicationContext(), SellerOptionActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.home_buy_bt_id) {

        }
    }
}
