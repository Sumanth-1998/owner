package com.example.owner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class status extends Fragment {
    TextView name,email,phone,latitude,longitude,statusTv;
    boolean status;
    RadioGroup gender;
    RadioButton male,female;
    String phoneNum="";
    FirebaseFirestore db;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate( R.layout.fragment_status, container, false );
        name=view.findViewById(R.id.nameEditText);
        email=view.findViewById(R.id.emailEditText);
        phone=view.findViewById(R.id.phoneEditText);

        statusTv=view.findViewById(R.id.statusEditText);
        latitude=view.findViewById(R.id.latitudeEditText);
        longitude=view.findViewById(R.id.longitudeEditText);
        phoneNum= FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber();
        db=FirebaseFirestore.getInstance();
        db.collection("owners").document(phoneNum).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                    if(e!=null){
                        Toast.makeText(getActivity(), "Error!", Toast.LENGTH_SHORT).show();
                    }


                        name.setText(documentSnapshot.getString("Name"));
                        email.setText(documentSnapshot.getString("Email"));
                        phone.setText(documentSnapshot.getString("Phone"));
                        status=documentSnapshot.getBoolean("status");
                        if(status){
                            statusTv.setText("Online");
                        }else{
                            statusTv.setText("Offline");
                        }

                        latitude.setText(documentSnapshot.getString("Latitude"));
                        longitude.setText(documentSnapshot.getString("Longitude"));

                    }
                });

        return view;
    }}