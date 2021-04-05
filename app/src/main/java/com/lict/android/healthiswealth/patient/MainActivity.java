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
import com.lict.android.healthiswealth.SignUp;
import com.lict.android.healthiswealth.doctor.doctor_login;

public class MainActivity extends AppCompatActivity/* implements View.OnClickListener*/{

    EditText etUsername, etPassword;
    BlurImageView blurImageView;
    DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseReference= FirebaseDatabase.getInstance().getReference("Users");

        mAuth = FirebaseAuth.getInstance();
        etUsername= (EditText) findViewById(R.id.EditTextLoginUsername);
        etPassword= (EditText) findViewById(R.id.EditTextLoginPassword);

        blurImageView = (BlurImageView)findViewById(R.id.BlurImageView);
        blurImageView.setBlur(2);
    }


    public void signUp(View view) {
        Intent intent =new Intent(MainActivity.this, SignUp.class);
        startActivity(intent);
    }


    public void Login(View view) {
//        databaseReference.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    for (DataSnapshot dataSnapshot1:  dataSnapshot.getChildren()){
//                        User user=dataSnapshot1.getValue(User.class);
//                        if(username==user.getEmail()){
//                            if(pass==user.getPass()){
//                                Toast.makeText(getApplicationContext(),"Username: "+user.getEmail()+"-->password: "+user.getPass()+"",Toast.LENGTH_LONG).show();
//                                Intent intent=new Intent(MainActivity.this,PatientHomepageUI.class);
//                                startActivity(intent);
//                                break;
//                            }else{
//                                Toast.makeText(getApplicationContext(),"Incorrect Password",Toast.LENGTH_LONG).show();


//                            }
//                        }else{
//                            Toast.makeText(getApplicationContext(),"User Not Found",Toast.LENGTH_LONG).show();


//                        }
//                    }
//                }

//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                }
//            });
//            super.onStart();

        String email=etUsername.getText().toString();
        String password=etPassword.getText().toString();
        if(email.isEmpty()){
            etUsername.setError("Username field cannot be empty");
        }
        if(password.isEmpty()){
            etPassword.setError("Password field cannot be empty");
        }
        if(email.isEmpty()!=true && password.isEmpty()!=true){
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                Intent intent =new Intent(MainActivity.this, PatientHomepageUI.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(),"User not found",Toast.LENGTH_LONG).show();
                            }

                            // ...
                        }
                    });
        }

    }

    public void loginAsDoc(View view) {
        Intent intent =new Intent(MainActivity.this, doctor_login.class);
        startActivity(intent);

    }

    public void googleSignIn(View view) {
    }
}
