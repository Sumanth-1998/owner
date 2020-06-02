package com.example.owner;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;



public class fragment_call extends DialogFragment {
    private static final String TAG = "MyCustomDialog";
    private Button btn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate( R.layout.fragment_call, container, false );


        btn = view.findViewById( R.id.button13 );
        btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        } );
        btn = view.findViewById( R.id.button17 );
        btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData( Uri.parse("tel:" + "7349064541") );
                callIntent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
                startActivity( callIntent );
            }
        } );
        btn = view.findViewById( R.id.button16 );
        btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData( Uri.parse("tel:" + "7587166577") );
                callIntent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
                startActivity( callIntent );

            }
        } );
        btn = view.findViewById( R.id.button21 );
        btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData( Uri.parse("tel:" + "9407984499") );
                callIntent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
                startActivity( callIntent );
            }
        } );
        return view;

    }
}

