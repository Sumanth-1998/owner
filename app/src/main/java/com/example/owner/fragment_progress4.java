package com.example.owner;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.owner.introduction.IntroActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kofigyan.stateprogressbar.StateProgressBar;

import java.util.HashMap;
import java.util.Map;


public class fragment_progress4 extends Fragment {
    String[] descriptionData = {"Photo", "My details", "Submit"};
    Button btn,btn1;
    CheckBox cb,cb2;
    FirebaseFirestore db;
    String phone;
    Map<String, Object> ownerDetail;
    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate( R.layout.fragment_progress4, container, false );

        db=FirebaseFirestore.getInstance();
        /*Get arguments from previous fragment*/
        final Bundle ownerDetails=getArguments();
        String name=ownerDetails.getString("name");
        String emailId=ownerDetails.getString("emailId");
        phone=ownerDetails.getString("phone");
        String address=ownerDetails.getString("address");
        String city=ownerDetails.getString("city");
        //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        //LocalDateTime now = LocalDateTime.now();
        //String reg_date = dtf.format(now);
        String register_date=ownerDetails.getString("register_date");
        int earnings=ownerDetails.getInt("earnings", 0);
        String last_active_time=ownerDetails.getString("last_active_time");
        ownerDetail=new HashMap<>();
        ownerDetail.put("name", name);
        ownerDetail.put("emailId", emailId);
        ownerDetail.put("phone", phone);
        ownerDetail.put("address", address);
        ownerDetail.put("city",city);
        ownerDetail.put("register_date", register_date);
        ownerDetail.put("earnings", earnings);
        ownerDetail.put("last_active_time", last_active_time);
        
        StateProgressBar stateProgressBar = (StateProgressBar) view.findViewById(R.id.progress4);
        stateProgressBar.setStateDescriptionData(descriptionData);
        stateProgressBar.setStateSize(30f);
        stateProgressBar.setStateNumberTextSize(15f);
        stateProgressBar.setStateLineThickness(5f);
        btn1 = view.findViewById( R.id.button26 );
        btn1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent( getActivity(), IntroActivity.class );
                startActivity( intent );

            }
        } );
        cb=view.findViewById(R.id.checkBox);
        cb2=view.findViewById(R.id.checkBox2);
        btn = view.findViewById( R.id.button22 );
        btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cb.isChecked() && cb2.isChecked()) {
                    //db.collection("owners").document(phone).set(ownerDetail)
                            //.addOnSuccessListener(new OnSuccessListener<Void>() {
                               // @Override
                               // public void onSuccess(Void aVoid) {
                                    Intent intent = new Intent(getActivity(), MainActivity.class);
                                    startActivity(intent);
                               // }
                           // })
                           /* .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getActivity(), "Failed to add  data to DB", Toast.LENGTH_SHORT).show();
                                }
                            });*/

                }else{
                    Toast.makeText(getActivity(), "Please agree to the terms and conditions", Toast.LENGTH_LONG).show();
                }
            }
        } );



        return view;
    }

}