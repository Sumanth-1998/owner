package com.example.owner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.text.SimpleDateFormat;

public class service extends Fragment {

    TextView active_since,tot_bookings;
    FirebaseFirestore db;
    FirebaseUser user;
    String phone="";
    Button back;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate( R.layout.fragment_service, container, false );
        back=view.findViewById(R.id.button);
        active_since=view.findViewById(R.id.textView136);
        tot_bookings=view.findViewById(R.id.textView137);
        db=FirebaseFirestore.getInstance();
        user= FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            phone=user.getPhoneNumber();
        }
        db.collection("owners").document(phone)
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                        if(e!=null){
                            Toast.makeText(getContext(), "Error fetching document", Toast.LENGTH_SHORT).show();
                        }
                        if(documentSnapshot.exists()){
                            SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
                            active_since.setText(sdf.format(documentSnapshot.getDate("date")));
                            tot_bookings.setText(""+documentSnapshot.getLong("bookings"));
                        }
                    }
                });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        return view;
    }


}
