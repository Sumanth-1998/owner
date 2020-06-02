package com.example.owner;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kofigyan.stateprogressbar.StateProgressBar;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class fragment_progress2 extends Fragment {
    FirebaseUser user;
    FirebaseFirestore db;
    View view;
    String[] descriptionData = {"Photo", "My details", "Submit"};
    Button btn;
    private Button mDisplayDate;
    private RadioGroup genderRadioGroup;
    private EditText nameEditText;
    private EditText mDisplayDate1,emailEditText,phoneEditText,addressEditText,cityEditText;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    @SuppressLint("ClickableViewAccessibility")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        view = inflater.inflate( R.layout.fragment_progress2, container, false );

        db= FirebaseFirestore.getInstance();
        String phone="";
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            phone=user.getPhoneNumber();

        }



        mDisplayDate1= (EditText) view.findViewById( R.id.dobEditText );
        mDisplayDate= (Button) view.findViewById( R.id.calendarButton );
        mDisplayDate.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                cal.add(Calendar.YEAR,-12);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                 DatePickerDialog dialog= new DatePickerDialog( getActivity(),android.R.style.Theme_Holo_Light_Dialog_MinWidth,mDateSetListener,year,month,day );

                dialog.getDatePicker().setMaxDate( cal.getTimeInMillis() );
              dialog.getWindow().setBackgroundDrawable( new ColorDrawable( Color.TRANSPARENT ) ); ;
            dialog.show();
            }
        } );
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {

                Log.d( TAG, "onDateSet: dd/mm/yyy: " + day + "/" + month + "/" + year );
                String date = day + "/" + month + "/" + year;

                mDisplayDate1.setText( date );

            }
        };



        StateProgressBar stateProgressBar = (StateProgressBar) view.findViewById(R.id.progress2);
        stateProgressBar.setStateDescriptionData(descriptionData);
        stateProgressBar.setStateSize(30f);
        stateProgressBar.setStateNumberTextSize(15f);
        stateProgressBar.setStateLineThickness(5f);

        nameEditText=(EditText) view.findViewById(R.id.nameEditText);
        emailEditText=(EditText)view.findViewById(R.id.emailEditText);
        phoneEditText=(EditText)view.findViewById(R.id.phoneEditText);
        phoneEditText.setText(phone);
        addressEditText=(EditText)view.findViewById(R.id.addressEditText);
        cityEditText=(EditText)view.findViewById(R.id.cityEditText);
        genderRadioGroup=(RadioGroup)view.findViewById(R.id.genderRadioGroup);



        btn = view.findViewById( R.id.nextButton);
        btn.setOnClickListener( new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if (nameEditText.length() == 0) {
                    nameEditText.setError("Name Required");
                } else if (emailEditText.length() == 0) {
                    emailEditText.setError("Email ID Required");
                } else if (phoneEditText.length() == 0) {
                    phoneEditText.setError("Phone NUmber Required");
                } else if (phoneEditText.length() != 13) {
                    phoneEditText.setError("Phone number to be in 10 digits");
                } else if (addressEditText.length() == 0) {
                    addressEditText.setError("Address required");
                } else if (cityEditText.length() == 0) {
                    cityEditText.setError("City Required");
                } else if (mDisplayDate1.length() == 0) {
                    mDisplayDate1.setError("Date required");
                } else {
                    String name = nameEditText.getText().toString();
                    String email = emailEditText.getText().toString();
                    String phone = phoneEditText.getText().toString();
                    String address = addressEditText.getText().toString();
                    String city = cityEditText.getText().toString();
                    Bundle ownerDetails=new Bundle();
                    ownerDetails.putString("name", name);
                    ownerDetails.putString("emailId", email);
                    ownerDetails.putString("phone", phone);
                    ownerDetails.putString("address", address);
                    ownerDetails.putString("city", city);
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    String reg_date = dtf.format(now);
                    ownerDetails.putString("register_date", reg_date);
                    ownerDetails.putInt("earnings", 0);
                    ownerDetails.putString("last_active_time", reg_date);
                    //Navigation.findNavController(view).navigate(R.id., bundle);
                    fragment_progress4 frag=new fragment_progress4();
                    frag.setArguments(ownerDetails);
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.pro, frag);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();





                }
            }
        } );


        return view;
    }

}