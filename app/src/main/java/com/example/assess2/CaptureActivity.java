package com.example.assess2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class CaptureActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST_CODE = 1;
    private static final int IMAGE_PICK_CODE = 1000;
    private static final int GALLERY_PERMISSION_CODE = 1001;

    static String EXTRA_BACKIMAGE;
    private String currentPhotoPath = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);
    }

   /* *//* this method if for checking whether there is already permission of camera use *//*
    public void checkCameraPermissions(View view){
        *//* if permission has not been request, request permission, then call onRequestPermissionsResult method  *//*
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
        }
        else{  *//* if self already has permission, just open camera. *//*
            openCamera();
        }

    }

    *//* this method is to receive camera request result *//*
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


    *//* the openCamera() method starts image capture activity *//*
    private void openCamera() {
        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intentCamera, CAMERA_REQUEST_CODE);

    }

    *//* this method receives captured image and passes it to main activity *//*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CAMERA_REQUEST_CODE){ // receive image result from image capture activity
            Bitmap backgroudImage = (Bitmap) data.getExtras().get("data");
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(EXTRA_BACKIMAGE,backgroudImage);
            startActivity(intent);

        }
    }*/

    /* the clickCamera(View view) method start another activity to capture photo and get path of the image file*/
    public void clickCamera(View view){

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(cameraIntent.resolveActivity(getPackageManager())!= null){
            File imageFile = null;
            try{
                String fileName = "capturedPhoto";
                File storageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                imageFile = File.createTempFile(fileName,".jpg",storageDirectory); //create a new image file in file directory
                currentPhotoPath = imageFile.getAbsolutePath(); // get the absolute path of the new image file
            }catch (IOException e) {
                e.printStackTrace();
            }

            if(imageFile!=null){
                Uri imageUri = FileProvider.getUriForFile(this,"com.example.assess2.fileprovider", imageFile);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri); // bind captured photo with image file
                startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
            }
        }

    }


    /* this method pass photo path to main activity*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK){
            //Bitmap capturedImage = BitmapFactory.decodeFile(currentPhotoPath);
            Intent bgImageIntent = new Intent(this, MainActivity.class);
            bgImageIntent.putExtra(EXTRA_BACKIMAGE,currentPhotoPath);
            startActivity(bgImageIntent);
        }
        else if(requestCode == IMAGE_PICK_CODE && resultCode == RESULT_OK){
            Intent bgImageIntent = new Intent(this, MainActivity.class);
            bgImageIntent.putExtra(EXTRA_BACKIMAGE,data.getData());
            startActivity(bgImageIntent);

        }
    }

    /* this method if for checking whether there is already permission of gallery use */
    public void onClickGallery(View view){
        /* if permission has not been request, request permission, then call onRequestPermissionsResult method  */
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, GALLERY_PERMISSION_CODE);
        }
        else{  /* if self already has permission, just pick image from gallery. */
            pickImageFromGallery();
        }
    }


    /* this method is to receive gallery request result */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == GALLERY_PERMISSION_CODE){ // request gallery permission
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){ // if permission granted, then open camera
                pickImageFromGallery();
            }
            else{ // if permission not granted, then show toast message
                Toast.makeText(this,"Gallery permission denied",Toast.LENGTH_LONG).show();
            }
        }
    }

    /* Start activity to pick image from gallery*/
    private void pickImageFromGallery() {
        Intent pickIntent = new Intent(Intent.ACTION_PICK);
        pickIntent.setType("image/*");
        startActivityForResult(pickIntent, IMAGE_PICK_CODE);
    }


}
