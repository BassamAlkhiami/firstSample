package com.mohammed.zwayen.ptotonbleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener {

    //UI's
    Button btnStartHearingTest,btnLinaerOption,btnP1Option;
    EditText tvLinearOffest,tvP1Offest;
    TextView tvBaseState,tvCompState,tvVoiceValue;
    Switch baseSwitch,compSwitch;
    SeekBar sbVoiceVol;
    private RadioGroup rgIntensity;
    private RadioButton rbIntensity20,rbIntensity40,rbIntensity60,rbIntensity80,rbIntensity100;


    private int userSelectedIdx = -1;
    private BLEManager bleManager = BLEManager.getInstance();
    private CachManeger localStorageManeger = CachManeger.getIntstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userSelectedIdx = getIntent().getIntExtra("selectedUser",-1);
        Toast.makeText(this, "userSelectedIndex: " + userSelectedIdx, Toast.LENGTH_LONG).show();
        initUI();
    }

    private void initUI()
    {
        btnStartHearingTest = findViewById(R.id.startHearingTest);
        btnStartHearingTest.setOnClickListener(this);
        btnLinaerOption = findViewById(R.id.linearCmdBtn);
        btnLinaerOption.setOnClickListener(this);
        tvLinearOffest = findViewById(R.id.linearOffest);
        tvP1Offest = findViewById(R.id.p1Offset);
        tvBaseState = findViewById(R.id.tvBassState);
        tvCompState = findViewById(R.id.tvCompState);
        baseSwitch = findViewById(R.id.baseSwitch);
        baseSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setBaseStatus(isChecked);
            }
        });
        compSwitch = findViewById(R.id.compSwitch);
        compSwitch.setTextOff("Comp OFF");
        compSwitch.setTextOn("Comp ON");
        compSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setCompStatus(isChecked);
            }
        });
        tvVoiceValue = findViewById(R.id.tvVoiceValue);
        sbVoiceVol = findViewById(R.id.sbVoiceVolume);
        sbVoiceVol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                tvVoiceValue.setText(""+progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        sbVoiceVol.setProgress(50);
        tvVoiceValue.setText(""+50);
        rgIntensity = findViewById(R.id.rgIntensity);
        rbIntensity20 = findViewById(R.id.rbIntensity20);
        rbIntensity40 = findViewById(R.id.rbIntensity40);
        rbIntensity60 = findViewById(R.id.rbIntensity60);
        rbIntensity80 = findViewById(R.id.rbIntensity80);
        rbIntensity100 = findViewById(R.id.rbIntensity100);
        rbIntensity20.setOnClickListener(this);
        rbIntensity40.setOnClickListener(this);
        rbIntensity60.setOnClickListener(this);
        rbIntensity80.setOnClickListener(this);
        rbIntensity100.setOnClickListener(this);
    }

    private void setBaseStatus(boolean Checked)
    {
        if (Checked)
        {
            Toast.makeText(this, "base switch checked!", Toast.LENGTH_SHORT).show();
            tvBaseState.setText("Base ON");
        }
        else
        {
            Toast.makeText(this, "base switch unchecked!", Toast.LENGTH_SHORT).show();
            tvBaseState.setText("Base OFF");
        }
    }

    private void setCompStatus(boolean Checked)
    {
        if (Checked)
        {
            Toast.makeText(this, "comp switch checked!", Toast.LENGTH_SHORT).show();
            tvCompState.setText("Comp ON");
        }
        else
        {
            Toast.makeText(this, "comp switch unchecked!", Toast.LENGTH_SHORT).show();
            tvCompState.setText("Comp OFF");
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.startHearingTest:
                Toast.makeText(this,"start gearing test !",Toast.LENGTH_SHORT).show();
                Intent startHraingTest = new Intent(this, HearingTestActivity.class);
                startHraingTest.putExtra("selectedUser",userSelectedIdx);
                startActivity(startHraingTest);
                break;
            case R.id.linearCmdBtn:

                break;
            case R.id.p1CmdBtn:

                break;
            case R.id.rbIntensity20:

                break;
            case R.id.rbIntensity40:

                break;
            case R.id.rbIntensity60:

                break;
            case R.id.rbIntensity80:

                break;
            case R.id.rbIntensity100:

                break;


        }
    }
}
