package com.example.rashmit.todolist;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ToDoItemsAdapter extends RecyclerView.Adapter<ToDoItemsAdapter.ViewHolder>{

    private Context mContext;
    private static MainActivity mainActivity;

    public ToDoItemsAdapter(Context mContext, MainActivity mainActivity){
        this.mContext = mContext;
        this.mainActivity = mainActivity;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView todoitems;
        public ViewHolder(View V){
            super(V);
            todoitems = (TextView) V.findViewById(R.id.todoitems);
            todoitems.setTextColor(Color.BLUE);
            todoitems.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //System.out.println(getLayoutPosition());
                    EditActivity editActivity = EditActivity.getInstance(mainActivity, getLayoutPosition());
                    editActivity.show(mainActivity.getFragmentManager(), "dialog");
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View V = LayoutInflater.from(mContext).inflate(R.layout.activity_recycler_view, parent, false);
        ViewHolder vh = new ViewHolder(V);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ViewHolder holder1 = holder;
        holder1.todoitems.setText(mainActivity.toDoItems.get(position));
    }

    public int getItemCount(){
        return mainActivity.toDoItems.size();
    }
}