package com.edgeasia.myapplication;

import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    final String fullPath = Environment.getExternalStorageDirectory().getPath() + "/Android/data/com.edge.myapp/";
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
                //viewPager.setCurrentItem(3, false);
                Log.i("page", String.valueOf(viewPager.getCurrentItem()));
            }
        });

        final EditText nameApp = (EditText)findViewById(R.id.nameApp);

        Button btnCreate = (Button) findViewById(R.id.btnCreate);
        btnCreate.setAlpha(0.5f); // it can set to ZERO
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String customPath = Environment.getExternalStorageDirectory().getPath() + "/Android/data/com.edge." + nameApp.getText();
                File customFolder = new File(customPath);
                File[] customContents = customFolder.listFiles();

                if (!customFolder.exists()) {
                    Log.i("mkdir", "success");
                    customFolder.mkdir();
                }
            }
        });

        Button btnOpen = (Button) findViewById(R.id.btnOpen);
        btnOpen.setAlpha(0.5f); // it can set to ZERO
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String customPath = Environment.getExternalStorageDirectory().getPath() + "/Android/data/com.edge." + nameApp.getText();
                File customFolder = new File(customPath);
                File[] customContents = customFolder.listFiles();

                if (customContents.length == 0) {
                    Log.i("contain", "0");
                    imageAdapter.setSetDefault(true);
                }else {
                    Log.i("contain", String.valueOf(customContents.length) + ":" + customContents.toString());
                    imageAdapter.setSetDefault(false);
                    imageAdapter.setContentLength(customContents.length);
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
            }
        });

        Button btnDelete = (Button) findViewById(R.id.btnDelete);
        btnDelete.setAlpha(0.5f); // it can set to ZERO
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String customPath = Environment.getExternalStorageDirectory().getPath() + "/Android/data/com.edge." + nameApp.getText();
                File customFolder = new File(customPath);
                if (customFolder.isDirectory())
                {
                    String[] children = customFolder.list();
                    for (int i = 0; i < children.length; i++)
                    {
                        new File(customFolder, children[i]).delete();
                    }
                }

                customFolder.delete();
            }
        });
    }
}
