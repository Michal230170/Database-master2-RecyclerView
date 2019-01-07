package com.example.studia.projekt.ui.section;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.studia.projekt.R;
import com.example.studia.projekt.db.model.Section;

import java.util.ArrayList;
import java.util.List;

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.ViewHolder> {

    private OnItemClickListener mListener;
    private List<Section> sections = new ArrayList<>();

    public interface OnItemClickListener {
        void onItemClick(int position);

        void OnItemLongClick(int adapterPosition);
    }



    public SectionAdapter() {
    }

    public void setOnClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.section_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Section currentItem = sections.get(i);

        //viewHolder.mTextView1.setText(currentItem.getArea());
        viewHolder.mTextView1.setText("Działka nr: " + currentItem.getNumber());
        viewHolder.mTextView2.setText("Powierzchnia: " + currentItem.getArea() + "ha");
        viewHolder.mTextView3.setText("\"" + currentItem.getName() + "\"");
    }

    @Override
    public int getItemCount() {
        return sections.size();
    }


    public Section getSectionAt(int position){
        return sections.get(position);
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

            v.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mListener.OnItemLongClick(getAdapterPosition());
                    return false;
                }
            });

        }
    }

    public void swapData(List<Section> newSections) {
        sections.addAll(newSections);
        notifyDataSetChanged();
    }



    public Section getSection(int position) {
        return sections.get(position);
    }
}
