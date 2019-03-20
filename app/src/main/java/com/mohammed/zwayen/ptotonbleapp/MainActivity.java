package com.mohammed.zwayen.ptotonbleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    BLEManager bleman ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bleman = BLEManager.getInstance();
        bleman.initBle(this);
    }


}
