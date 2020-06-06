package com.example.owner.ui.notifications;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.owner.R;
import com.example.owner.notifications_holder;
import com.example.owner.notifications_pojo;
import com.example.owner.support;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.text.SimpleDateFormat;

public class NotificationsFragment extends Fragment {
  private View pview;
    FirestoreRecyclerAdapter<notifications_pojo, notifications_holder> adapter;
    FirebaseFirestore db;
    FirebaseUser user;
    String phone="";
    RecyclerView noti;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        pview = inflater.inflate( R.layout.fragment_notifications, container, false );

        db=FirebaseFirestore.getInstance();
        user= FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            phone=user.getPhoneNumber();
        }
        noti=pview.findViewById(R.id.notificationsRecyclerView);
        noti.setHasFixedSize(true);
        noti.setLayoutManager(new LinearLayoutManager(getContext()));

        db.collection("owners").document(phone).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String name=documentSnapshot.getString("Name");

                        Log.d("Name",name);
                        Query query=db.collection("notification").whereEqualTo("marker",name).orderBy("startDate", Query.Direction.DESCENDING);
                        FirestoreRecyclerOptions options=new FirestoreRecyclerOptions.Builder<notifications_pojo>()
                                .setQuery(query,notifications_pojo.class)
                                .build();
                        adapter=new FirestoreRecyclerAdapter<notifications_pojo, notifications_holder>(options) {
                            @Override
                            protected void onBindViewHolder(@NonNull notifications_holder holder, int position, @NonNull notifications_pojo model) {
                                SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
                                holder.date.setText(sdf.format(model.getStartDate()));
                                holder.intime.setText(sdf.format(model.getStartDate())+" "+model.getStartTime());
                                SimpleDateFormat sdf1=new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
                                if(model.getMsg().equals("New Booking!")){
                                    holder.outtime.setVisibility(View.INVISIBLE);
                                    holder.otTv.setVisibility(View.INVISIBLE);
                                }else {
                                    holder.outtime.setVisibility(View.VISIBLE);
                                    holder.otTv.setVisibility(View.VISIBLE);
                                    holder.outtime.setText(sdf1.format(model.getEndTime()));
                                }
                                holder.msg.setText(model.getMsg());
                                holder.name.setText(model.getCust_name());
                                holder.support.setOnClickListener( new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Navigation.findNavController(getActivity(),R.id.nav_host_fragment).navigate(R.id.support);
                                    }
                                } );
                            }

                            @NonNull
                            @Override
                            public notifications_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                                View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.notifications_card,parent,false);
                                //Log.d("Hiiiiiiiiiiiiiiiiiii",v.toString());
                                return new notifications_holder(v);
                            }
                        };
                        adapter.startListening();
                        noti.setAdapter(adapter);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "Failed to get data!", Toast.LENGTH_SHORT).show();
                    }
                });




        return pview;


            }



}
