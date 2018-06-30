package com.fpt.edu.converttool;

import android.icu.text.StringSearch;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DistanceConverter extends AppCompatActivity {

    Spinner spinner;
    private int currentUnit = 0;
    //                                              meter      | mile              | cm          | inch            | yd
    private float[][] arrDistanceConvertRate = {    {1,         0.000621371f,        100f,        39.3701f,         1.09361f},//meter
                                                    {1609.339f, 1,                   160933.9f,   63359.8f,         1759.99f},// mile
                                                    {0.01f,     9.999969f,           1,           0.39369f,         0.01093f},// cm
                                                    {0.025399f, 1.5782f,             2.53999f,    1,                0.02777f},// inch
                                                    {0.914f,    0.000568f,           91.4397f,    35.999f,          1}};      // yd

    EditText edt ;
    TextView textView;
    String[] arrUnit = {"Met", "Mile", "Cm", "Inch","yard"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance_converter);
        edt= findViewById(R.id.txtInput);

        textView=findViewById(R.id.txtResult);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, arrUnit);

        spinner = findViewById(R.id.spiner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //  Toast.makeText(this,"Change",Toast.LENGTH_LONG).show();
                Toast.makeText(DistanceConverter.this, ""+position, Toast.LENGTH_SHORT).show();
                convert(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                convert(currentUnit);



            }
        });








    }

    private void convert(int currentUnit) {

        if(edt.getText().toString().trim().length()<=0){
            return;

        }



        float value= Float.parseFloat(edt.getText().toString().trim());


         String result="";

        for (int i = 0; i <arrUnit.length ; i++) {
            if(currentUnit!=i){
                result+=arrUnit[i]+ ": ";
                result+=(value*arrDistanceConvertRate[currentUnit][i])+"";
                result+="\n";
            }
        }


        textView.setText(result);






    }
}
