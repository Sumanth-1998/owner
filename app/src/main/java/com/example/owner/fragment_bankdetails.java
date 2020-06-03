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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import static android.content.Context.MODE_PRIVATE;

public class fragment_bankdetails extends Fragment {
    FirebaseFirestore db;
    FirebaseUser user;
    String phone="";
    TextView name,acc_no,ifsc;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate( R.layout.fragment_bankdetails, container, false );
        name=view.findViewById(R.id.textView140);
        acc_no=view.findViewById(R.id.textView141);
        ifsc=view.findViewById(R.id.textView142);
        db=FirebaseFirestore.getInstance();
        user= FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            phone=user.getPhoneNumber();
        }
        db.collection("owners").document(phone).collection("payment details").document("Bank account")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        name.setText(documentSnapshot.getString("acc_name"));
                        acc_no.setText(documentSnapshot.getString("acc_no"));
                        ifsc.setText(documentSnapshot.getString("ifsc"));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "Failed to get bank details!", Toast.LENGTH_SHORT).show();
                    }
                });
        Button btn1 = view.findViewById( R.id.button34 );
        btn1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Navigation.findNavController(getActivity(),R.id.nav_host_fragment).navigate(R.id.navigation_dashboard);


            }

        } );
        Button btn = view.findViewById( R.id.button10 );
        btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.collection("owners").document(phone).collection("payment details").document("Bank account").delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getActivity(), "Bank account deleted Successfully!", Toast.LENGTH_SHORT).show();
                                Navigation.findNavController(getActivity(),R.id.nav_host_fragment).popBackStack();

                                Navigation.findNavController(getActivity(),R.id.nav_host_fragment).navigate(R.id.payment);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getActivity(), "Failed to delete bank details", Toast.LENGTH_SHORT).show();
                            }
                        });


            }

        } );
        return view;

}


}