package com.example.owner.ui.home;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.owner.Logout;
import com.example.owner.Owner_pojo;
import com.example.owner.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QuerySnapshot;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import java.util.Date;


public class HomeFragment extends Fragment {
    private  Button btn;
    private  Button btn1;
    private FirebaseFirestore db,userdb;
    private FirebaseUser user;
    private final String TAG="HomeFragment";
    String name,userPhone,userName,book_id,startTime;
    TextView nameTextView,phoneTextView,bookId,inTime,days,time,bookings,earnings;
    Switch statusSwitch;
    String phone="sss";
    Owner_pojo owner_obj;
    Activity activity;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        user= FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            phone=user.getPhoneNumber();

        }
        View view = inflater.inflate( R.layout.fragment_home, container, false );
        nameTextView=view.findViewById(R.id.textView44);
        phoneTextView=view.findViewById(R.id.textView46);
        bookId=view.findViewById(R.id.textView45);
        inTime=view.findViewById(R.id.inTimeTextView);
        statusSwitch=view.findViewById(R.id.switch1);
        days=view.findViewById(R.id.textView31);
        time=view.findViewById(R.id.textView33);
        bookings=view.findViewById(R.id.textView35);
        earnings=view.findViewById(R.id.textView37);
        db=FirebaseFirestore.getInstance();
        //ListenerRegistration registration=db.collection("owners").document(phone).add
        Log.d("Phone",phone);
        db.collection("owners").document(phone)
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {


                   /*     }
                    }
                })
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {*/
                   if(e!=null){
                       Toast.makeText(getContext(), "Error while listening", Toast.LENGTH_SHORT).show();
                       return;
                   }
                        owner_obj=documentSnapshot.toObject(Owner_pojo.class);
                        statusSwitch.setChecked(owner_obj.isStatus());
                        bookings.setText(""+owner_obj.getBookings());
                        earnings.setText(""+owner_obj.getEarnings());
                        setStats();
                        statusSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {
                                Log.d("Message", "Reached");
                                if (isChecked){
                                    db.collection("owners").document(phone).update("status", isChecked,"last_online",new DateTime().toDate())
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Toast.makeText(activity, "Status changed successfully!", Toast.LENGTH_SHORT).show();

                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(activity, "Failed to update status!", Toast.LENGTH_SHORT).show();
                                                    statusSwitch.setChecked(!isChecked);
                                                }
                                            });
                                    }
                                else{
                                    db.collection("owners").document(phone).update("status",isChecked)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    db.collection("owners").document(phone).get()
                                                            .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                                @Override
                                                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                                    //Date last_active=documentSnapshot.getDate("last_active");

                                                                    DateTime last_active=new DateTime(documentSnapshot.getDate("last_online"));

                                                                    Duration duration=new Duration(last_active,new DateTime());
                                                                    long seconds=duration.getStandardSeconds();
                                                                    Log.d("seconds",""+seconds);
                                                                    //Log.d("seconds",new DateTime().toDate().toString());
                                                                    db.collection("owners").document(phone).update("sec_online", FieldValue.increment(seconds),"last_online",null)
                                                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                @Override
                                                                                public void onSuccess(Void aVoid) {
                                                                                    Toast.makeText(activity, "Offline Successfull!", Toast.LENGTH_SHORT).show();
                                                                                    setStats();
                                                                                }
                                                                            })
                                                                            .addOnFailureListener(new OnFailureListener() {
                                                                                @Override
                                                                                public void onFailure(@NonNull Exception e) {
                                                                                    Toast.makeText(activity, "Failed to go offline!", Toast.LENGTH_SHORT).show();
                                                                                }
                                                                            });
                                                                }
                                                            })
                                                            .addOnFailureListener(new OnFailureListener() {
                                                                @Override
                                                                public void onFailure(@NonNull Exception e) {
                                                                    Toast.makeText(activity, "Failed to get last active time", Toast.LENGTH_SHORT).show();
                                                                }
                                                            });
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(activity, "Failed to update status", Toast.LENGTH_SHORT).show();
                                                }
                                            });

                                }
                            }
                        });


                    }
                });

                /*.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(activity, "Failed to get owner data!", Toast.LENGTH_SHORT).show();
                    }
                });*/


        FirebaseApp uap=FirebaseApp.getInstance("usersapp");

        userdb=FirebaseFirestore.getInstance(uap);
        db.collection("owners").document(phone).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        name=documentSnapshot.getString("Name");
                        userdb.collectionGroup("let out").whereEqualTo("type","PRIVATE PARKING")
                                .whereEqualTo("status","Active")
                                .whereEqualTo("marker",name).get()
                                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                    @Override
                                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                        if (queryDocumentSnapshots.size() > 0) {
                                            DocumentSnapshot documentSnapshot = queryDocumentSnapshots.getDocuments().get(0);
                                            userPhone = documentSnapshot.getString("phone");
                                            book_id = documentSnapshot.getId();
                                            startTime = documentSnapshot.getString("startTime");
                                            userdb.collection("profile").document(userPhone).get()
                                                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                        @Override
                                                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                            userName = documentSnapshot.getString("name");
                                                            nameTextView.setText(userName);
                                                            phoneTextView.setText(userPhone);
                                                            bookId.setText(book_id);
                                                            inTime.setText(startTime);
                                                        }
                                                    })

                                                    .addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Toast.makeText(activity, "Failed to fetch profile data", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                        }
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(activity, "Failed to get user data", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(activity, "Failed to get owner data", Toast.LENGTH_SHORT).show();
            }
        });


        btn = view.findViewById( R.id.imageButton2 );
        btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logout dialog = new Logout();
                dialog.show( getFragmentManager(),"Logout" );

            }
        } );
       return view;

    }
    public void setStats(){
        if(statusSwitch.isChecked()){
            db.collection("owners").document(phone).get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            long n=documentSnapshot.getLong("sec_online");
                            DateTime last_active=new DateTime(documentSnapshot.getDate("last_online"));

                            Duration duration=new Duration(last_active,new DateTime());
                            long sec=duration.getStandardSeconds();
                            n+=sec;
                            long day = n / (24 * 3600);

                            n = n % (24 * 3600);
                            long hour = n / 3600;

                            n %= 3600;
                            long minutes = n / 60 ;

                            n %= 60;
                            long seconds = n;

                            days.setText(day+"");
                            time.setText(hour+" : "+minutes);


                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(activity, "Failed to get seconds online", Toast.LENGTH_SHORT).show();
                        }
                    });
        }else {
            db.collection("owners").document(phone).get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            long n = documentSnapshot.getLong("sec_online");
                            long day = n / (24 * 3600);

                            n = n % (24 * 3600);
                            long hour = n / 3600;

                            n %= 3600;
                            long minutes = n / 60;

                            n %= 60;
                            long seconds = n;
                            days.setText(day + "");
                            time.setText(hour + " : " + minutes + " hrs");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(activity, "Failed to get seconds online", Toast.LENGTH_SHORT).show();

                        }
                    });
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.activity=(Activity)context;
    }
}