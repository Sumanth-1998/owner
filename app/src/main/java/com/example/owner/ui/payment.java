package com.example.owner.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.owner.R;
import com.example.owner.fragment_bankdetails;
import com.example.owner.fragment_upidetails;
import com.example.owner.ui.dashboard.DashboardFragment;

import static android.content.Context.MODE_PRIVATE;

public class payment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate( R.layout.fragment_payment, container, false );

        if (restorePrefData()) {

            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.nav_host_fragment,new fragment_bankdetails());

            fragmentTransaction.commit();


        }

        if (restorePrefData1()) {

            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.nav_host_fragment,new fragment_upidetails());

            fragmentTransaction.commit();


        }

        Button btn2 = view.findViewById( R.id.button32);
        btn2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment,new DashboardFragment());

                fragmentTransaction.commit();
            }
        } );


        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radiogroup);


        final TextView txt = (TextView) view.findViewById( R.id.bankaccd );
        final TextView txt11 = (TextView) view.findViewById( R.id.bankaccd1 );
        final TextView txt1 = (TextView) view.findViewById( R.id.textView89 );
        final TextView txt12 = (TextView) view.findViewById( R.id.textView199 );
        final TextView txt2 = (TextView) view.findViewById( R.id.textView90 );
        final TextView txt3 = (TextView) view.findViewById( R.id.textView91);
        final EditText edt1 = (EditText) view.findViewById( R.id.editText2);
        final EditText edt13 = (EditText) view.findViewById( R.id.editText199);
        final EditText edt2 = (EditText) view.findViewById( R.id. editText5);
        final EditText edt3 = (EditText) view.findViewById( R.id. editText6);
        final Button btn1 = (Button) view.findViewById( R.id.button30);
        final Button btn199 = (Button) view.findViewById( R.id.button199);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {


                @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {

                switch(arg1)
                {
                   case R.id.radioButton3:

                       if (txt11.getVisibility() == View.VISIBLE)
                           txt11.setVisibility(View.GONE);
                       if (txt12.getVisibility() == View.VISIBLE)
                           txt12.setVisibility(View.GONE);
                       if (edt13.getVisibility() == View.VISIBLE)
                           edt13.setVisibility(View.GONE);
                       if (btn199.getVisibility() == View.VISIBLE)
                       btn1.setVisibility(View.GONE);

                       txt.setVisibility(View.VISIBLE  );
                       txt1.setVisibility(View.VISIBLE  );
                       txt2.setVisibility(View.VISIBLE  );
                       txt3.setVisibility(View.VISIBLE  );
                       edt1.setVisibility(View.VISIBLE  );
                       edt2.setVisibility(View.VISIBLE  );
                       edt3.setVisibility(View.VISIBLE  );
                       btn1.setVisibility( View.VISIBLE );

                       break;
                       case R.id.radioButton4:
                           txt11.setVisibility(View.VISIBLE  );
                           txt12.setVisibility(View.VISIBLE  );
                           edt13.setVisibility( View.VISIBLE );
                           btn199.setVisibility( View.VISIBLE );
                           if (txt.getVisibility() == View.VISIBLE)
                               txt.setVisibility(View.GONE);
                           if (txt1.getVisibility() == View.VISIBLE)
                               txt1.setVisibility(View.GONE);
                           if (txt2.getVisibility() == View.VISIBLE)
                               txt2.setVisibility(View.GONE);
                           if (txt3.getVisibility() == View.VISIBLE)
                               txt3.setVisibility(View.GONE);
                           if (edt1.getVisibility() == View.VISIBLE)
                               edt1.setVisibility(View.GONE);
                           if (edt2.getVisibility() == View.VISIBLE)
                               edt2.setVisibility(View.GONE);
                           if (edt3.getVisibility() == View.VISIBLE)
                               edt3.setVisibility(View.GONE);
                           if (btn1.getVisibility() == View.VISIBLE)
                               btn1.setVisibility(View.GONE);

                       break;

            }}
        });
        Button btn = view.findViewById( R.id.button30);
        btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment,new fragment_bankdetails());
                savePrefsData();
                fragmentTransaction.commit();
            }
        } );
        Button btn19 = view.findViewById( R.id.button199);
        btn19.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment,new fragment_upidetails());
                savePrefsData1();
                fragmentTransaction.commit();
            }
        } );
        return view;


    }
    private boolean restorePrefData() {


        SharedPreferences pref = this.getActivity().getSharedPreferences("myPrefs",MODE_PRIVATE);
        Boolean isIntroActivityOpnendBefore = pref.getBoolean("isIntroOpnend",false);
        return  isIntroActivityOpnendBefore;



    }

    private void savePrefsData() {

        SharedPreferences pref = this.getActivity().getSharedPreferences("myPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpnend",true);
        editor.commit();


    }
    private boolean restorePrefData1() {


        SharedPreferences pref1 = this.getActivity().getSharedPreferences("myPrefs",MODE_PRIVATE);
        Boolean isIntroActivityOpnendBefore = pref1.getBoolean("isIntroOpnend",false);
        return  isIntroActivityOpnendBefore;



    }

    private void savePrefsData1() {

        SharedPreferences pref1 = this.getActivity().getSharedPreferences("myPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref1.edit();
        editor.putBoolean("isIntroOpnend",true);
        editor.commit();


    }


}
