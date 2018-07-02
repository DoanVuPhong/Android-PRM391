package com.fpt.edu.demoradiobtnandcheckbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    RadioButton male, female;
    CheckBox checkBox, cb1, cb2, cb3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkBox = findViewById(R.id.checkBox);
        cb1 = findViewById(R.id.cbx1);
        cb2 = findViewById(R.id.cbx2);
        cb3 = findViewById(R.id.cbx3);
        checkBox.setOnCheckedChangeListener(this);
        cb1.setOnCheckedChangeListener(this);
        cb2.setOnCheckedChangeListener(this);
        cb3.setOnCheckedChangeListener(this);


        male = findViewById(R.id.radioButton);
        female = findViewById(R.id.radioButton2);

        male.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(MainActivity.this, "GG Male dc chon roi nhe", Toast.LENGTH_LONG).show();
            }
        });
        female.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(MainActivity.this, "GG Female dc chon roi nhe", Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if( buttonView==checkBox){
           if(isChecked){
               Toast.makeText(MainActivity.this, "Check box is checked", Toast.LENGTH_SHORT).show();
           }
        }
        if( buttonView==cb1){
            if(isChecked){
                Toast.makeText(MainActivity.this, "Shopping is checked", Toast.LENGTH_SHORT).show();
            }
        }
        if( buttonView==cb2){
            if(isChecked){
                Toast.makeText(MainActivity.this, "PLay Game is checked", Toast.LENGTH_SHORT).show();
            }
        }
        if( buttonView==cb3){
            if(isChecked){
                Toast.makeText(MainActivity.this, "FootBall is checked", Toast.LENGTH_SHORT).show();
            }
        }




    }
}
