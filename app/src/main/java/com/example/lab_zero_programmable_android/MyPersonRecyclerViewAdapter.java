package com.example.lab_zero_programmable_android;

import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lab_zero_programmable_android.task.PersonContent;
import com.example.lab_zero_programmable_android.task.PersonContent.PersonItem;

import java.util.List;


public class MyPersonRecyclerViewAdapter extends RecyclerView.Adapter<MyPersonRecyclerViewAdapter.ViewHolder> {

    private final List<PersonItem> mValues;

    public MyPersonRecyclerViewAdapter(List<PersonItem> items) {
        mValues = items;
    }

    public interface EventListener {
        void onItemClick(int contact_id);
        void onDeleteClick(String contact_id);
    }
    private EventListener mListener;
    public void SetInputListeners(EventListener listener){
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_person_, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        String clickedID = holder.mItem.id;
        holder.mIdView.setText(mValues.get(position).id);
        holder.mTitleView.setText(mValues.get(position).personName);

        holder.mDeleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Log.d("Check", "delete button clicked");
                if(mListener != null)
                    mListener.onDeleteClick(clickedID);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mTitleView;
        public final ImageView mImageView;
        public final ImageButton mDeleteButton;
        public PersonItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mTitleView = (TextView) view.findViewById(R.id.item_title);
            mImageView = view.findViewById(R.id.item_imageView);
            mDeleteButton = (ImageButton) view.findViewById(R.id.deleteButton);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTitleView.getText() + "'";
        }
    }
}