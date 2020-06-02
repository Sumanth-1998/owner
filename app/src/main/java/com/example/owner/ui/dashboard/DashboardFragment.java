package com.example.owner.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.owner.R;
import com.example.owner.about;
import com.example.owner.qr;
import com.example.owner.service;
import com.example.owner.status;
import com.example.owner.support;
import com.example.owner.ui.payment;

public class DashboardFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate( R.layout.fragment_dashboard, container, false );

        CardView cardView1 = (CardView) view.findViewById(R.id.card_service);
        cardView1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment,new service());
                fragmentTransaction.addToBackStack(null );
                fragmentTransaction.commit();
            }
        } );
        CardView cardView2 = (CardView) view.findViewById(R.id.card_payment);


        cardView2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment,new payment());
                fragmentTransaction.addToBackStack(null );
                fragmentTransaction.commit();
            }
        } );
        CardView cardView3 = (CardView) view.findViewById(R.id.card_qr);
        cardView3.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment,new qr());
                fragmentTransaction.addToBackStack(null );
                fragmentTransaction.commit();
            }
        } );
        CardView cardView4 = (CardView) view.findViewById(R.id.card_status);
        cardView4.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment,new status());
                fragmentTransaction.addToBackStack(null );
                fragmentTransaction.commit();
            }
        } );
        CardView cardView5 = (CardView) view.findViewById(R.id.card_support);
        cardView5.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment,new support());
            //    fragmentTransaction.addToBackStack(null );
                fragmentTransaction.commit();
            }
        } );
        CardView cardView6 = (CardView) view.findViewById(R.id.card_about);
        cardView6.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment,new about());
                fragmentTransaction.addToBackStack(null );
                fragmentTransaction.commit();
            }
        } );
        return view;
    }

}