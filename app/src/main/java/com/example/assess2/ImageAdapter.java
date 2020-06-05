package com.example.assess2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context context;

    /* the image array used for grid view */
    public int[] imageArray = {
            R.drawable.elephant, R.drawable.smile, R.drawable.vintagecar};

    public ImageAdapter(Context context) {
        this.context= context;
    }

    @Override
    public int getCount() {
        return imageArray.length;
    }

    @Override
    public Object getItem(int position) {
        return imageArray[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    /* set image resource and layout params*/
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(imageArray[position]);
        imageView.setScaleType(ImageView.ScaleType.FIT_START); // use fit start to scale image
        imageView.setLayoutParams(new GridLayout.LayoutParams());
        return imageView;
    }
}
