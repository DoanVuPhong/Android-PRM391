package com.fpt.edu.tiengviet123;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.fpt.edu.tiengviet123.bean.VCharacter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageView imgView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgView = findViewById(R.id.imgView);

        Bundle bd =  getIntent().getBundleExtra("data");

        ArrayList<VCharacter> list = (ArrayList<VCharacter>) bd.getSerializable("list");

        Intent i = new Intent(this, CharacterTable1.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("list", list);
        i.putExtra("data", bundle);

        startActivity(i);



    }
}
