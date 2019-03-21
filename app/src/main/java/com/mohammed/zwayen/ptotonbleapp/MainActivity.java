package com.mohammed.zwayen.ptotonbleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener {

    BLEManager bleman ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bleman = BLEManager.getInstance();
        bleman.initBle(this);
        initUI();
    }

    private void initUI()
    {
        Button btnStartHearingTest = findViewById(R.id.startHearingTest);
        Button btnStartScan = findViewById(R.id.scanBtn);
        Button btnStopScan = findViewById(R.id.stopScan);
        btnStartHearingTest.setOnClickListener(this);
        btnStartScan.setOnClickListener(this);
        btnStopScan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.scanBtn:
                Toast.makeText(this,"start scanning !",Toast.LENGTH_SHORT).show();
                bleman.startScanning();
                break;
            case R.id.stopScan:
                Toast.makeText(this,"stop scanning !",Toast.LENGTH_SHORT).show();
                bleman.stopScanning();
                break;
            case R.id.startHearingTest:
                Toast.makeText(this,"start gearing test !",Toast.LENGTH_SHORT).show();
                Intent startHraingTest = new Intent(this, HearingTestActivity.class);
                startActivity(startHraingTest);
                break;
        }
    }
}
