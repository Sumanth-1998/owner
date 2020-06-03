package com.example.owner;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import hotchemi.android.rate.AppRate;
interface  DrawerLocker{
    public void setDrawerLocked(boolean shouldLock);
}

public class MainActivity extends AppCompatActivity implements DrawerLocker {

    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        FirebaseOptions options=new FirebaseOptions.Builder()
                .setApiKey("AIzaSyBm4RMO66ueAx5kmBuCvKWjfWlyzFZqIgI")
                .setApplicationId("com.example.owner")
                .setProjectId("parkin-n-charge")
                .build();
        FirebaseApp.initializeApp(this,options,"usersapp");

        AppRate.with(this)
            .setInstallDays(0)
                .setLaunchTimes( 3 )
                .setRemindInterval( 3 )
                .monitor();

        AppRate.showRateDialogIfMeetsConditions(this);


        BottomNavigationView navView = findViewById( R.id.nav_view );
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.id.navigation_account)
                .build();
        NavController navController = Navigation.findNavController( this, R.id.nav_host_fragment );
        //NavigationUI.setupActionBarWithNavController( this, navController, appBarConfiguration );
        NavigationUI.setupWithNavController( navView, navController );
       this.getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
       try{
           this.getSupportActionBar().hide();
       }catch (Exception e){

       }

    }

    public void setDrawerLocked(boolean enabled) {
        if(enabled){
            drawerLayout.setDrawerLockMode( DrawerLayout.LOCK_MODE_LOCKED_CLOSED );
        }
        else
        {
            drawerLayout.setDrawerLockMode( DrawerLayout.LOCK_MODE_UNLOCKED );
        }
    }


}
