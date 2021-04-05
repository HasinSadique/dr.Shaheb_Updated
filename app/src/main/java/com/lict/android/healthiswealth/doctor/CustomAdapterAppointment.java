package com.lict.android.healthiswealth.doctor;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lict.android.healthiswealth.R;
import com.lict.android.healthiswealth.doctor.Appoinment;

import java.util.List;

public class CustomAdapterAppointment extends ArrayAdapter<Appoinment> {

    private Activity context;
    private List<Appoinment> appointmentList;

    public CustomAdapterAppointment(Activity context, List<Appoinment> appointmentList) {
        super(context, R.layout.sample_layout_appoinments, appointmentList);
        this.context = context;
        this.appointmentList = appointmentList;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater layoutInflater=context.getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.sample_layout_appoinments,null,true);

        Appoinment appoinment=appointmentList.get(position);

        TextView t1=view.findViewById(R.id.TextView_pName);
        TextView t2=view.findViewById(R.id.TextView_pEmail);

        t1.setText("Name: "+appoinment.getPname());
        t2.setText("E-mail: "+appoinment.getpEmail());
        return view;
    }
}