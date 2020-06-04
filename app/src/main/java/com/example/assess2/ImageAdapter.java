package com.example.assess2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context context;

    public int[] imageArray = {
            R.drawable.elephant, R.drawable.smile, R.drawable.vintagecar,R.drawable.santahat};

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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(imageArray[position]);
        imageView.setScaleType(ImageView.ScaleType.FIT_START);
        imageView.setLayoutParams(new GridLayout.LayoutParams());  //width=250, height=250
        return imageView;
    }
}
