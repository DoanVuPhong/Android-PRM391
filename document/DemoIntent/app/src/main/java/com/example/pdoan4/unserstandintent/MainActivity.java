package com.example.pdoan4.unserstandintent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText edt1;
    EditText edt2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt1= this.findViewById(R.id.edt1);
        edt2= this.findViewById(R.id.edt2);
    }

    public void CallToOtherActivity(View view) {
        String value1 = edt1.getText().toString();
        String value2 = edt2.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString("var1",value1);
        bundle.putString("var2",value2);

        Intent t = new Intent(this, ChildActivity.class);
        t.putExtra("test",bundle);
        startActivity(t);
    }
}
