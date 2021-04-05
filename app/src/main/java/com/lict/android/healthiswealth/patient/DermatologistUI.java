package com.lict.android.healthiswealth.patient;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jgabrielfreitas.core.BlurImageView;
import com.lict.android.healthiswealth.doctor.Doctor;
import com.lict.android.healthiswealth.R;

import java.util.ArrayList;
import java.util.List;


public class DermatologistUI extends AppCompatActivity {

    ImageView Image;
    BlurImageView blurImageView;
    private ListView listView;
    DatabaseReference databaseReference;
    private List<Doctor> doctorList;
    private CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dermatologist_ui);

        blurImageView = (BlurImageView)findViewById(R.id.BlurImageView);
        blurImageView.setBlur(2);

        databaseReference= FirebaseDatabase.getInstance().getReference("Docotrs");
        doctorList=new ArrayList<>();
        customAdapter=new CustomAdapter(DermatologistUI.this,doctorList);

        listView=findViewById(R.id.ListView_derma);
        Image = (ImageView)findViewById(R.id.imageLogo);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String tempListView=listViewItems[i].toString();
//                 View v=customAdapter.getView(i,view,listView);
//                 Intent intent=new Intent(ChildSpecialistUI.this,open_doctor.class);
////
//                 startActivity(intent);
                int itempos=i;
                //Toast.makeText(ChildSpecialistUI.this, "clicked position: "+itempos, Toast.LENGTH_LONG).show();
                Doctor o= doctorList.get(itempos);
                //Toast.makeText(ChildSpecialistUI.this, "O: "+o, Toast.LENGTH_LONG).show();
                String name=o.getName();
                String mobile=o.getMobile();
                String bmdc=o.getBMDC();
                String email=o.getEmail();
                String pass=o.getPass();
                Toast.makeText(DermatologistUI.this, "Shob kichu nisi eibar pathabo", Toast.LENGTH_LONG).show();
                Intent intent=new Intent(DermatologistUI.this, open_doctor.class);
                intent.putExtra("Name",name);
                intent.putExtra("Mobile",mobile);
                intent.putExtra("bmdc",bmdc);
                intent.putExtra("email",email);
                intent.putExtra("pass",pass);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                doctorList.clear();
                for (DataSnapshot dataSnapshot1:  dataSnapshot.getChildren()){
                    Doctor doctor=dataSnapshot1.getValue(Doctor.class);
                    doctorList.add(doctor);
                }
                listView.setAdapter(customAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        super.onStart();

    }
}
