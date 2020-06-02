package com.example.owner;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.owner.ui.dashboard.DashboardFragment;
import com.example.owner.ui.payment;

import static android.content.Context.MODE_PRIVATE;


public class fragment_upidetails extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate( R.layout.fragment_upidetails, container, false );
        Button btn1 = view.findViewById( R.id.button35 );
        btn1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace( R.id.nav_host_fragment, new DashboardFragment() );

                fragmentTransaction.commit();


            }

        } );
        Button btn = view.findViewById( R.id.button11 );
        btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences pref1= getContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
                SharedPreferences.Editor editor = pref1.edit();
                editor.putBoolean("isIntroOpnend",false);
                editor.commit();

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace( R.id.nav_host_fragment, new payment() );

                fragmentTransaction.commit();


            }

        } );

        return view;
    }}