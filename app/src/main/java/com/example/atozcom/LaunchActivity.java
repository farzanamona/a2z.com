package com.example.atozcom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class LaunchActivity extends AppCompatActivity implements View.OnClickListener {
    private Button launch_signin_bt, launch_seePost_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        launch_signin_bt = findViewById(R.id.launch_signin_bt_id);
        launch_seePost_bt = findViewById(R.id.launch_seepost_bt_id);

        launch_signin_bt.setOnClickListener(this);
        launch_seePost_bt.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.launch_signin_bt_id) {
            Intent intent = new Intent(getApplicationContext(), SigninActivity.class);
            startActivity(intent);

        } else if (v.getId() == R.id.launch_seepost_bt_id) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);


        }

    }
}
