package com.lict.android.healthiswealth.doctor;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jgabrielfreitas.core.BlurImageView;
import com.lict.android.healthiswealth.R;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

import java.util.ArrayList;
import java.util.List;

public class DocHomeUI extends AppCompatActivity {
    BoomMenuButton bmb;

    private ListView listView;
    ArrayList<Integer> imageIDList;
    ArrayList<String> titleList;
    private List<Appoinment> appoinmentList;

    DatabaseReference databaseReference;
    BlurImageView blurImageView;
    CustomAdapterAppointment customAdapterAppointment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_home_ui);

        blurImageView = (BlurImageView)findViewById(R.id.BlurImageView);
        blurImageView.setBlur(2);

        databaseReference= FirebaseDatabase.getInstance().getReference("Appoinments");
        appoinmentList=new ArrayList<>();
        customAdapterAppointment=new CustomAdapterAppointment(DocHomeUI.this,appoinmentList);

        listView=findViewById(R.id.ListView_DocHomeUI);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int position=i;
                Appoinment appoinment= appoinmentList.get(position);
                String pEmail=appoinment.getpEmail();
//                String pName=appoinment.getPname();
                Intent intent=new Intent(DocHomeUI.this, open_appointment.class);
//                intent.putExtra("Name",name);
                intent.putExtra("email",pEmail);
                startActivity(intent);
            }
        });


        //boom
        bmb = (BoomMenuButton) findViewById(R.id.boom);
        imageIDList = new ArrayList<>();
        titleList = new ArrayList<>();
        setInitialData();
        bmb.setButtonEnum(ButtonEnum.Ham);
        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_3_1);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.HAM_3);
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {

            HamButton.Builder builder = new HamButton.Builder()
                    .normalImageRes(imageIDList.get(i))
                    .normalText(titleList.get(i))
                    .subNormalText("Click to enter");
            bmb.addBuilder(builder);
        }

    }

//    @Override
//    protected void onStart() {
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
////                appoinmentList.clear();
////                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
////                    Appoinment appoinment=dataSnapshot1.getValue(Appoinment.class);
////                    appoinmentList.add(appoinment);
////                }
////                listView.setAdapter(customAdapterAppointment);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//        super.onStart();
//    }

    private void setInitialData() {
        imageIDList.add(R.drawable.user);
        imageIDList.add(R.drawable.settings);
        imageIDList.add(R.drawable.logout);
        titleList.add("User Account");
        titleList.add("Settings");
        titleList.add("Logout");
    }

    public void showAppointments(View view) {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                appoinmentList.clear();
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Appoinment appoinment=dataSnapshot1.getValue(Appoinment.class);
                    appoinmentList.add(appoinment);
                }
                listView.setAdapter(customAdapterAppointment);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
