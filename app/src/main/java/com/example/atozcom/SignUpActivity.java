package com.example.atozcom;


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

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    Button signUp_button;
    EditText signUp_username_et, signUp_email_et, signUp_password_et;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signUp_button = findViewById(R.id.signup_button_id);
        signUp_username_et = findViewById(R.id.signup_username_et_id);
        signUp_email_et = findViewById(R.id.signup_EmailOrPhn_et_id);
        signUp_password_et = findViewById(R.id.signup_password_et_id);

        signUp_button.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.signup_button_id) {

            String email = signUp_email_et.getText().toString().trim();
            String password = signUp_password_et.getText().toString().trim();


            if (password.length() < 6) {

                signUp_password_et.setError("minimum length of passwords should 6");

                signUp_password_et.requestFocus();
                return;
            }

            if (email.isEmpty()) {
                signUp_email_et.setError("please enter your email address");
                signUp_email_et.requestFocus();
                return;
            }

            if (password.isEmpty()) {
                signUp_password_et.setError("please enter your password address");
                signUp_password_et.requestFocus();
                return;
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                signUp_email_et.setError("please enter your email address");
                signUp_email_et.requestFocus();
                return;

            }
            mAuth.createUserWithEmailAndPassword(signUp_email_et.getText().toString(), signUp_password_et.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "signed Up", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }

                }
            });
        }

    }


}

