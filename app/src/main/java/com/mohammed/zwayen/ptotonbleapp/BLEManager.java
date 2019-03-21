package com.mohammed.zwayen.ptotonbleapp;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.util.Log;

import com.mohammed.zwayen.ptotonbleapp.Model.BLE_UUIDs;

public class BLEManager
{
    private final static String TAG = "BLEManager tag";
    private final static String BLUETOOTH_DEVICE_NAME = "Esp32 DevKit";
    private final static int REQUEST_ENABLE_BT = 1;
    private static final int PERMISSION_REQUEST_COARSE_LOCATION = 2;
    private static final BLEManager BALE_MANAGER = new BLEManager();
    private BluetoothManager btManager;
    private BluetoothAdapter btAdapter;
    private BluetoothLeScanner btScanner;
    private BluetoothDevice espdevice;
    private Activity ctxActivity;

    public static BLEManager getInstance() {
        return BALE_MANAGER;
    }

    private BLEManager() {

    }

    public void initBle(Activity ctx)
    {
        ctxActivity = ctx;
        btManager = (BluetoothManager)ctx.getSystemService(Context.BLUETOOTH_SERVICE);
        btAdapter = btManager.getAdapter();
        btScanner = btAdapter.getBluetoothLeScanner();
        checkBTEnable();
        checkBTPermission();
    }

    private void checkBTEnable()
    {
        if (btAdapter != null && !btAdapter.isEnabled()) {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            ctxActivity.startActivityForResult(enableIntent,REQUEST_ENABLE_BT);
        }
        Log.d(TAG,"checkBTEnable");
    }

    private void checkBTPermission()
    {
        // Make sure we have access coarse location enabled, if not, prompt the user to enable it
        if (ctxActivity.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(ctxActivity);
            builder.setTitle("This app needs location access");
            builder.setMessage("Please grant location access so this app can detect peripherals.");
            builder.setPositiveButton(android.R.string.ok, null);
            builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    ctxActivity.requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST_COARSE_LOCATION);
                }
            });
            builder.show();
        }

        Log.d(TAG,"checkBTPermission");
    }

    // Device scan callback.
    private ScanCallback leScanCallback = new ScanCallback() {

        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            if(result.getDevice().getName().equals(BLUETOOTH_DEVICE_NAME))
            {
                stopScanning();
                espdevice = result.getDevice();
                espdevice.connectGatt(ctxActivity.getApplicationContext(),false,gattCallback);
            }
            super.onScanResult(callbackType,result);
        }
    };


    BluetoothGattCallback gattCallback = new BluetoothGattCallback()
    {
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            /*
            TODO:
            int STATE_DISCONNECTED = 0;
            int STATE_CONNECTING = 1;
            int STATE_CONNECTED = 2;
            int STATE_DISCONNECTING = 3;
            */
            if (newState == BluetoothProfile.STATE_CONNECTED)
                gatt.discoverServices();
            super.onConnectionStateChange(gatt, status, newState);

        }

        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {

            Log.d("gattCallback", status+" ");
            BluetoothGattService service = gatt.getService(BLE_UUIDs.GPIO_SERVIS);
            BluetoothGattCharacteristic characteristic = service.getCharacteristic(BLE_UUIDs.LED_CHARACTERISITIC);
            String str = "mohammed";
            characteristic.setValue(str.getBytes());
            gatt.writeCharacteristic(characteristic);
            gatt.disconnect();

            super.onServicesDiscovered(gatt, status);
        }

        @Override
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            super.onCharacteristicRead(gatt, characteristic, status);
        }

        @Override
        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            super.onCharacteristicWrite(gatt, characteristic, status);
        }

        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            super.onCharacteristicChanged(gatt, characteristic);
        }
    };


    public void startScanning() {
        Log.d(TAG,"start scanning");
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                btScanner.startScan(leScanCallback);
            }
        });
    }

    public void stopScanning() {
        Log.d(TAG,"stopping scanning");
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                btScanner.stopScan(leScanCallback);
            }
        });
    }
}
