package com.example.owner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


public class loginfragment extends Fragment {
Button btn;
EditText edt;

        public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {


            View view = inflater.inflate( R.layout.fragment_login, container, false );

            edt =view.findViewById( R.id.editText3 );

            btn = view.findViewById( R.id.button8 );
            btn.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String number = edt.getText().toString().trim();
                    /*if(number.isEmpty()||number.length()<10||number.length()>10)
                    {
                        edt.setError( "Valid number is required" );
                        edt.requestFocus();
                        return;
                    }*/

                  String phoneNumber = "+91" + number;
                    Bundle bundle = new Bundle(  );
                    bundle.putString( "number",phoneNumber );
                    otp myFrag = new otp();
                    myFrag.setArguments(bundle);

                   // FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    getFragmentManager().beginTransaction().replace(R.id.log,myFrag).commit();
                }
            } );
            return view;
        }

}
