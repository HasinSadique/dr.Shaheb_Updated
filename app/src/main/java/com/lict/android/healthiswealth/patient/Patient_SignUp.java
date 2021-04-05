package com.lict.android.healthiswealth.patient;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.lict.android.healthiswealth.R;

public class Patient_SignUp extends AppCompatActivity {

    BlurImageView blurImageView;
    EditText eTName, eTAge, eTPassword, eTEmail, eTMobile, eTRetypePassword;

    DatabaseReference databaseReference;
    DatabaseReference databaseReference2;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__sign_up);
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Patients");
//        databaseReference2= FirebaseDatabase.getInstance().getReference("Users");


        blurImageView = (BlurImageView) findViewById(R.id.BlurImageView);
        blurImageView.setBlur(2);

        eTName = findViewById(R.id.EditTextFullName);
        eTAge = findViewById(R.id.EditTextAge);
        eTEmail = findViewById(R.id.EditTextEmail);
        eTMobile = findViewById(R.id.EditTextMobile);
        eTPassword = findViewById(R.id.EditTextPassword);
        eTRetypePassword = findViewById(R.id.EditTextRetypePassword);
    }

    public void Register(View view) {
        try {
            Boolean x = false;
            String age = eTAge.getText().toString();
            final int Age;
            if (age == null) {
                Age = 0;
            } else {
                Age = Integer.parseInt(age);
            }
            final String name = eTName.getText().toString().trim();
            final String mobile = eTMobile.getText().toString().trim();
            final String password = eTPassword.getText().toString().trim();
            final String email = eTEmail.getText().toString().trim();
            final String rePass = eTRetypePassword.getText().toString().trim();
            if (!password.equals(rePass)) {
                x = true;
                Toast.makeText(getApplicationContext(), "register clicked--->", Toast.LENGTH_LONG).show();
            }
            if (age.isEmpty() || name.isEmpty() || mobile.isEmpty() || x) {
                Toast.makeText(this, "One of the fields is empty", Toast.LENGTH_SHORT).show();
            } else {

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    //Add patient to patient table
                                    String key = databaseReference.push().getKey();
                                    Patient patient = new Patient(name, email, Age, mobile, password);
                                    databaseReference.child(key).setValue(patient);
                                    Intent intent = new Intent(getApplicationContext(), PatientHomepageUI.class);
                                    startActivity(intent);
                                    finish();

                                    Toast.makeText(getApplicationContext(), "Registered", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Not Registered" + task.getException().getMessage(), Toast.LENGTH_LONG).show();

                                }

                                // ...
                            }
                        });
            }
        }catch (Exception e){
            Toast.makeText(this, "One of the fields is empty>> "+e, Toast.LENGTH_SHORT).show();
        }


//        if(password == rePass){
//            //register
//            Toast.makeText(getApplicationContext(), "INSIDE IF", Toast.LENGTH_LONG).show();
//            mAuth.createUserWithEmailAndPassword(email, password)
//                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            Toast.makeText(Patient_SignUp.this, "inside on complete", Toast.LENGTH_LONG).show();
//                            if (task.isSuccessful()) {
//                                Toast.makeText(getApplicationContext(), "New Patient Added--->" + task.getException().getMessage()
//                                        , Toast.LENGTH_LONG).show();
//
//                                //Add patient to patient table
//                                String key = databaseReference.push().getKey();
//                                Patient patient = new Patient(name, email, Age, mobile, password);
//                                databaseReference.child(key).setValue(patient);
//
//                                Intent intent = new Intent(getApplicationContext(), PatientHomepageUI.class);
//                                startActivity(intent);
//                                finish();
//                            } else {
//
//                                Toast.makeText(getApplicationContext(), "New patient addition unsuccessful--->"
//                                        + task.getException().getMessage(), Toast.LENGTH_LONG).show();
//
//                            }
//                        }
//                    });
//        }else{
//
//
//        }


    }
}


