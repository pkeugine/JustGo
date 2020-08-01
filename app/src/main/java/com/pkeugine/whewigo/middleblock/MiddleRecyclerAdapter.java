package com.pkeugine.whewigo.middleblock;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.pkeugine.whewigo.data.PoiRepo;
import com.pkeugine.whewigo.R;

import java.util.ArrayList;

public class MiddleRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<PoiRepo> mList;
    private OnClickListener mClickListener;

    public ArrayList<PoiRepo> getList() {
        return mList;
    }

    public void updateList(ArrayList<PoiRepo> list) {
        this.mList = list;
        notifyDataSetChanged();
    }


    private class CellViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView addBtn;
        private TextView placeName;
        private CardView cardView;

        //TextView tName, tStar, tPrice,tReview;
        //CardView cardview;

        CellViewHolder(@NonNull View itemView) {
            super(itemView);
            addBtn = itemView.findViewById(R.id.addBtn);
            placeName = itemView.findViewById(R.id.tname);
            cardView = itemView.findViewById(R.id.cardview);
           // delBlock = itemView.findViewById(R.id.del_block);
            addBtn.setOnClickListener(this);
            cardView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if (mClickListener != null) {
                mClickListener.onItemClick(v, getLayoutPosition());
            }
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            default: {
                View v1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_middle, parent, false);
                return new CellViewHolder(v1);
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            default: {
                CellViewHolder cellViewHolder = (CellViewHolder) holder;
                cellViewHolder.placeName.setText("" + mList.get(position).getName());
                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mList == null)
            return 0;
        return mList.size();
    }

    // onItemClick 함수를 갖는 OnClickListener를 받음
    public void setOnClickListener(final OnClickListener mClickListener) { this.mClickListener = mClickListener; }
    public interface OnClickListener { void onItemClick(View view, int position); }

}
