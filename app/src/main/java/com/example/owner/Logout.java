package com.example.owner;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;


public class Logout extends DialogFragment {
    private static final String TAG = "MyCustomDialog";
    private Button btn,btn1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate( R.layout.fragment_logout, container, false );
        btn1=view.findViewById(R.id.button5);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(getActivity(),intro.class);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                startActivity(intent);
                getActivity().finishAffinity();
            }
        });
        // Button btnStart;
        //Dialog myDialog;    @Override
        //  protected void onCreate(Bundle savedInstanceState) {
        //   super.onCreate( savedInstanceState );

        // supportRequestWindowFeature( Window.FEATURE_NO_TITLE );


        //  this.getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        //setContentView( R.layout.fragment_logout );
        //  Button button =(Button)findViewById( R.id.button6 );
        // button.setOnClickListener( new View.OnClickListener() {
        //  @Override
        // public void onClick(View v) {
        //  Intent intent =new Intent(Logout.this,MainActivity.class);
        //startActivity( intent );

        //  }
        //    } );

        // }

    btn = view.findViewById( R.id.button6 );
        btn.setOnClickListener( new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          getDialog().dismiss();
        }
    } );
       return view;

}
}