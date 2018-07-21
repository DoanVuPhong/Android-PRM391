package com.fpt.edu.tiengviet123;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fpt.edu.tiengviet123.bean.VCharacter;

import java.util.ArrayList;

public class Menu extends AppCompatActivity {
    ArrayList<VCharacter> list;
    Button btnTableCharacter, btnCamera,btnDetails;



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

        btnDetails=findViewById(R.id.btnDetails);
        btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu.this,Details.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("list", list);
                i.putExtra("data", bundle);
                startActivityForResult(i,2);
            }
        });


        btnCamera= findViewById(R.id.btnCamera);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchIntent= getPackageManager().getLaunchIntentForPackage("com.fpt.edu.arcamera");
                if (launchIntent != null) {
                    startActivity(launchIntent);//null pointer check in case package name was not found
                }else{
                    Toast.makeText(Menu.this, "ERROR When lauch Intent from other app",Toast.LENGTH_LONG).show();

                }
            }
        });

    }
}
