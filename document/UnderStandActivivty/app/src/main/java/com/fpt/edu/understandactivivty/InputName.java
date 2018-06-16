package com.fpt.edu.understandactivivty;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;

public class InputName extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_name);
        Intent itent = this.getIntent();
        System.out.println(itent.getExtras().getString("test"));
        String value =itent.getExtras().getString("test");


    }

    public void onClickDoneInput(View view) {
        Intent itent = new Intent();

        EditText txt = this.findViewById(R.id.txtName);
        String result = txt.getText().toString();
        if (result.equals("phong")) {


            itent.putExtra("fullName", result);
            this.setResult(RESULT_OK, itent);
            this.finish();
        }
        else{
            itent.putExtra("fullName", result);
            this.setResult(RESULT_CANCELED, itent);
            this.finish();

        }


        }
    }
