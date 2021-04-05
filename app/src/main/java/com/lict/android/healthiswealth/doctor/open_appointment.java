package com.lict.android.healthiswealth.doctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.lict.android.healthiswealth.R;

public class open_appointment extends AppCompatActivity {
    String pEmail,dEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_appointment);
        pEmail=getIntent().getExtras().getString("pEmail");
        String dEmail=getIntent().getExtras().getString("dEmail");
    }
;
    public void CancelAppoinment(View view) {

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Query applesQuery = ref.child("Appoinments").equalTo(pEmail);

        applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                    appleSnapshot.getRef().removeValue();
                }
                Toast.makeText(getApplicationContext(), "Appointment cancelled", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(open_appointment.this, DocHomeUI.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void CheckedAppoinment(View view) {

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Query applesQuery = ref.child("Appoinments").equalTo(pEmail);

        applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                    appleSnapshot.getRef().removeValue();
                }
                Toast.makeText(getApplicationContext(), "Appointment completed", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(open_appointment.this,DocHomeUI.class);
                startActivity(intent);
                finish();
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
