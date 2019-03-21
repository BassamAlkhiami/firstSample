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


    CachManeger localStorage = CachManeger.getIntstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user);
        CachManeger localStorage = CachManeger.getIntstance();
        localStorage.CachManagerInit(this);
        ArrayList<User> currentUsers = localStorage.getUsers();
        ListAdapter usersAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,currentUsers);
        ListView lvCurrentUsers = findViewById(R.id.lvCurrentUser);
        lvCurrentUsers.setAdapter(usersAdapter);
        lvCurrentUsers.setOnItemClickListener(this);
        Button createUserbtn = findViewById(R.id.btnCreateUser);
        createUserbtn.setOnClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        User selectedUser = (User) parent.getItemAtPosition(position);
        Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();

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
                Intent intent = new Intent(this,MainActivity.class);

            break;
        }
    }

    @Override
    public void applyName(String usrName,int idx) {
        Toast.makeText(this, usrName, Toast.LENGTH_SHORT).show();
    }
}
