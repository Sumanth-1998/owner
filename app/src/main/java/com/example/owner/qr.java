package com.example.owner;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.owner.ui.dashboard.DashboardFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

import static android.content.Context.WINDOW_SERVICE;

public class qr  extends Fragment {

    String TAG = "GenerateQRCode";
    TextView edtValue;
    ImageView qrImage;
    Button start, save;
    String inputValue;
    String savePath = Environment.getExternalStorageDirectory().getPath() + "/QRCode/";
    Bitmap bitmap;
    QRGEncoder qrgEncoder;
    Bitmap mBitmap;
    FirebaseFirestore db;

    @Override


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        db=FirebaseFirestore.getInstance();
        View view = inflater.inflate( R.layout.fragment_qr, container, false );
       Button btn = view.findViewById( R.id.button24 );
        btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment,new DashboardFragment());

                fragmentTransaction.commit();
            }
        } );

        qrImage = (ImageView) view.findViewById( R.id.QR_Image );
        edtValue = (TextView) view.findViewById( R.id.edt_value );
        start = (Button) view.findViewById( R.id.start );
        save = (Button) view.findViewById( R.id.save );
        db.collection("owners").document(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber())
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                        edtValue.setText(documentSnapshot.getString("Name"));
                        start.setOnClickListener( new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                save.setVisibility( View.VISIBLE );
                                inputValue = edtValue.getText().toString().trim();
                                if (inputValue.length() > 0) {
                                    WindowManager manager = (WindowManager) getContext().getSystemService( WINDOW_SERVICE );
                                    Display display = manager.getDefaultDisplay();
                                    Point point = new Point();
                                    display.getSize( point );
                                    int width = point.x;
                                    int height = point.y;
                                    int smallerDimension = width < height ? width : height;
                                    smallerDimension = smallerDimension * 2 / 3;

                                    qrgEncoder = new QRGEncoder(
                                            inputValue, null,
                                            QRGContents.Type.TEXT,
                                            smallerDimension );
                                    try {
                                        bitmap = qrgEncoder.encodeAsBitmap();
                                        qrImage.setImageBitmap( bitmap );
                                    } catch (WriterException e) {
                                        Log.v( TAG, e.toString() );
                                    }
                                } else {
                                    edtValue.setError( "Required" );
                                }
                            }
                        } );

                        save.setOnClickListener( new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String output;
                                String result;
                                try {

                                    String url = MediaStore.Images.Media.insertImage( getContext().getContentResolver(), bitmap,
                                            "ParkInnChargeService-qr.jpg", null );

                                } catch (Exception e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                            }
                        } );
                    }
                });




return view;
    }
}
