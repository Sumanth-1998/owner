package com.example.owner;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.kofigyan.stateprogressbar.StateProgressBar;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;


public class fragment_progress1 extends Fragment {
    private static final int IMAGE_PICKER_SELECT = 1000;


    String[] descriptionData = {"Photo", "My details",  "Submit"};
    Button btn;
    private CircleImageView ProfileImage;
    private static final int PICK_IMAGE = 1;
    Uri imageUri;


 CircleImageView circleImageView;
    private static final int  IMAGE_PICK_CODE=1001;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate( R.layout.fragment_progress1, container, false );
        circleImageView  = view.findViewById( R.id.Profile_Image );
        circleImageView.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                    Intent.ACTION_PICK,
                     android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, PICK_IMAGE);


               // Intent gallery = new Intent();
              //  gallery.setType("image/*");
             //   gallery.setAction(Intent.ACTION_GET_CONTENT);

              //  startActivityForResult(Intent.createChooser(gallery, "Sellect Picture"), PICK_IMAGE);
            }

        });







        StateProgressBar stateProgressBar = (StateProgressBar) view.findViewById(R.id.progress1);
        stateProgressBar.setStateDescriptionData(descriptionData);
        stateProgressBar.setStateSize(30f);
        stateProgressBar.setStateNumberTextSize(15f);
        stateProgressBar.setStateLineThickness(5f);
        btn = view.findViewById( R.id.button19 );
        btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.pro,new fragment_progress2());

                fragmentTransaction.commit();
            }
        } );
        return view;}

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );


        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            CropImage.activity(imageUri)
                    .setGuidelines( CropImageView.Guidelines.ON )
                    .setAspectRatio( 1,1 )
                .start( getContext(), this );
        }
            if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                CropImage.ActivityResult result = CropImage.getActivityResult( data );
                if (resultCode == RESULT_OK) {
                    Uri resultUri = result.getUri();

                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap( getContext().getContentResolver(), resultUri );
                        circleImageView.setImageBitmap( bitmap );
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    //@Override
  //  public void onActivityResult(int requestCode, int resultCode, Intent data)
  //  {
     //   if (requestCode == IMAGE_PICKER_SELECT
      //          && resultCode == Activity.RESULT_OK) {
      //      String path = getPathFromCameraData(data, this.getActivity());
      //      Log.i("PICTURE", "Path: " + path);

     //   }
   // }


   // public static String getPathFromCameraData(Intent data, Context context) {
   //     Uri selectedImage = data.getData();
    //    String[] filePathColumn = { MediaStore.Images.Media.DATA };
    //    Cursor cursor = context.getContentResolver().query(selectedImage,
       //         filePathColumn, null, null, null);
     //   cursor.moveToFirst();
      //  int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
      //  String picturePath = cursor.getString(columnIndex);
       // cursor.close();
      //  return picturePath;
   // }
