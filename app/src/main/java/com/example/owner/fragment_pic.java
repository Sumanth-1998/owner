package com.example.owner;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;



public class fragment_pic extends Fragment {
    Button btn;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate( R.layout.fragment_pic, container, false );


        Button button = (Button) view.findViewById( R.id.button14 );
        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent( getActivity(), pulse.class );

                startActivity( intent );
            }
        });
        return view;
    }

}