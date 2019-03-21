package com.mohammed.zwayen.ptotonbleapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class CreateUserDialog extends AppCompatDialogFragment {

    private EditText edUserName ;
    private SelectUserDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.create_user_layout,null);

        builder.setView(v)
                .setTitle("Craete New User:")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Ok",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String usrName = edUserName.getText().toString();
                        listener.applyName(usrName,1);
                    }
                });
        edUserName = v.findViewById(R.id.createdUserName);
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (SelectUserDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +"class must implement SelectUserDialogListener");
        }
    }

    public interface SelectUserDialogListener {
        void applyName(String usrName,int idx);
    }
}
