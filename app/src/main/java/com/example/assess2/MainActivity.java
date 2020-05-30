package com.example.assess2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ViewUtils;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.drawable.DrawableUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.UUID;
import java.util.zip.Inflater;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity implements ImageFragment.ImageFragmentListener {

    /* transfer image between fragments*/
    public static final int PERMISSION_PICK_IMAGE = 1000;
    public static final int PERMISSION_INSERT_IMAGE = 1001;
    static String EXTRA_BACKIMAGE;

    Bitmap originalBitmap, finalBitmap;
    ImageFragment imageFragment;
    BrushFragment brushFragment;
    CardView imageBtn, brushBtn;
    ImageView sticker;
    ImageView backgroundImg;
    ViewGroup viewGroup = null;
    FrameLayout fragContainer;
    Toolbar toolbar;

    private LayoutInflater inflater;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        //bottomNav.setOnNavigationItemSelectedListener(navListener);
        /* set default fragment on activity create*/
        //getSupportFragmentManager().beginTransaction().add(R.id.frag_container, new BrushFragment()).commit();

        imageBtn = findViewById(R.id.btnImage);
        brushBtn = findViewById(R.id.btnBrush);
        backgroundImg = findViewById(R.id.imageView);
        fragContainer = findViewById(R.id.frag_container);
        inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        toolbar = findViewById(R.id.toolbar);

        ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

    }

    /* change fragment by setting navigation item select listener*//*
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()){
                case R.id.nav_brush:
                    selectedFragment = new BrushFragment();
                    break;
                case R.id.nav_text:
                    selectedFragment = new TextFragment();
                    break;
                case R.id.nav_image:
                    selectedFragment = new ImageFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().add(R.id.frag_container,selectedFragment).commit();
            return true;
        }
    };*/

    public void onClickImageBtn(View view){  // click image button
        /*Intent intent = new Intent(MainActivity.this,ImageFragment.class);
        *//*An intent carries key-value pairs called extras.*//*
        intent.setType("image/*");
        *//*system delivers intent*//*
        startActivityForResult(intent, PERMISSION_INSERT_IMAGE);*/
        fragContainer.setVisibility(view.VISIBLE);

        imageFragment = new ImageFragment();
        // Replace whatever is in container with image fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, imageFragment).commit();
        //addImageToPicture();
    }

    private void addImageToPicture() {

    }


    public void onClickBrushBtn(View view){ // click brush button
        fragContainer.setVisibility(view.VISIBLE);

        brushFragment = new BrushFragment();
        //brushFragment.show(getSupportFragmentManager(),brushFragment.getTag()); //fragment pop up from buttom

        // Replace whatever is in container with brush fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, brushFragment).commit();
    }

   /* @Override
    public void onImageItemClick(int position) {
        sticker = findViewById(R.id.sticker);
        if (position == 0) {
            sticker.getDrawable().setLevel(1);
        } else if (position == 1) {
            sticker.getDrawable().setLevel(2);
        } else {
            sticker.getDrawable().setLevel(3);
        }
        addViewToParent(sticker);
    }*/

    @Override
    public void onImageItemClick(int position) {

        //View imageRootView = inflater.inflate(R.layout.view_add_image, null);
        ImageView imageContent = findViewById(R.id.imageContent);
        //final FrameLayout frmBorder = imageRootView.findViewById(R.id.frmBorder);
        //final ImageView icClose = imageRootView.findViewById(R.id.icClose);

        /* display sticker */
        if (position == 0) {
            imageContent.getDrawable().setLevel(1);
        } else if (position == 1) {
            imageContent.getDrawable().setLevel(2);
        } else {
            imageContent.getDrawable().setLevel(3);
        }


      /*  MultiTouchListener multiTouchListener = getMultiTouchListener();
        multiTouchListener.setOnGestureControl(new MultiTouchListener.OnGestureControl() {
            @Override
            public void onClick() {
                boolean isBackgroundVisible = frmBorder.getTag() != null && (boolean) frmBorder.getTag();
                frmBorder.setBackgroundResource(isBackgroundVisible ? 0 : R.drawable.rounded_border_tv);
                imgClose.setVisibility(isBackgroundVisible ? View.GONE : View.VISIBLE);
                frmBorder.setTag(!isBackgroundVisible);
            }

            @Override
            public void onLongClick() {

            }
        });

        imageRootView.setOnTouchListener(multiTouchListener);

        addViewToParent(imageRootView);*/

    }

    public void save(View view){
        /*Intent intent = new Intent(MainActivity.this, SaveActivity.class);
        Drawable source = backgroundImg.getDrawable();
        intent.putExtra(MainActivity.EXTRA_BACKIMAGE, backgroundImg.);
        startActivity(intent);*/

        imageBtn.setVisibility(view.GONE);
        brushBtn.setVisibility(view.GONE);
        fragContainer.setVisibility(view.GONE);
        toolbar.setVisibility(view.GONE);
        view = findViewById(R.id.mainLayout);
        view.setSystemUiVisibility(view.SYSTEM_UI_FLAG_HIDE_NAVIGATION|view.SYSTEM_UI_FLAG_FULLSCREEN); // hide bottom navigation bar and top status bar

        /* take a screen shot and save to gallery as well as file folder */
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View saveView = getWindow().getDecorView();
        saveView.setDrawingCacheEnabled(true);
        String imagSaved = MediaStore.Images.Media.insertImage(getContentResolver(),saveView.getDrawingCache(),UUID.randomUUID().toString(),"paste up");
        if(imagSaved!=null){
            Toast savedToast = Toast.makeText(getApplicationContext(),"Image saved to gallery",Toast.LENGTH_LONG);
            savedToast.show();
        } else {
            Toast unsavedToast = Toast.makeText(getApplicationContext(),"Image not saved",Toast.LENGTH_LONG);
            unsavedToast.show();
        }
        saveView.destroyDrawingCache();


       /* *//* take a screen shot and save to file*//*
        View view1 = getWindow().getDecorView().getRootView();
        view1.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(view1.getDrawingCache());
        view1.setDrawingCacheEnabled(false);

        String filePath = Environment.getExternalStorageDirectory()+"/Download/"+ Calendar.getInstance().getTime().toString()+".jpg";
        File fileSavedImage = new File(filePath);

        FileOutputStream fileOutputStream = null;
        try{
            fileOutputStream = new FileOutputStream(fileSavedImage);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }*/

        /* jump to specific image in file *//*
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(fileSavedImage);
        intent.setDataAndType(uri,"image/jpeg");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);*/

    }

    /*public void save(View view){
        //drawingView.setDrawingCacheEnabled(true);
        String imagSaved = MediaStore.Images.Media.insertImage(getContentResolver(),drawingView.getDrawingCache(),UUID.randomUUID().toString()+".png","drawing");
        if(imagSaved!=null){
            Toast savedToast = Toast.makeText(getApplicationContext(),"Image saved",Toast.LENGTH_LONG);
            savedToast.show();
        } else {
            Toast unsavedToast = Toast.makeText(getApplicationContext(),"Image not saved",Toast.LENGTH_LONG);
            unsavedToast.show();
        }
        //drawingView.destroyDrawingCache();
    }*/

   /*private void addViewToParent(View rootView) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);

        viewGroup.addView(backgroundImg);
        viewGroup.addView(rootView,params);

        //addedViews.add(rootView);
        *//*if (mOnPhotoEditorListener != null)
            mOnPhotoEditorListener.onAddViewListener(viewType, addedViews.size());*//*
    }*/
}
