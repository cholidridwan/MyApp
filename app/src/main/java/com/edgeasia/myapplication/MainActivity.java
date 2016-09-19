package com.edgeasia.myapplication;

import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    String fullPath = Environment.getExternalStorageDirectory().getPath() + "/Android/data/com.edge.myapp/";
    final File newFolder = new File(fullPath);
    final File[] contents = newFolder.listFiles();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewPageAndroid);
        ImageAdapter adapterView = new ImageAdapter(this);
        mViewPager.setAdapter(adapterView);

        if (!newFolder.exists()) {
            Log.i("mkdir", "success");
            newFolder.mkdir();
        }

        if (contents.length == 0) {
            Log.i("contain", "0");
        }else {
            Log.i("contain", String.valueOf(contents.length) + ":" + contents.toString());
        }

    }
}
