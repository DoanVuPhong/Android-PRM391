package com.example.pdoan4.layoutdemo;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RealativeLayout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realative_layout);
//        TextView txtView = findViewById(R.id.textView);
//        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/ClassiqueSaigon.ttf");
//        txtView.setTypeface(typeface);



    }

    public void channgImage(View view) {
        ImageView imageView = findViewById(R.id.imgView);
        imageView.setImageResource(R.drawable.furie2);
        Toast.makeText(this,"Hahahaha Ngon lanh canh dao",Toast.LENGTH_LONG);

    }
}
