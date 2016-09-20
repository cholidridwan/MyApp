package com.edgeasia.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by cholid_ridwan on 9/19/2016.
 */
public class ImageAdapter extends PagerAdapter {
    Context mContext;
    private int[] sliderImagesId = new int[]{
            R.drawable.p01, R.drawable.p03, R.drawable.p05,
            R.drawable.p02, R.drawable.p04, R.drawable.p06
    };
    private boolean setDefault = true;
    private int contentLength;
    private File imgFile;
    private String[] nameFile;
    private Bitmap myBitmap;

    ImageAdapter(Context context) {
        this.mContext = context;
    }

    public void setImage(String[] name){
        this.nameFile = name;
    }

    public void setSetDefault(boolean boo){
        setDefault = boo;
    }

    public void setContentLength(int contentLength){
        this.contentLength = contentLength;
    }

    @Override
    public int getCount() {
        if(!setDefault){
            return contentLength;
        }
        return sliderImagesId.length;
    }

    @Override
    public boolean isViewFromObject(View v, Object obj) {
        return v == ((ImageView) obj);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int i) {
        ImageView mImageView = new ImageView(mContext);
        mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        if(!setDefault) {
            imgFile = new File(nameFile[i]);
            myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            mImageView.setImageBitmap(myBitmap);
        }else{
            mImageView.setImageResource(sliderImagesId[i]);
        }
        ((ViewPager) container).addView(mImageView, 0);
        return mImageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int i, Object obj) {
        ((ViewPager) container).removeView((ImageView) obj);
    }
}
