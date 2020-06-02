package com.example.owner;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;


public class otp extends Fragment {
    private PhoneAuthProvider.ForceResendingToken resendToken;
    private FirebaseAuth mAuth;
    private String verificationid;
    private TextView resend;
    private ProgressBar progressBar;
    Button btn;
    private EditText edt;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate( R.layout.fragment_otp, container, false );
        progressBar =(ProgressBar)view.findViewById( R.id.progressBar );
        mAuth = FirebaseAuth.getInstance();
        final String phoneNumber = getArguments().getString( "number" );
        sendVerificationCode( phoneNumber );
        resend = (TextView) view.findViewById(R.id.textView129  );

        resend.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility( View.VISIBLE );
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            phoneNumber, 60, TimeUnit.SECONDS,
                            getActivity(),
                            mCallBck,
                          resendToken

                    );     Toast.makeText( getActivity(),"OTP sent",Toast.LENGTH_LONG ).show();

                }
        } );

        edt = (EditText) view.findViewById( R.id.editText12 );
        Button button = (Button) view.findViewById( R.id.button9 );
        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code =  edt.getText().toString().trim();
                if(code.isEmpty()|| code.length()<6){
                    edt.setError( "Please enter otp" );
                    edt.requestFocus();
                    return;
                   // getFragmentManager().beginTransaction().replace(R.id.log,new pulse()).commit();
                }
                verifyCode( code );
            }
        } );

        return view;
    }private void verifyCode(String Code)
    {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential( verificationid,Code );
        signInWithCredential(credential);
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential( credential )
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                            fragmentTransaction.replace( R.id.log, new pulse() );
                            fragmentTransaction.commit();

                        }
                        else{       edt.setError( "Please enter valid otp" );
                        Log.d("Exception kano:",task.getException().toString());
                        }
                    }
                } );
    }

    private void sendVerificationCode(String number){
        progressBar.setVisibility( View.VISIBLE );
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,60, TimeUnit.SECONDS,
                getActivity(),
                mCallBck

        );
        Toast.makeText( getActivity(),"OTP sent",Toast.LENGTH_LONG ).show();
    }




    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallBck = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
          super.onCodeSent( s, forceResendingToken );
            verificationid = s;

            resendToken= forceResendingToken;

        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            /*String code = phoneAuthCredential.getSmsCode();
            if(code != null)
            {
                edt.setText(code  );
                verifyCode( code );
            }*/
            signInWithCredential(phoneAuthCredential);
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            /*if(e instanceof FirebaseAuthInvalidCredentialsException){
                Log.d(TAG,"Invalid No.:" + e.getLocalizedMessage());

            }
            else if (e instanceof FirebaseTooManyRequestsException){
                Log.d(TAG,"You have exceeded your SMS Quota");
            }*/
           Toast.makeText( getActivity(),"FAILED",Toast.LENGTH_LONG ).show();

        }
    };
}