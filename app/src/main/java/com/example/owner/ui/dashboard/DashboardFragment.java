package com.example.owner.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

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

                Navigation.findNavController(getActivity(),R.id.nav_host_fragment).navigate(R.id.service);
            }
        } );
        CardView cardView2 = (CardView) view.findViewById(R.id.card_payment);


        cardView2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getActivity(),R.id.nav_host_fragment).navigate(R.id.payment);

            }
        } );
        CardView cardView3 = (CardView) view.findViewById(R.id.card_qr);
        cardView3.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getActivity(),R.id.nav_host_fragment).navigate(R.id.qr);

            }
        } );
        CardView cardView4 = (CardView) view.findViewById(R.id.card_status);
        cardView4.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getActivity(),R.id.nav_host_fragment).navigate(R.id.status);

            }
        } );
        CardView cardView5 = (CardView) view.findViewById(R.id.card_support);
        cardView5.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getActivity(),R.id.nav_host_fragment).navigate(R.id.support);
            }
        } );
        CardView cardView6 = (CardView) view.findViewById(R.id.card_about);
        cardView6.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getActivity(),R.id.nav_host_fragment).navigate(R.id.about);
            }
        } );
        return view;
    }

}