package com.fpt.edu.converttool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    RelativeLayout layoutDistance, layoutWeight, layoutTemperture, layoutSpeed;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layoutDistance=findViewById(R.id.distanceLayout);
        layoutWeight=findViewById(R.id.weightLayout);
        layoutTemperture=findViewById(R.id.layoutTemperture);
        layoutSpeed=findViewById(R.id.layoutSpeed);
        /*SET event for these layout*/

        layoutDistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t= new Intent(MainActivity.this, DistanceConverter.class);
                startActivity(t);
            }
        });









    }
}
