package com.lict.android.healthiswealth.patient;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jgabrielfreitas.core.BlurImageView;
import com.lict.android.healthiswealth.R;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

import java.util.ArrayList;

public class PatientHomepageUI extends AppCompatActivity {
    BoomMenuButton bmb;
    ArrayList<Integer> imageIDList;
    ArrayList<String> titleList;

    BlurImageView blurImageView;
    Button connect,genPhysician,childSpecialist,gynecologist,entSpecialist,dermatologist,reqDoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_patient_homepage_ui);
        bmb = (BoomMenuButton) findViewById(R.id.boom);
        imageIDList = new ArrayList<>();
        titleList = new ArrayList<>();
        setInitialData();
        bmb.setButtonEnum(ButtonEnum.Ham);
        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_4_1);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.HAM_4);
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            HamButton.Builder builder = new HamButton.Builder()
                    .normalImageRes(imageIDList.get(i))
                    .normalText(titleList.get(i))
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            if(index==0){
                                //HomePage e jabe
                                Intent intent =new Intent(getApplicationContext(), PatientHomepageUI.class);
                                startActivity(intent);
                            }else if(index==1){
                                //User Account e jabe

                            }else if(index==2){
                                //Settings e jabe

                            }else if(index==3){
                                //Logout Korbe
                                Intent intent =new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    })
                    .subNormalText("Click to enter>>");
            bmb.addBuilder(builder);
        }

        connect = (Button)findViewById(R.id.ButtonConnect);
        genPhysician = (Button)findViewById(R.id.GeneralPhysician);
        childSpecialist = (Button)findViewById(R.id.ChildSpecialist);
        gynecologist = (Button)findViewById(R.id.Gynecologist);
        entSpecialist = (Button)findViewById(R.id.ENTSpecialist);
        dermatologist = (Button)findViewById(R.id.Dermatologist);

        blurImageView = (BlurImageView)findViewById(R.id.BlurImageView);
        blurImageView.setBlur(2);

//        SimpleCircleButton.Builder builder = new SimpleCircleButton.Builder()
//                .listener(new OnBMClickListener() {
//                    @Override
//                    public void onBoomButtonClick(int index) {
//                        // When the boom-button corresponding this builder is clicked.
//                        Toast.makeText(getApplicationContext(), "Clicked " + index, Toast.LENGTH_LONG).show();
//                    }
//                });
//        bmb.addBuilder(builder);
}


    public void GeneralPhysicianUI(View view){
        Intent intent = new Intent(PatientHomepageUI.this, GeneralPhysicianUI.class);
        startActivity(intent);
    }

    public void ChildSpecialistUI(View view) {
        Intent intent=new Intent(PatientHomepageUI.this, ChildSpecialistUI.class);
        startActivity(intent);
    }

    public void ENTSpecialistUI(View view) {
        Intent intent=new Intent(PatientHomepageUI.this, ENTSpecialistUI.class);
        startActivity(intent);
    }

    public void GynecologistUI(View view) {
        Intent intent=new Intent(PatientHomepageUI.this, GynecologistUI.class);
        startActivity(intent);
    }

    public void DermatologistUI(View view) {
        Intent intent=new Intent(PatientHomepageUI.this, DermatologistUI.class);
        startActivity(intent);
    }
    private void setInitialData() {
        imageIDList.add(R.drawable.ic_baseline_home_24);
        imageIDList.add(R.drawable.ic_baseline_person_24);
        imageIDList.add(R.drawable.settings);
        imageIDList.add(R.drawable.logout);
        titleList.add("Home");
        titleList.add("User Account");
        titleList.add("Settings");
        titleList.add("Logout");
    }
}
