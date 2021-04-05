package com.lict.android.healthiswealth.doctor;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lict.android.healthiswealth.patient.Patient;

import java.util.ArrayList;
import java.util.List;

public class Appoinment {
    private String pEmail;
    private String docEmail;
    private String pName,dName;
    private List<Patient> patientList;

    public Appoinment(){

    }

    public Appoinment(String pEmail, String docEmail) {
        this.pEmail = pEmail;
        this.docEmail = docEmail;
    }

    public String getpEmail() {
        return pEmail;
    }

    public void setpEmail(String pEmail) {
        this.pEmail = pEmail;
    }

    public String getDocEmail() {
        return docEmail;
    }

    public void setDocEmail(String docEmail) {
        this.docEmail = docEmail;
    }

    public String getPname() {
        patientList=new ArrayList<>();
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Patient");

//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                for (DataSnapshot dataSnapshot1:  dataSnapshot.getChildren()){
//                    Patient patient=dataSnapshot1.getValue(Patient.class);
//                    if(patient.getName())
//                }
//
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        return pName;
    }

    public String getDname(String docEmail) {
        return dName;
    }
}
