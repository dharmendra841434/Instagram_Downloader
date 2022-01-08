package com.example.instagram_downloader;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.hcr2bot.instagramvideosdownloader.InstaVideo;

import java.util.Timer;
import java.util.TimerTask;

import javax.xml.datatype.Duration;

public class MainActivity extends AppCompatActivity {

    TextInputEditText myurl;
    Button download,past,clr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        myurl = findViewById(R.id.url);
        download = findViewById(R.id.downloadbtn);
        past = findViewById(R.id.pastbtn);
        clr = findViewById(R.id.clearbtn);


        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= 23) {
                    if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_GRANTED) {
                    } else {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    }
                }
                else { //you dont need to worry about these stuff below api level 23

                }

                String MYURL = myurl.getText().toString();
                InstaVideo.downloadVideo(MainActivity.this,MYURL,"InstaVideos");



                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Please Subscribe My YouTube Channel");
                builder.setPositiveButton("GO TO CHANNEL", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCdMrmvbzofHJlTQV49maAVA")));

                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
               alert.show();
            }
        });
        past.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ClipboardManager myClipboard;
                myClipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);

                ClipData abc = myClipboard.getPrimaryClip();
                ClipData.Item item = abc.getItemAt(0);
                String text = item.getText().toString();
                myurl.setText(text);

            }
        });
        clr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myurl.setText("");
            }
        });
    }
}