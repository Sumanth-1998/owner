package com.example.owner.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.owner.MainActivity;
import com.example.owner.R;


public class profile extends Fragment {


Button button;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate( R.layout.fragment_profile, container, false );
        button = (Button) view.findViewById( R.id.button18);
        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent( getActivity(), MainActivity.class );

                startActivity( intent );
            }
        });

            return view;
    }

        }




