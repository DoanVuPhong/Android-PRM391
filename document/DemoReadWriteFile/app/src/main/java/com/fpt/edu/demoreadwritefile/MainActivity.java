package com.fpt.edu.demoreadwritefile;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    Button btnWriteSD, btnWriteInternal, btnReadImage, btnDelete;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnWriteSD = findViewById(R.id.btnWriteSD);
        btnWriteInternal = findViewById(R.id.btnWriteInternal);
        btnReadImage = findViewById(R.id.btnReadImage);
        btnDelete = findViewById(R.id.btnDeleteImage);
        imageView = findViewById(R.id.imageView);

        int imageID=R.drawable.android_icon;


         imageView.setImageResource(imageID);





        // set listener for button
        btnReadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Bitmap image = Utils.readFile("test.png", MainActivity.this);
                    if (image != null) {
                        imageView.setImageBitmap(image);
                    } else {
                        Toast.makeText(MainActivity.this, "File not found", Toast.LENGTH_SHORT).show();

                    }


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }


            }
        });
        btnWriteSD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap image = BitmapFactory.decodeResource(MainActivity.this.getResources(), R.drawable.android_icon);
                try {
                    Utils.writeImageToSD(image, "test.png", MainActivity.this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        btnWriteInternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap image = BitmapFactory.decodeResource(MainActivity.this.getResources(), R.drawable.android_icon);
                try {
                    Utils.writeImageToInternalMemory(image, "test.png", MainActivity.this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.deleteImage("test.png",MainActivity.this);
            }
        });


    }
}
