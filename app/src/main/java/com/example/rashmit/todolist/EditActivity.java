package com.example.rashmit.todolist;

import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditActivity extends DialogFragment {

    MainActivity mainActivity;
    int position;


    public static EditActivity getInstance(MainActivity mainActivity, int position){
        EditActivity editActivity =  new EditActivity();
        editActivity.position = position;
        editActivity.mainActivity = mainActivity;
        return editActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.activity_edit, container, false);

        final EditText addItems = (EditText) v.findViewById(R.id.edititem);
        addItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println();
                getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
            }
        });

        final Button submitbutton = (Button) v.findViewById(R.id.save);
        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = addItems.getText().toString();
                addItems.setText("");
                addItems.setWidth(1000);
                mainActivity.onEditItemListener(item, position);
                getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                dismiss();
            }
        });
        return v;
    }
}
