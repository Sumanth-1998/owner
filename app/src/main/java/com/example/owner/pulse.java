package com.example.owner;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.owner.ui.progress;
import com.gigamole.library.PulseView;

public class pulse extends Fragment {
    PulseView pulseView;
    Button button,btn,btn1;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate( R.layout.fragment_pulse, container, false );
         btn = (Button) view.findViewById( R.id.button15 );
         btn.setOnClickListener( new CompoundButton.OnClickListener() {
             @Override
             public void onClick(View v) {
                 button.setVisibility( View.VISIBLE );
             }
         } );


        button = (Button) view.findViewById( R.id.button4 );
        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent( getActivity(), progress.class );

                startActivity( intent );
            }
        });

        return view;
    }

}


  //  @Override
 //   protected void onCreate(Bundle savedInstanceState) {
     //   super.onCreate( savedInstanceState );
     //   setContentView( R.layout.fragment_pulse );

     //  pulseView = (PulseView) findViewById( R.id.pv );
     //   pulseView.startPulse();
     // btnStart = (Button) findViewById( R.id.text_edit1 );
       // btn2 = (Button) findViewById( R.id.button4 );

       // btn2.setOnClickListener( new View.OnClickListener() {
          //  @Override
         //   public void onClick(View v) {
            //    openActivity();



         //   }
       // } );

         //    btnStart.setOnClickListener( new View.OnClickListener() {
      // @Override
      // public void onClick(View v) {


   //   }
  // });
//}

// public void  openActivity() {
  //   Intent intent =  new Intent (this,MainActivity.class);
   //  startActivity( intent );
 //}
//}

