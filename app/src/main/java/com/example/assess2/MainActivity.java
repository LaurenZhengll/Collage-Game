package com.example.assess2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import java.util.UUID;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity implements ImageFragment.ImageFragmentListener {

    public static final int PERMISSION_INSERT_IMAGE = 1001;

    ImageFragment imageFragment;
    //BrushFragment brushFragment;
    CardView imageBtn;
    ImageView backgroundImg, imageContent, imageContent2, imageContent3,closeIcon, closeIcon2, closeIcon3;
    ViewGroup mainLayout, imageContainer,imageContainer2,imageContainer3;
    FrameLayout fragContainer;
    Toolbar toolbar;
    /* used for scaling stickers*/
    private ScaleGestureDetector SGD;
    private Matrix matrix = new Matrix();
    private float scale = 1f;
    /* used for moving stickers*/
    private int xDelta;
    private int yDelta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageBtn = findViewById(R.id.btnImage);
        backgroundImg = findViewById(R.id.imageView);
        fragContainer = findViewById(R.id.frag_container);
        toolbar = findViewById(R.id.toolbar);
        mainLayout = findViewById(R.id.mainLayout);
        imageContainer = findViewById(R.id.stickerContainer);
        imageContent = findViewById(R.id.imageContent);
        closeIcon = findViewById(R.id.icClose);
        imageContainer2 = findViewById(R.id.stickerContainer2);
        imageContent2 = findViewById(R.id.imageContent2);
        closeIcon2 = findViewById(R.id.icClose2);
        imageContainer3 = findViewById(R.id.stickerContainer3);
        imageContent3 = findViewById(R.id.imageContent3);
        closeIcon3 = findViewById(R.id.icClose3);


        /* get image without losing quality from camera by decoding file path */
        Bitmap backgroundImage = BitmapFactory.decodeFile(getIntent().getStringExtra(CaptureActivity.EXTRA_BACKIMAGE));
        if(backgroundImage!=null){
            backgroundImg.setImageBitmap(backgroundImage); } // set background image from camera
        else {
            Uri bgImagUri = (Uri) getIntent().getExtras().get(CaptureActivity.EXTRA_BACKIMAGE); // get gallery image uri
            backgroundImg.setImageURI(bgImagUri); } // set background image from gallery


        ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        /* detect scale gesture of stickers*/
        SGD = new ScaleGestureDetector(this, new ScaleListener());

        //BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        //bottomNav.setOnNavigationItemSelectedListener(navListener);
        /* set default fragment on activity create*/
        //getSupportFragmentManager().beginTransaction().add(R.id.frag_container, new BrushFragment()).commit();

    }

    private View.OnTouchListener onImageDragListener() {
        return new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int x = (int) event.getRawX();
                final int y = (int) event.getRawY();

                switch (event.getAction() & MotionEvent.ACTION_MASK){
                    case MotionEvent.ACTION_DOWN:
                        RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
                        xDelta = x - lParams.leftMargin;
                        yDelta = y - lParams.topMargin;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
                        layoutParams.leftMargin = x - xDelta;
                        layoutParams.topMargin = y - yDelta;
                        layoutParams.bottomMargin = 0;
                        layoutParams.rightMargin = 0;
                        v.setLayoutParams(layoutParams);
                        break;
                }
                mainLayout.invalidate();
                return true;
            }
        };
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

    /* set image fragment when clicking image button*/
    public void onClickImageBtn(View view){
        fragContainer.setVisibility(view.VISIBLE);
        imageFragment = new ImageFragment();
        // Replace whatever is in container with image fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, imageFragment).commit();
    }



    /*public void onClickBrushBtn(View view){ // click brush button
        fragContainer.setVisibility(view.VISIBLE);

        brushFragment = new BrushFragment();
        //brushFragment.show(getSupportFragmentManager(),brushFragment.getTag()); //fragment pop up from buttom

        // Replace whatever is in container with brush fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, brushFragment).commit();
    }*/



    /* implement interface and display sticker*/
    @Override
    public void onImageItemClick(int position) {
        if (position == 0) {
            imageContent.getDrawable().setLevel(1);
            closeIcon.setVisibility(View.VISIBLE);
            imageContainer.setVisibility(View.VISIBLE);
            imageContainer.setOnTouchListener(onImageDragListener()); // listen sticker drag event
        } else if (position == 1) {
            imageContent2.getDrawable().setLevel(2);
            closeIcon2.setVisibility(View.VISIBLE);
            imageContainer2.setVisibility(View.VISIBLE);
            imageContainer2.setOnTouchListener(onImageDragListener()); // listen sticker drag event
        } else {
            imageContent3.getDrawable().setLevel(3);
            closeIcon3.setVisibility(View.VISIBLE);
            imageContainer3.setVisibility(View.VISIBLE);
            imageContainer3.setOnTouchListener(onImageDragListener()); // listen sticker drag event
        }

    }

    /* click close icon to close a sticker */
    public void clickCloseIcon(View view){
        imageContainer.setVisibility(View.INVISIBLE);
    }
    public void clickCloseIcon2(View view){
        imageContainer2.setVisibility(View.INVISIBLE);
    }
    public void clickCloseIcon3(View view){
        imageContainer3.setVisibility(View.INVISIBLE);
    }

    /* this method is to save view and store in photo gallery*/
    public void save(View view){

        imageBtn.setVisibility(view.INVISIBLE);
        //brushBtn.setVisibility(view.INVISIBLE);
        fragContainer.setVisibility(view.INVISIBLE);
        toolbar.setVisibility(view.INVISIBLE);
        closeIcon.setVisibility(view.INVISIBLE);
        closeIcon2.setVisibility(view.INVISIBLE);
        closeIcon3.setVisibility(view.INVISIBLE);
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

    }


    /* sticker on touch listener*/
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        SGD.onTouchEvent(event);
        return true;
    }

    /* sticker scale listener*/
    private class ScaleListener implements ScaleGestureDetector.OnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scale *= detector.getScaleFactor();
            scale = Math.max(0.1f,Math.min(scale,5.0f));
            matrix.setScale(scale,scale);
            imageContent.setImageMatrix(matrix);
            backgroundImg.setImageMatrix(matrix);
            return true;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            return false;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {

        }
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
