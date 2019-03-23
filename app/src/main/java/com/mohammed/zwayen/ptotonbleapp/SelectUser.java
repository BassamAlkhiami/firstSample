package com.mohammed.zwayen.ptotonbleapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.mohammed.zwayen.ptotonbleapp.Model.User;

import java.util.ArrayList;

public class SelectUser extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener , CreateUserDialog.SelectUserDialogListener {

    BLEManager bleman ;
    CachManeger localStorage = CachManeger.getIntstance();
    ArrayAdapter<User> usersListAdapter;
    ListView lvCurrentUsers;
    ArrayList<User> currentUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user);
        //TODO: add code here to BLE connection
        bleman = BLEManager.getInstance();
        bleman.initBle(this);
        //bleman.sendTestMessage("from SelectUser Activity MSG!!");
        //TODO: start scn for BLE Device here
        localStorage.CachManagerInit(this);
        currentUsers = localStorage.getUsers();
        usersListAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,currentUsers);
        lvCurrentUsers = findViewById(R.id.lvCurrentUser);
        lvCurrentUsers.setAdapter(usersListAdapter);
        lvCurrentUsers.setOnItemClickListener(this);
        Button createUserbtn = findViewById(R.id.btnCreateUser);
        createUserbtn.setOnClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        User selectedUser = (User) parent.getItemAtPosition(position);
        Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
        Intent startMainActivity = new Intent(this,MainActivity.class);
        startMainActivity.putExtra("selectedUser",position);
        startActivity(startMainActivity);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnCreateUser:
                CreateUserDialog dialog = new CreateUserDialog();
                dialog.show(getSupportFragmentManager(),"Create User Dialog");

            break;
            case R.id.selectUserNextBtn:

            break;
        }
    }

    @Override
    public void applyName(String usrName,int idx) {
        User newUser = new User(usrName);
        localStorage.addUser(newUser);
        //TODO: create this user aslo in the proton device!
        currentUsers = localStorage.getUsers();
        usersListAdapter.notifyDataSetChanged();
        Toast.makeText(this, usrName, Toast.LENGTH_SHORT).show();
    }
}
