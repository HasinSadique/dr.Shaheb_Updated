package com.lict.android.healthiswealth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.felipecsl.gifimageview.library.GifImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.lict.android.healthiswealth.patient.MainActivity;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class splashScreen extends AppCompatActivity {


    private GifImageView gifImageView;
    private FirebaseAuth mAuth;
    public static SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mAuth = FirebaseAuth.getInstance();
        gifImageView=(GifImageView)findViewById(R.id.GIFImageView);
        sp = getSharedPreferences("login",MODE_PRIVATE);

        try{
            InputStream inputStream=getAssets().open("pulse.gif");
            byte[] bytes= IOUtils.toByteArray(inputStream);
            gifImageView.setBytes(bytes);
            gifImageView.startAnimation();
        }catch (IOException ex){

        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                splashScreen.this.startActivity(new Intent(splashScreen.this, MainActivity.class));
                splashScreen.this.finish();


            }


        },3000);

    }

}
