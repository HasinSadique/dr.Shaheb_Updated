package com.lict.android.healthiswealth.doctor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jgabrielfreitas.core.BlurImageView;
import com.lict.android.healthiswealth.patient.MainActivity;
import com.lict.android.healthiswealth.R;
import com.lict.android.healthiswealth.SignUp;

public class doctor_login extends Activity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    EditText etUsername, etPassword;
    BlurImageView blurImageView;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_login);
        databaseReference= FirebaseDatabase.getInstance().getReference("Users");
        etUsername= (EditText) findViewById(R.id.EditTextLoginUsername);
        etPassword= (EditText) findViewById(R.id.EditTextLoginPassword);

        blurImageView = (BlurImageView)findViewById(R.id.BlurImageView);
        blurImageView.setBlur(2);

        mAuth = FirebaseAuth.getInstance();

    }

    public void Login(View view) {
        String email=etUsername.getText().toString();
        String password=etPassword.getText().toString();
        if(email.isEmpty()){
            etUsername.setError("Username field cannot be empty");
        }
        if(password.isEmpty()){
            etPassword.setError("Password field cannot be empty");
        }
        if(email.isEmpty()!=true && password.isEmpty()!=true) {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                Toast.makeText(getApplicationContext(), "User found", Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(doctor_login.this, DocHomeUI.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), "User not found" + task.getException().toString(), Toast.LENGTH_LONG).show();
                            }

                            // ...
                        }

                    });
        }
    }

    public void loginAsPatient(View view) {
        Intent intent =new Intent(doctor_login.this, MainActivity.class);
        startActivity(intent);

    }

    public void signUp(View view) {
        Intent intent =new Intent(doctor_login.this, SignUp.class);
        startActivity(intent);

    }


    public void googleSignIn(View view) {
    }
}
