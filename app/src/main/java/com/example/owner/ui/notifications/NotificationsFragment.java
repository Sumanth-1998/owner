package com.example.owner.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.owner.R;
import com.example.owner.support;

public class NotificationsFragment extends Fragment {
  private View pview;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        pview = inflater.inflate( R.layout.fragment_notifications, container, false );
       Button btn = pview.findViewById( R.id.button3);
        btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment,new support());
                fragmentTransaction.addToBackStack(null );
                fragmentTransaction.commit();
            }
        } );
        return pview;


            }



}
