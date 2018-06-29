package com.fpt.edu.converttool;

import android.icu.text.StringSearch;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class DistanceConverter extends AppCompatActivity {

    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance_converter);
        ArrayList<String> dataSource = new ArrayList<>();
        dataSource.add("Met");
        dataSource.add("Km");
        dataSource.add("Cm");
        dataSource.add("Mile");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.activity_distance_converter,dataSource );

        spinner = findViewById(R.id.spiner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              //  Toast.makeText(this,"Change",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }
}
