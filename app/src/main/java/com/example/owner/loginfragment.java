package com.example.owner;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class loginfragment extends Fragment {
Button btn;
EditText edt;
FirebaseFirestore admindb;

        public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {


            View view = inflater.inflate( R.layout.fragment_login, container, false );

            edt =view.findViewById( R.id.editText3 );
            FirebaseApp aop=FirebaseApp.getInstance("adminapp");
            admindb=FirebaseFirestore.getInstance(aop);
            btn = view.findViewById( R.id.button8 );
            btn.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String number = edt.getText().toString().trim();
                    String phoneNumber = "+91" + number;
                    admindb.collection("approved").document(phoneNumber).get()
                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    DocumentSnapshot doc=task.getResult();
                                    if(doc.exists()){
                                        Intent intent = new Intent(getActivity(), MainActivity.class);
                                        startActivity(intent);
                                    }else{
                                        Toast.makeText(getActivity(), "Your account is not verified by the admin! Please wait for admin approval.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getActivity(), "Failed to get registration details!", Toast.LENGTH_SHORT).show();
                                }
                            });


                    /*if(number.isEmpty()||number.length()<10||number.length()>10)
                    {
                        edt.setError( "Valid number is required" );
                        edt.requestFocus();
                        return;
                    }*/



                }
            } );
            return view;
        }

}
