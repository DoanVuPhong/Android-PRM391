package com.fpt.edu.tiengviet123;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.fpt.edu.tiengviet123.SqlHelper.MyDatabaseHelper;
import com.fpt.edu.tiengviet123.bean.VCharacter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        try {
            Thread.sleep(2000);
            Intent intent = new Intent(this, Menu.class);


            MyDatabaseHelper mySqlHelper = new MyDatabaseHelper(this);
            mySqlHelper.insertDefaultNote();
            ArrayList<VCharacter> list = (ArrayList<VCharacter>) mySqlHelper.getAllNote();
            Bundle bundle = new Bundle();
            bundle.putSerializable("list", list);
            intent.putExtra("data", bundle);
            startActivity(intent);
            finish();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
