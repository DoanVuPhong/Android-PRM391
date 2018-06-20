package com.example.pdoan4.unserstandintent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ChildActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);
        Intent i = this.getIntent();
        Bundle bundle = i.getExtras();
        Double var1= Double.parseDouble(bundle.getString("var1"));
        Double var2= Double.parseDouble(bundle.getString("var2"));
        ((TextView)this.findViewById(R.id.txtResult)).setText(var1+var2+"");







    }

    public void backToCaller(View view) {
        finish();
    }
}
