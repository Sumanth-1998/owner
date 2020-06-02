package com.example.owner;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.owner.ui.dashboard.DashboardFragment;

public class support extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
         final Dialog myDialog;

        View view = inflater.inflate( R.layout.fragment_support, container, false );
        myDialog = new Dialog( getContext());
        Button btn2 = view.findViewById( R.id.button23 );
        btn2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment,new DashboardFragment());

                fragmentTransaction.commit();

            }
        } );
        CardView cardView1 = (CardView) view.findViewById(R.id.faq1);
        cardView1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.setContentView( R.layout.fragment_faq1 );
                Button btn = myDialog.findViewById( R.id.button28 );
                btn.setOnClickListener( new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }


                });
                myDialog.show();}
        } );
        CardView cardView2 = (CardView) view.findViewById(R.id.faq2);
        cardView2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.setContentView( R.layout.fragment_faq2 );
                Button btn = myDialog.findViewById( R.id.button29 );
                btn.setOnClickListener( new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }


            });
                myDialog.show();}
        } );
        CardView cardView3 = (CardView) view.findViewById(R.id.faq3);
        cardView3.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.setContentView( R.layout.fragment_faq3 );
                Button btn = myDialog.findViewById( R.id.button27 );
                btn.setOnClickListener( new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }


                });
                myDialog.show();}
        } );

        Button btn1 = view.findViewById( R.id.button11 );
        btn1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment_call dialog = new fragment_call();
                dialog.show( getFragmentManager(),"call" );

            }
        } );
        Button btn = view.findViewById( R.id.button12 );
        btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                alertDialog.setTitle( "Do you want to send message on whatsapp?" );

                alertDialog.setNegativeButton( "NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                } );
                alertDialog.setPositiveButton( "Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Uri uri = Uri.parse("smsto:" + "7587166577");
                        Intent sendIntent = new Intent(Intent.ACTION_SENDTO, uri);
                        sendIntent.putExtra(Intent.EXTRA_TEXT, "Hi Good Morning");
                        sendIntent.setPackage("com.whatsapp");
                        startActivity(sendIntent);
                    }
                } );
                alertDialog.show();

            }
        } );
        return view;
    }}