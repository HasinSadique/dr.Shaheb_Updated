package com.lict.android.healthiswealth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MessageBox extends AppCompatActivity {
    private String docEmail;
    private String pEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_box);
        docEmail=getIntent().getExtras().getString("docEmail");
        pEmail=getIntent().getExtras().getString("pEmail");




    }
}
