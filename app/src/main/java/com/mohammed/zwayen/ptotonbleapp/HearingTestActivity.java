package com.mohammed.zwayen.ptotonbleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import me.tankery.lib.circularseekbar.CircularSeekBar;

public class HearingTestActivity extends AppCompatActivity implements View.OnClickListener {


    private int userSelectedIdx;
    private RadioGroup rgFineStatus,rgDirction;
    private RadioButton rbFineStatusOn,rbFineStatusOff,rbDirLinks,rbDirRechts;
    private Spinner freqSelector;
    private TextView tvSeekBarValue;
    private Button voiceCtrlPHalf,voiceCtrlP,voiceCtrlM,iHeardBtn,sendHearingResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hearing_test);
        setTitle("Hearing Test..");
        userSelectedIdx = getIntent().getIntExtra("selectedUser",-1);
        Toast.makeText(this, "userSelectedIndex: " + userSelectedIdx, Toast.LENGTH_SHORT).show();
        initUi();
    }

    public void initUi()
    {
        rgFineStatus = findViewById(R.id.rgFineStatus);
        rbFineStatusOn = findViewById(R.id.rbFineOn);
        rbFineStatusOff = findViewById(R.id.rbFineOff);
        rbDirLinks = findViewById(R.id.rbDirLinks);
        rbDirRechts = findViewById(R.id.rbDirRechts);
        rbFineStatusOn.setOnClickListener(this);
        rbFineStatusOff.setOnClickListener(this);
        rbDirLinks.setOnClickListener(this);
        rbDirRechts.setOnClickListener(this);
        freqSelector = findViewById(R.id.freqSelector);
        ArrayAdapter<String> freqsAdapter = new ArrayAdapter<String>(HearingTestActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.freqs));
        freqsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        freqSelector.setAdapter(freqsAdapter);
        freqSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(HearingTestActivity.this, "freqSelected is"+parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        //final TextView textEvent = findViewById(R.id.text_event);
        //final TextView textProgress = findViewById(R.id.text_progress);
        CircularSeekBar seekBar = (CircularSeekBar) findViewById(R.id.seek_bar);
        seekBar.setOnSeekBarChangeListener(new CircularSeekBar.OnCircularSeekBarChangeListener() {
            @Override
            public void onProgressChanged(CircularSeekBar circularSeekBar, float progress, boolean fromUser) {
                String message = String.format("Progress changed to %.2f, fromUser %s", progress, fromUser);
                Log.d("Main", message);

                //textProgress.setText(message);
            }

            @Override
            public void onStopTrackingTouch(CircularSeekBar seekBar) {
                Log.d("Main", "onStopTrackingTouch");
                String val=String.format(" %.1f",seekBar.getProgress());
                tvSeekBarValue.setText(val);
                //TODO: send to the ProTon Device (val)
                //textEvent.setText("");
            }

            @Override
            public void onStartTrackingTouch(CircularSeekBar seekBar) {
                Log.d("Main", "onStartTrackingTouch");
                //textEvent.setText("touched | ");
            }
        });
        seekBar.setProgress(50);
        tvSeekBarValue = findViewById(R.id.tvSeekBarValue);
        tvSeekBarValue.setText(""+50);
        voiceCtrlPHalf = findViewById(R.id.voiceCtrlPhalf);
        voiceCtrlPHalf.setOnClickListener(this);
        voiceCtrlP = findViewById(R.id.voiceCtrlP);
        voiceCtrlP.setOnClickListener(this);
        voiceCtrlM = findViewById(R.id.voiceCtrlM);
        voiceCtrlM.setOnClickListener(this);
        iHeardBtn = findViewById(R.id.iHearedBtn);
        iHeardBtn.setOnClickListener(this);
        sendHearingResult = findViewById(R.id.sendHearingTest);
        sendHearingResult.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.rbFineOn:
                Toast.makeText(this, "R.id.rbFineOn", Toast.LENGTH_SHORT).show();

                break;
            case R.id.rbFineOff:
                Toast.makeText(this, "R.id.rbFineOff", Toast.LENGTH_SHORT).show();

                break;

            case R.id.rbDirLinks:
                Toast.makeText(this, "rbDirLinks", Toast.LENGTH_SHORT).show();

                break;
            case R.id.rbDirRechts:
                Toast.makeText(this, "rbDirRechts", Toast.LENGTH_SHORT).show();

                break;
            case R.id.voiceCtrlPhalf:
                Toast.makeText(this, "voiceCtrlPhalf btn", Toast.LENGTH_SHORT).show();
                break;
            case R.id.voiceCtrlP:
                Toast.makeText(this, "voiceCtrlP btn", Toast.LENGTH_SHORT).show();
                break;
            case R.id.voiceCtrlM:
                Toast.makeText(this, "voiceCtrlM btn", Toast.LENGTH_SHORT).show();
                break;

            case R.id.iHearedBtn:
                Toast.makeText(this, "iHeared btn", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sendHearingTest:
                Toast.makeText(this, "sendHearingTestResult btn", Toast.LENGTH_SHORT).show();
                sendHearingResult.setClickable(false);
                break;

        }
    }


}
