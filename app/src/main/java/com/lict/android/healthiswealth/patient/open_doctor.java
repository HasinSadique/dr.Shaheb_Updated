package com.lict.android.healthiswealth.patient;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jgabrielfreitas.core.BlurImageView;
import com.lict.android.healthiswealth.doctor.Appoinment;
import com.lict.android.healthiswealth.R;

public class open_doctor extends AppCompatActivity {
    TextView Name,BMDC,Mobile;
    private FirebaseAuth mAuth;
    BlurImageView blurImageView;
    DatabaseReference databaseReference;

    private String loggedInUserEmail;

    String docName,docMobile,docEmail,bmdc,docPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_doctor);
        mAuth = FirebaseAuth.getInstance();
        blurImageView = (BlurImageView)findViewById(R.id.BlurImageView);
        blurImageView.setBlur(2);
        databaseReference= FirebaseDatabase.getInstance().getReference("Appoinments");

        Name=findViewById(R.id.TextView_openDoc_NameView);
        BMDC=findViewById(R.id.TextView_openDoc_BMDCView);
        Mobile=findViewById(R.id.TextView_openDoc_MobileView);

        docName=getIntent().getExtras().getString("Name");
        docEmail=getIntent().getExtras().getString("email");
        docMobile=getIntent().getExtras().getString("Mobile");
        bmdc=getIntent().getExtras().getString("bmdc");
        docPass=getIntent().getExtras().getString("pass");

        Name.setText("Name: "+docName);
        BMDC.setText("BMDC Number: "+bmdc);
        Mobile.setText("Mobile Number: "+docMobile);




    }

    @Override
    protected void onStart() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            loggedInUserEmail = user.getEmail();

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();

            Toast.makeText(getApplicationContext(), "Email: --->"+loggedInUserEmail, Toast.LENGTH_SHORT).show();
        }
        super.onStart();

    }

    public void makeCall(View view) {

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+docMobile));
        startActivity(intent);
    }

    public void SetAppoinment(View view) {
//        String key1=databaseReference.push().getKey();
//        Appoinment appoinment=new Appoinment(loggedInUserEmail,docEmail);
//        databaseReference.child(key1).setValue(appoinment);

        String s=databaseReference.getKey();
        Appoinment appoinment=new Appoinment(loggedInUserEmail,docEmail);
        databaseReference.setValue(appoinment);
        Toast.makeText(getApplicationContext(), "Appointment Fixed For Tomorrow. Be there on time", Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "S: "+s, Toast.LENGTH_SHORT).show();
    }

    public void Message(View view) {

    }

//    public void OpenMessageBox(View view) {
//        Intent intent=new Intent(open_doctor.this,MessageBox.class);
//        intent.putExtra("docEmail",docEmail);
//        intent.putExtra("pEmail",loggedInUser);
//        startActivity(intent);
//    }
}


