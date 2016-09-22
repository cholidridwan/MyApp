package com.edgeasia.myapplication;

import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    String fullPath = Environment.getExternalStorageDirectory().getPath() + "/Android/data/com.edge.myapp/";
    final File newFolder = new File(fullPath);
    final File[] contents = newFolder.listFiles();

    private String[] mFileStrings;
    private File[] listFile;
    private ViewPager viewPager;
    private ImageAdapter imageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewPageAndroid);
        imageAdapter = new ImageAdapter(this);

        if (!newFolder.exists()) {
            Log.i("mkdir", "success");
            newFolder.mkdir();
        }

        if (contents.length == 0) {
            Log.i("contain", "0");
            imageAdapter.setSetDefault(true);
        }else {
            Log.i("contain", String.valueOf(contents.length) + ":" + contents.toString());
            imageAdapter.setSetDefault(false);
            imageAdapter.setContentLength(contents.length);
            File file = new File(fullPath);

            if (file.isDirectory())
            {
                listFile = file.listFiles();
                mFileStrings = new String[listFile.length];

                for (int i = 0; i < listFile.length; i++)
                {
                    mFileStrings[i] = listFile[i].getAbsolutePath();
                    imageAdapter.setImage(mFileStrings);
                }
            }
        }

        viewPager.setAdapter(imageAdapter);

        Button btnTag = (Button) findViewById(R.id.btnTag);
        btnTag.setAlpha(0.5f); // it can set to ZERO
        btnTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(3, false);
            }
        });
    }
}
