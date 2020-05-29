package com.example.assess2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.UUID;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity implements ImageFragment.ImageFragmentListener {

    /* transfer image between fragments*/
    public static final int PERMISSION_PICK_IMAGE = 1000;
    public static final int PERMISSION_INSERT_IMAGE = 1001;

    Bitmap originalBitmap, finalBitmap;
    ImageFragment imageFragment;
    BrushFragment brushFragment;
    CardView imageBtn;
    ImageView sticker;
    ImageView backgroundImg;
    ViewGroup viewGroup = null;

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
        backgroundImg = findViewById(R.id.imageView);

        inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);


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

        imageFragment = new ImageFragment();
        // Replace whatever is in container with image fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, imageFragment).commit();
        //addImageToPicture();
    }

    private void addImageToPicture() {

    }


    public void onClickBrushBtn(View view){ // click brush button
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

    /*public void save(View view){
        drawingView.setDrawingCacheEnabled(true);
        String imagSaved = MediaStore.Images.Media.insertImage(getContentResolver(),drawingView.getDrawingCache(), UUID.randomUUID().toString()+".png","drawing");
        if(imagSaved!=null){
            Toast savedToast = Toast.makeText(getApplicationContext(),"Image saved",Toast.LENGTH_LONG);
            savedToast.show();
        } else {
            Toast unsavedToast = Toast.makeText(getApplicationContext(),"Image not saved",Toast.LENGTH_LONG);
            unsavedToast.show();
        }
        drawingView.destroyDrawingCache();

    }
*/
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
