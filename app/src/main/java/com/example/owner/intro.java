package com.example.owner;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

public class intro extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_intro );
        checkPermission();
        Button button = (Button) findViewById( R.id.button7 );
        FirebaseOptions options1=new FirebaseOptions.Builder()
                .setApiKey("AIzaSyABcRbxWMARaSNyfY2fOz-PWykCQh1j2LA")
                .setApplicationId("com.example.owner")
                .setProjectId("admin-a70aa")
                .build();
        FirebaseApp.initializeApp(this,options1,"adminapp");
        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent( intro.this, MainLogin.class );

                startActivity( intent );
            }
        });

    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
       try{
        this.getSupportActionBar().hide();
    }catch (Exception e) {

       }  }
    public void checkPermission(){
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                ).withListener(new MultiplePermissionsListener() {

            @Override public void onPermissionsChecked(MultiplePermissionsReport report) {
            }


            @Override public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                token.continuePermissionRequest();
            }

        }).check();





    }
    @Override
    public void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser()!=null)
        {
            Intent intent=new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }



}
