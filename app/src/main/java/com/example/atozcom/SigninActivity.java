package com.example.atozcom;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SigninActivity extends AppCompatActivity implements View.OnClickListener {
    Button signin_createAcc_bt;
    Button signin_login_bt;
    EditText signin_email, signin_password;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        signin_login_bt = findViewById(R.id.signin_login_bt_id);
        signin_createAcc_bt = findViewById(R.id.signin_createacc_bt_id);
        signin_email = findViewById(R.id.signin_username_et_id);
        signin_password = findViewById(R.id.signin_password_et_id);

        signin_createAcc_bt.setOnClickListener(this);
        signin_login_bt.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.signin_createacc_bt_id) {
            Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.signin_login_bt_id) {
            String email = signin_email.getText().toString().trim();
            String password = signin_password.getText().toString().trim();


            if (password.length() < 6) {

                signin_password.setError("minimum length of passwords should 6");

                signin_password.requestFocus();
                return;
            }

            if (email.isEmpty()) {
                signin_email.setError("please enter your email address");
                signin_email.requestFocus();
                return;
            }

            if (password.isEmpty()) {
                signin_password.setError("please enter your password address");
                signin_password.requestFocus();
                return;
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                signin_email.setError("please enter your email address");
                signin_email.requestFocus();
                return;
            }

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "signed In", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });

        }


    }

}

