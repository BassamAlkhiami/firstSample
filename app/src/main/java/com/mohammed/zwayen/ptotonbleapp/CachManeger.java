package com.mohammed.zwayen.ptotonbleapp;

import android.app.Activity;
import android.widget.Toast;
import com.mohammed.zwayen.ptotonbleapp.Model.User;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CachManeger
{
    private static final CachManeger intstance = new CachManeger();
    private final static String FILE_NAME = "proton data";
    private FileOutputStream fos;
    private Activity activityContex;
    ArrayList<User> users = new ArrayList<>();

    private CachManeger()
    {

    }

    public static CachManeger getIntstance() {
        return intstance;
    }

    public void CachManagerInit(Activity ctx)
    {
        activityContex = ctx;
        User u1 = new User("mohammed");
        User u2 = new User("bassam");
        User u3 = new User("Andi");
        User u4 = new User("Artour");
        users.add(u1);
        users.add(u2);
        users.add(u3);
        users.add(u4);
    }

    public void addUser(User usr)
    {
        users.add(usr);
    }


    public void storeData(String dataToStore)
    {
        try {
            fos = activityContex.openFileOutput(FILE_NAME, activityContex.MODE_PRIVATE);
            fos.write(dataToStore.getBytes());
            fos.close();

            Toast.makeText(activityContex, FILE_NAME + " saved", Toast.LENGTH_SHORT).show();


        } catch (FileNotFoundException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}

    }

    public void createUser(User usr)
    {
        users.add(usr);
    }

    public ArrayList<User> getUsers()
    {
        return  users;
    }

}




