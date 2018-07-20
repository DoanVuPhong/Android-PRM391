package com.fpt.edu.tiengviet123;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fpt.edu.tiengviet123.bean.VCharacter;

import java.util.ArrayList;
import java.util.List;

public class CharacterTable2 extends AppCompatActivity {
    ArrayList<VCharacter> list;
    Button btnN, btnO, btnO6, btnO7, btnP, btnQ, btnR, btnS, btnT, btnU, btnU7, btnV, btnX, btnY;
    Button btnChangeStyle1;
    boolean isUpercase=false;
    Button btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_table2);

        Bundle bd = getIntent().getBundleExtra("data");
        list = (ArrayList<VCharacter>) bd.getSerializable("list");

        btnN = findViewById(R.id.btnN);
        btnO = findViewById(R.id.btnO);
        btnO6 = findViewById(R.id.btnO6);
        btnO7 = findViewById(R.id.btnO7);
        btnP = findViewById(R.id.btnP);
        btnQ = findViewById(R.id.btnQ);
        btnR = findViewById(R.id.btnR);
        btnS = findViewById(R.id.btnS);
        btnT = findViewById(R.id.btnT);
        btnU = findViewById(R.id.btnU);
        btnU7 = findViewById(R.id.btnU7);
        btnV = findViewById(R.id.btnV);
        btnX = findViewById(R.id.btnX);
        btnY= findViewById(R.id.btnY);

        final List<Button> listBtn1 = new ArrayList<>();

        listBtn1.add(btnN);
        listBtn1.add(btnO);
        listBtn1.add(btnO6);
        listBtn1.add(btnO7);
        listBtn1.add(btnP);
        listBtn1.add(btnQ);
        listBtn1.add(btnR);
        listBtn1.add(btnS);
        listBtn1.add(btnT);
        listBtn1.add(btnU);
        listBtn1.add(btnU7);
        listBtn1.add(btnV);
        listBtn1.add(btnX);
        listBtn1.add(btnY);

        for (int i = 0; i < listBtn1.size(); i++) {
            final int finalI = i;
            listBtn1.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Utils.playAudio(listBtn1.get(finalI).getText().toString(), list, CharacterTable2.this);
                }
            });
        }

        btnChangeStyle1 = findViewById(R.id.btnChangeStyle);
        btnChangeStyle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i <listBtn1.size() ; i++) {

                    listBtn1.get(i).setText(listBtn1.get(i).getText().toString().toUpperCase());
                }
                if(isUpercase){
                    isUpercase=false;
                    for (int i = 0; i <listBtn1.size() ; i++) {

                        listBtn1.get(i).setText(listBtn1.get(i).getText().toString().toUpperCase());
                    }

                }else{
                    isUpercase=true;
                    for (int i = 0; i <listBtn1.size() ; i++) {

                        listBtn1.get(i).setText(listBtn1.get(i).getText().toString().toLowerCase());
                    }
                }
            }
        });

        btnBack = findViewById(R.id.btnBack1);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharacterTable2.this.finish();

            }
        });
    }
}
