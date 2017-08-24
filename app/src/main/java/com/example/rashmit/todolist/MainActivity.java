package com.example.rashmit.todolist;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity{

    ArrayList<String> toDoItems = new ArrayList<String>();
    ToDoItemsAdapter adapter;

    public RecyclerView mRecyclerView;
    public LinearLayoutManager mLinearLayoutManager;

    public File toDoItemsFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "ToDoItems.txt");
    public FileReader fileReader;
    public FileWriter fileWriter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ReadFromFile();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLinearLayoutManager = new LinearLayoutManager(getApplicationContext());
        adapter = new ToDoItemsAdapter(getApplicationContext(), this);

        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(adapter);

        final EditText addItems = (EditText) findViewById(R.id.addItems);
        addItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
            }
        });

        final Button submitbutton = (Button) findViewById(R.id.submit);
        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = addItems.getText().toString();
                addItems.setText("");
                if (item.length() > 0) {
                    toDoItems.add(toDoItems.size(), item);
                    //adapter.notifyDataSetChanged();
                    adapter.notifyItemInserted(toDoItems.size());
                }
                System.out.println(toDoItems);
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            }
        });


        /*
        adapter = new ArrayAdapter<String>(this, R.layout.layout_todoitems, toDoItems);
        listView.setAdapter(adapter);

        final EditText addItems = (EditText) findViewById(R.id.additems);
        addItems.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
            }
        });

        final Button submitbutton = (Button) findViewById(R.id.submitbutton);
        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = addItems.getText().toString();
                addItems.setText("");
                if(item.length()>0){
                    toDoItems.add(toDoItems.size(), item);
                    adapter.notifyDataSetChanged();
                }
                System.out.println(toDoItems);
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            }
        });

        final TextView item = (TextView) findViewById(R.id.item);
        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        */
    }

    @Override
    public void onPause(){
        WriteToFile();
        super.onPause();
    }

    public void onEditItemListener(String item, int position){
        Log.d("onEditItemListener", item + " " + position);
        toDoItems.remove(position);
        toDoItems.add(position, item);
        adapter.notifyItemChanged(position);
    }

    public void ReadFromFile(){
        try{
            System.out.println("Attempting to read from file in: " + toDoItemsFile.getAbsolutePath());
            if(toDoItemsFile.exists()){
                Scanner read = new Scanner(toDoItemsFile);
                while(read.hasNext()){
                    toDoItems.add(toDoItems.size(), read.next());
                    System.out.println("Success in reading 1 ");
                }
                System.out.println("Success in reading "+toDoItems);
            }
        }catch(IOException ie){
            ie.printStackTrace();
        }
    }

    public void WriteToFile(){
        //System.out.println("Writing to a file "+toDoItems);
        try{
            String userHomeFolder = System.getProperty("user.home");
            //File textFile = new File(toDoItemsFile);
            BufferedWriter out = new BufferedWriter(new FileWriter(toDoItemsFile));

            fileWriter = new FileWriter(toDoItemsFile);
            System.out.println("Attempting to write to file in: " + toDoItemsFile.getAbsolutePath());
            Iterator it = toDoItems.iterator();
            System.out.println("Success in writing " + toDoItems);
            while(it.hasNext()){
                out.write(it.next() + "\n");
                System.out.println("Success in writing ");
            }
            ReadFromFile();
        }catch(IOException e){
            System.out.println("Error "+toDoItems);
            e.printStackTrace();
        }
    }
}
