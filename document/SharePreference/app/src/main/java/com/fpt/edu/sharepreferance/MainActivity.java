package com.fpt.edu.sharepreferance;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

        private SeekBar seekBar;
        private Switch aSwitch;
        private TextView currentValue;

        private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences= this.getSharedPreferences("mySetting", Context.MODE_PRIVATE);
        seekBar= findViewById(R.id.seekBar);
        aSwitch = findViewById(R.id.switch1);
        currentValue= findViewById(R.id.txtCurentValue);

        boolean switchOnOff= sharedPreferences.getBoolean("sound",false);
        int currentVolume =sharedPreferences.getInt("volume",15);

        currentValue.setText(String.valueOf(currentVolume));
        seekBar.setProgress(currentVolume);


        aSwitch.setChecked(switchOnOff);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.putBoolean("sound",isChecked);
                editor.commit();
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    currentValue.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.putInt("volume",seekBar.getProgress());
                editor.commit();
            }
        });
    }
}
