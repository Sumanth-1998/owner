package com.example.owner;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import com.example.owner.ui.dashboard.DashboardFragment;
import com.example.owner.ui.payment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import static android.content.Context.MODE_PRIVATE;


public class fragment_upidetails extends Fragment {
    TextView upi;
    FirebaseFirestore db;
    FirebaseUser user;
    String phone="";
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate( R.layout.fragment_upidetails, container, false );
        upi=view.findViewById(R.id.textView144);
        db=FirebaseFirestore.getInstance();
        user= FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            phone=user.getPhoneNumber();
        }
        db.collection("owners").document(phone).collection("payment details").document("UPI ID")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        upi.setText(documentSnapshot.getString("upi_id"));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "Failed to get upi id!", Toast.LENGTH_SHORT).show();
                    }
                });
        Button btn1 = view.findViewById( R.id.button35 );
        btn1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Navigation.findNavController(getActivity(),R.id.nav_host_fragment).navigate(R.id.navigation_dashboard);


            }

        } );
        Button btn = view.findViewById( R.id.button11 );
        btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            db.collection("owners").document(phone).collection("payment details").document("UPI ID")
                    .delete()
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Navigation.findNavController(getActivity(),R.id.nav_host_fragment).popBackStack();
                            Navigation.findNavController(getActivity(),R.id.nav_host_fragment).navigate(R.id.payment);
                            Toast.makeText(getActivity(), "UPI ID deleted successfully!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(), "Failed to delete UPI ID!", Toast.LENGTH_SHORT).show();
                        }
                    });



            }

        } );

        return view;
    }}