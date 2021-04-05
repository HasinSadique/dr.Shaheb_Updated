package com.lict.android.healthiswealth.doctor;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class Doctor_SignUp extends AppCompatActivity {
    EditText eTName, eTPassword, eTEmail, eTRegistrationNumber, eTMobile, eTRetypePassword;
    BlurImageView blurImageView;
    private FirebaseAuth mAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor__sign_up);
        this.setTitle("Doctor registration");
        databaseReference = FirebaseDatabase.getInstance().getReference("Docotrs");
        mAuth = FirebaseAuth.getInstance();

        blurImageView = (BlurImageView) findViewById(R.id.BlurImageView);
        blurImageView.setBlur(2);

        eTName = findViewById(R.id.EditTextFullName);
        eTEmail = findViewById(R.id.EditTextEmail);
        eTMobile = findViewById(R.id.EditTextMobile);
        eTRegistrationNumber = findViewById(R.id.EditTextBMDCRegistrationNumber);
        eTPassword = findViewById(R.id.EditTextPassword);
        eTRetypePassword = findViewById(R.id.EditTextRetypePassword);

    }

    public void SignUp(View view) {
        Boolean all_OK=false;
        final String fullName = eTName.getText().toString();
        final String Mobile = eTMobile.getText().toString();
        final String BMDC = eTRegistrationNumber.getText().toString();
        final String email = eTEmail.getText().toString().trim();
        final String password = eTPassword.getText().toString().trim();
        String rePass = eTRetypePassword.getText().toString().trim();
        if(fullName.isEmpty()){
            eTName.setError("This field cannot be empty");
            all_OK=false;
        }else{
            all_OK=true;
        }
        if (Mobile.isEmpty()){
            eTMobile.setError("This field cannot be empty");
            all_OK=false;
        }else{
            all_OK=true;
        }
        if(BMDC.isEmpty()){
            eTRegistrationNumber.setError("This field cannot be empty");
            all_OK=false;
        }else{
            all_OK=true;
        }
        if(email.isEmpty()){
            eTEmail.setError("This field cannot be empty");
            all_OK=false;
        }else{
            all_OK=true;
        }
        if (password.equals(rePass) && all_OK==true) {
            //Start registration
            registerDoc(fullName, Mobile, BMDC, email, password);
        } else {
            eTPassword.setError("Password Mismatch");

            Toast.makeText(this, "Passswords Mismatch", Toast.LENGTH_SHORT).show();
        }
//
//        if(email.isEmpty() || password.isEmpty() || rePass.isEmpty()){
//            Toast.makeText(this, "One of the field is empty", Toast.LENGTH_LONG).show();
//
//        }else{
//
//        }
    }

    private void registerDoc(final String fullName, final String Mobile, final String BMDC, final String email, final String password) {
        Toast.makeText(getApplicationContext(), "register clicked--->", Toast.LENGTH_LONG).show();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Log.d("docregis", "in success   ");
                            //Add doctor to Doctor table
                            String key = databaseReference.push().getKey();
                            Doctor doctor = new Doctor(fullName, email, BMDC, Mobile, password);
                            databaseReference.child(key).setValue(doctor);
                            Toast.makeText(getApplicationContext(), "Doc Registered: ", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(getApplicationContext(), DocHomeUI.class);
                            startActivity(intent);


                            Toast.makeText(getApplicationContext(), "Registered" + task.getException().getMessage(), Toast.LENGTH_LONG).show();


                        } else {
                            Toast.makeText(getApplicationContext(), "Not Registered" + task.getException().getMessage(), Toast.LENGTH_LONG).show();

                        }

                        // ...
                    }
                });

        // Toast.makeText(getApplicationContext(), "exception s: "+s, Toast.LENGTH_SHORT).show();


    }


}


