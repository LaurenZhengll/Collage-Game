package com.example.assess2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

public class SaveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);

        Intent intent = getIntent();
        String backImage = intent.getStringExtra(MainActivity.EXTRA_BACKIMAGE);
        ImageView saveBackground = findViewById(R.id.imageView3);
        //saveBackground.setImageResource();
        //saveBackground.setImageDrawable(Drawable(backImage));
        //saveBackground.getDrawable();
    }
}
