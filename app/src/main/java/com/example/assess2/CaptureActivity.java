package com.example.assess2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

public class CaptureActivity extends AppCompatActivity {
    public static final int CAMERA_PERMISSION_CODE = 101;
    public static final int CAMERA_REQUEST_CODE = 102;

    static String EXTRA_BACKIMAGE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);
    }

    /* this method if for checking whether there is already permission of camera use */
    public void checkCameraPermissions(View view){
        /* if permission has not been request, request permission, then call onRequestPermissionsResult method  */
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
        }
        else{  /* if self already has permission, just open camera. */
            openCamera();
        }

    }

    /* this method is to receive camera request result */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == CAMERA_PERMISSION_CODE){ // request camera permission
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){ // if permission granted, then open camera
                openCamera();
            }
            else{ // if permission not granted, then show toast message
                Toast cameraNotGranted = Toast.makeText(getApplicationContext(),"Camera permission is required",Toast.LENGTH_LONG);
                cameraNotGranted.show();
            }
        }
    }


    /* the openCamera() method starts image capture activity */
    private void openCamera() {
        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intentCamera, CAMERA_REQUEST_CODE);

    }

    /* this method receives captured image and passes it to main activity */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CAMERA_REQUEST_CODE){ // receive image result from image capture activity
            Bitmap backgroudImage = (Bitmap) data.getExtras().get("data");
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(EXTRA_BACKIMAGE,backgroudImage);
            startActivity(intent);

        }
    }
}
