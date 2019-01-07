package com.example.studia.projekt.ui.field;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.studia.projekt.R;
import com.example.studia.projekt.db.model.FieldRecord;
import com.example.studia.projekt.db.model.Section;
import com.example.studia.projekt.ui.section.SectionAdapter;

import java.util.ArrayList;
import java.util.List;

public class FieldAdapter extends RecyclerView.Adapter<FieldAdapter.ViewHolder> {

    private OnItemClickListener mListener;
   // private FieldAdapter.OnItemClickListener mListener;
    private List<FieldRecord> fields = new ArrayList<>();

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public FieldAdapter() {
    }

    public void setOnClickListener(FieldAdapter.OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.content_record, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        FieldRecord currentItem = fields.get(i);

        //viewHolder.mTextView1.setText(currentItem.getArea());
        viewHolder.mTextView1.setText("Data: " + currentItem.getDate());
        viewHolder.mTextView2.setText("Roślina: " + currentItem.getPlant());
        viewHolder.mTextView3.setText("\"" + currentItem.getUsageReason() + "\"");
    }

    @Override
    public int getItemCount() {
        return fields.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView1;
        private TextView mTextView2;
        private TextView mTextView3;

        public ViewHolder(@NonNull View v) {
            super(v);

            mTextView1 = v.findViewById(R.id.number);
            mTextView2 = v.findViewById(R.id.area);
            mTextView3 = v.findViewById(R.id.name);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(getAdapterPosition());
                }
            });
        }
    }

    public void swapData(List<FieldRecord> newFields) {
        fields.addAll(newFields);
        notifyDataSetChanged();
    }

    public FieldRecord getField (int position) {
        return fields.get(position);
    }
}

