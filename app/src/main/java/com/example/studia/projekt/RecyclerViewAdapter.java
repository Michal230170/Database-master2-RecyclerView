package com.example.studia.projekt;

import android.arch.lifecycle.LiveData;
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

    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick (int i);
    }

    public void setOnClickListener(OnItemClickListener listener){
        mListener = listener;
    }




    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;


        public ViewHolder(@NonNull View v, final OnItemClickListener listener) {
            super(v);

            mTextView1 = v.findViewById(R.id.number);
            mTextView2 = v.findViewById(R.id.area);
            mTextView3 = v.findViewById(R.id.name);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int i = getAdapterPosition();
                        if (i != RecyclerView.NO_POSITION){
                            listener.onItemClick(i);
                        }
                    }

                }
            });

        }
    }



    private List<FieldRecords> values;

    public RecyclerViewAdapter(List<FieldRecords> values)
    {
        this.values = values;
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater =LayoutInflater.from(context);

        View v= inflater.inflate(R.layout.section_item,viewGroup,false);
        //View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.section_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v, mListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        FieldRecords currentItem = values.get(i);

        //viewHolder.mTextView1.setText(currentItem.getArea());
        viewHolder.mTextView1.setText("Działka nr: " + currentItem.getNumber());
        viewHolder.mTextView2.setText("Powierzchnia: " + currentItem.getArea() +"ha");
        viewHolder.mTextView3.setText("\""+currentItem.getName()+"\"");

    }

    @Override
    public int getItemCount() {
        return values.size();
    }

}
