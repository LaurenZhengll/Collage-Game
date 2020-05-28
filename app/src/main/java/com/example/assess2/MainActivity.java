package com.example.assess2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements ImageFragment.ImageFragmentListener {

    /* transfer image between fragments*/
    public static final int PERMISSION_PICK_IMAGE = 1000;
    public static final int PERMISSION_INSERT_IMAGE = 1001;

    Bitmap originalBitmap, finalBitmap;
    ImageFragment imageFragment;
    BrushFragment brushFragment;
    CardView imageBtn;
    ImageView sticker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        //bottomNav.setOnNavigationItemSelectedListener(navListener);
        /* set default fragment on activity create*/
        //getSupportFragmentManager().beginTransaction().add(R.id.frag_container, new BrushFragment()).commit();

        imageBtn = findViewById(R.id.btnImage);

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

    private void loadImage(){

    }

    public void onClickBrushBtn(View view){ // click brush button
        brushFragment = new BrushFragment();
        //brushFragment.show(getSupportFragmentManager(),brushFragment.getTag()); //fragment pop up from buttom

        // Replace whatever is in container with brush fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, brushFragment).commit();
    }

    @Override
    public void onImageItemClick(int position) {
        sticker = findViewById(R.id.sticker);
        if (position == 0) {
            sticker.getDrawable().setLevel(1);
        } else if (position == 1) {
            sticker.getDrawable().setLevel(2);
        } else {
            sticker.getDrawable().setLevel(3);
        }
    }
}
