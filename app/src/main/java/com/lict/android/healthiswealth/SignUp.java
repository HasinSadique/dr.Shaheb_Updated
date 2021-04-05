package com.lict.android.healthiswealth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jgabrielfreitas.core.BlurImageView;
import com.lict.android.healthiswealth.doctor.Doctor_SignUp;
import com.lict.android.healthiswealth.patient.Patient_SignUp;

public class SignUp extends AppCompatActivity {

    BlurImageView blurImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        blurImageView = (BlurImageView)findViewById(R.id.BlurImageView);
        blurImageView.setBlur(2);

        /*Thread myThread = new Thread(){
            @Override
            public void run(){
                    try {
                        sleep(3000);
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                        finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();*/

    }

    public void Doctor(View view) {
        Intent intent=new Intent(SignUp.this, Doctor_SignUp.class);
        startActivity(intent);
    }

    public void Patient(View view) {
        Intent intent=new Intent(SignUp.this, Patient_SignUp.class);
        startActivity(intent);
    }
}
