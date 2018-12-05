package com.example.studia.projekt;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.studia.projekt.db.model.FieldRecords;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    //private ArrayList<FieldRecords> values;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView1;
        public TextView mTextView2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.textView);
            mTextView2 = itemView.findViewById(R.id.textView2);
        }
    }



    private List<FieldRecords> mValues;
    public RecyclerViewAdapter(List<FieldRecords> values)
    {
        mValues = values;
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater =LayoutInflater.from(context);

        View v= inflater.inflate(R.layout.section_item,viewGroup,false);
        //View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.section_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        FieldRecords currentItem = mValues.get(i);

        //viewHolder.mTextView1.setText(currentItem.getArea());
        viewHolder.mTextView1.setText(currentItem.getName());
        viewHolder.mTextView2.setText(currentItem.getName());

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }
}
