package com.example.owner.ui.account;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.owner.R;
import com.example.owner.accounts_pojo;
import com.example.owner.credits_holder;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import java.text.ParseException;
import java.text.SimpleDateFormat;


public class AccountFragment  extends Fragment {
    FirebaseFirestore custdb,db;
    FirebaseUser user;
    private AccountViewModel accountViewModel;
    String phone="",ownername="";
    long earnings=0;
    TextView balance;
    FirestoreRecyclerAdapter<accounts_pojo, credits_holder> adapter;
    RecyclerView recyclerView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate( R.layout.fragment_account, container, false );
        balance=root.findViewById(R.id.balanceTV);
        recyclerView=root.findViewById(R.id.creditsRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseApp uap=FirebaseApp.getInstance("usersapp");
        user= FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            phone=user.getPhoneNumber();
        }
        custdb=FirebaseFirestore.getInstance(uap);
        db=FirebaseFirestore.getInstance();
        db.collection("owners").document(phone).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        earnings=documentSnapshot.getLong("earnings");
                        balance.setText(earnings+"");
                        ownername=documentSnapshot.getString("Name");
                        Query query=custdb.collectionGroup("let out").whereEqualTo("status","Completed").whereEqualTo("marker",ownername).orderBy("endDateTime", Query.Direction.DESCENDING);
                        FirestoreRecyclerOptions options=new FirestoreRecyclerOptions.Builder<accounts_pojo>()
                                .setQuery(query,accounts_pojo.class)
                                .build();
                        adapter=new FirestoreRecyclerAdapter<accounts_pojo, credits_holder>(options) {
                            @Override
                            protected void onBindViewHolder(@NonNull credits_holder holder, int position, @NonNull accounts_pojo model) {
                                holder.bid.setText(model.getOrder_id());
                                Log.d("Hello123456",model.getAmount());
                                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
                                holder.date.setText(model.getStartDate());
                                holder.type.setText("CREDIT");
                                holder.amount.setText(model.getAmount());
                                String sd=model.getStartDate()+" "+model.getStartTime();
                                String ed=model.getEndDate()+" "+model.getEndTime();
                                DateTime startDateTime=null,enddateTime=null;
                                try {
                                    startDateTime=new DateTime(formatter.parse(sd));
                                    enddateTime=new DateTime((formatter.parse(ed)));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                    Log.d("Hello123456","No123");
                                }
                                Duration duration=new Duration(startDateTime,enddateTime);
                                long hrs=duration.getStandardHours();
                                long mins=duration.getStandardMinutes();
                                mins=mins-(hrs*60);
                                holder.duration.setText(""+hrs+" : "+mins+" hrs");
                            }

                            @NonNull
                            @Override
                            public credits_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                                View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.credit_book_card,parent,false);
                                return new credits_holder(v);
                            }
                        };
                        adapter.startListening();
                        recyclerView.setAdapter(adapter);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "Failed to get earnings!", Toast.LENGTH_SHORT).show();
                    }
                });



        return root;
    }


}
