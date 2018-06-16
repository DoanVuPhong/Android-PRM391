package com.fpt.edu.understandactivivty;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Thread.*;

public class MainActivity extends AppCompatActivity {

        private static final int INPUT_NAME_ACTIVITY=1;
        private CharSequence  items[] = {"Google.com","Facebook.com","Youtube.com"};
        private ProgressDialog progress ;
        private boolean[] checkedItem = new boolean[items.length];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("onCreate  is Involked");




//        if(savedInstanceState==null){
//            getSupportFragmentManager().beginTransaction().add(R.)
//
//        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("onStart involked!!!!");
    }


    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("OnResume involked!!!");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("On restart involked!!!");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("On Pause Involke");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("On Stop Involke");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("ON Destroy Onvolked");

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("On Save Instance state involke");
        outState.putString("phong","doan vu");


    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        System.out.println("On restore instance involked");
        System.out.println("GET state value that save before"+ savedInstanceState.getString("phong"));
    }

    public void moveIntent(View view) {
        Intent intent = new Intent(this, InputName.class);
         intent.putExtra("test","TEST value");
         startActivity(intent);


//        intent.setAction(Intent.ACTION_CALL);
//        startActivityForResult(intent, INPUT_NAME_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        TextView t = this.findViewById(R.id.txtResult);
        if(resultCode==RESULT_OK) {
            if (requestCode == INPUT_NAME_ACTIVITY) {
                Intent i = this.getIntent();
                String result = "Welcome to Android "+i.getExtras().getString("fullName");

                t.setText(result);
                t.setTextSize(30);

            }

        }else{
            t.setText("NHU CUT GA");






        }

    }

    public void clickToApplyTheme(View view) {
        Intent t = new Intent(this,ThemeStyleActivity.class);
        startActivity(t);


    }

    public void clickToShowDiaglog(View view) {
        showDialog(0);
    }

    public void clickToDisplayProgressDialog(View view) {
        final ProgressDialog progressBar = ProgressDialog.show(this,"Processing...","Please wait",true);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        sleep(5000);
                        progress.dismiss();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }).start();
    }

    public void clickToShowComplexProgressDialog(View view) {
        showDialog(1);
        progress.setProgress(0);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i <16 ; i++) {
                        sleep(1000);
                        progress.incrementProgressBy((int)100/15);
                    }
                    progress.dismiss();

                }catch(InterruptedException e){
                    e.printStackTrace();

                }

            }
        }).start();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id){
            case 0:  return new AlertDialog.Builder(this).setIcon(R.drawable.ic_launcher_background).setTitle("Dialog Demo")
                    .setPositiveButton("Chap Nhan", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(getBaseContext(),"Chap nhan Clicked",Toast.LENGTH_LONG).show();
                        }
                    }).setNegativeButton("Huy", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(getBaseContext(),"Huy Clicked",Toast.LENGTH_LONG).show();
                        }
                    }).setMultiChoiceItems(items, checkedItem, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                            Toast.makeText(getBaseContext(),items[i] +"  "+ (b?"Checked":"Unchecked"),Toast.LENGTH_LONG).show();
                        }
                    }).create();

            case 1: progress = new ProgressDialog(this);
                progress.setIcon(R.drawable.ic_launcher_background);
                progress.setTitle("Processing....");
                progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progress.setButton(DialogInterface.BUTTON_POSITIVE, "NGON", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getBaseContext(),"Ngon Clicked",Toast.LENGTH_LONG).show();
                    }
                });
                progress.setButton(DialogInterface.BUTTON_NEGATIVE, "Huy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getBaseContext(),"Huy Clicked",Toast.LENGTH_LONG).show();
                    }
                });
                return progress;



        }
        return null;
    }
}
