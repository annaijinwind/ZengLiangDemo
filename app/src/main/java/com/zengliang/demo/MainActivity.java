package com.zengliang.demo;

import android.app.ProgressDialog;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.zengliang.demo.nativediff.DiffUtils;
import com.zengliang.demo.nativepatch.PatchUtils;

import java.io.File;

public class MainActivity extends AppCompatActivity {

   private Button button;
   private Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.button);
        button2=findViewById(R.id.button2);
        final File newApk = new File(Environment.getExternalStorageDirectory(), "fengjq/new.apk");
        final File newscApk = new File(Environment.getExternalStorageDirectory(), "fengjq/lalal.apk");
        final File oldApk = new File(Environment.getExternalStorageDirectory(), "fengjq/old.apk");
        final File patch = new File(Environment.getExternalStorageDirectory(), "fengjq/patch.patch");
        final ProgressDialog progressDialog= new ProgressDialog(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        DiffUtils.getDiffPatch(oldApk.getAbsolutePath(),newApk.getAbsolutePath(),patch.getAbsolutePath());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                                Toast.makeText(MainActivity.this,"好了",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).start();


            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        PatchUtils.getNewApk(oldApk.getAbsolutePath(),newscApk.getAbsolutePath(),patch.getAbsolutePath());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                                Toast.makeText(MainActivity.this,"好了",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).start();
            }
        });
    }
}
