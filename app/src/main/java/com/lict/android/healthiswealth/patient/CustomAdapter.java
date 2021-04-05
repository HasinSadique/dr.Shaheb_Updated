package com.lict.android.healthiswealth.patient;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lict.android.healthiswealth.R;
import com.lict.android.healthiswealth.doctor.Doctor;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Doctor> {

    private Activity context;
    private List<Doctor> doctorList;

    public CustomAdapter(Activity context,List<Doctor> doctorList) {
        super(context, R.layout.sample_layout_doctor, doctorList);
        this.context = context;
        this.doctorList = doctorList;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater  layoutInflater=context.getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.sample_layout_doctor,null,true);

        Doctor doctor=doctorList.get(position);

        TextView t1=view.findViewById(R.id.TextView_NameShow);
        TextView t2=view.findViewById(R.id.TextView_BMDCShow);
        TextView t3=view.findViewById(R.id.TextView_MobileNo_Show);

        t1.setText("Name: "+doctor.getName());
        t2.setText("BMDC Number: "+doctor.getBMDC());
        t3.setText("Mobile Number: "+doctor.getMobile());

        return view;
    }
}