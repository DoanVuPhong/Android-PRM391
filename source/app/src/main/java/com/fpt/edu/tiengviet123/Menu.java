package com.fpt.edu.tiengviet123;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fpt.edu.tiengviet123.bean.VCharacter;

import java.util.ArrayList;

public class Menu extends AppCompatActivity {
    ArrayList<VCharacter> list;
    Button btnTableCharacter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Bundle bd = getIntent().getBundleExtra("data");
        list = (ArrayList<VCharacter>) bd.getSerializable("list");
        btnTableCharacter = findViewById(R.id.btnCharacterTable1);
        btnTableCharacter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("list", list);
                Intent i = new Intent(Menu.this, CharacterTable1.class);
                i.putExtra("data", bundle);
                startActivity(i);
            }
        });
    }
}
