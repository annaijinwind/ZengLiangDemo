package com.zengliang.demo;

import android.app.ProgressDialog;
import android.content.pm.ApplicationInfo;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.zengliang.demo.nativediff.DiffUtils;
import com.zengliang.demo.nativepatch.PatchUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

   private Button button;
   private Button button2;
    private Button button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.button);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        final File newApk = new File(Environment.getExternalStorageDirectory(), "fengjq/new.apk");
        final File toApk = new File(Environment.getExternalStorageDirectory(),"fengjq/to.apk");
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
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    copyFile(extract(),toApk.getAbsolutePath());
                    Toast.makeText(MainActivity.this, "好了",Toast.LENGTH_SHORT).show();

            }
        });
    }
    public  String extract() {
        ApplicationInfo applicationInfo = getApplicationContext().getApplicationInfo();
        String apkPath = applicationInfo.sourceDir;
        return apkPath;
    }

    //进行复制的函数
    public void copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) { //文件不存在时
                InputStream inStream = new FileInputStream(oldPath); //读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1024];
                int length;
                while ( (byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //字节数 文件大小
                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();

        }

    }
}
