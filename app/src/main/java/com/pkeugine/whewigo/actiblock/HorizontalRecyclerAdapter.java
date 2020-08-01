package com.pkeugine.whewigo.actiblock;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pkeugine.whewigo.data.PoiRepo;
import com.pkeugine.whewigo.R;

import java.util.ArrayList;

public class HorizontalRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<PoiRepo> mList;
    private onHorizonItemClickListener mHitemClickListener;

    public void updateList(ArrayList<PoiRepo> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    private static class HorizonViewHolder extends RecyclerView.ViewHolder {

        private TextView placeName;

        HorizonViewHolder(@NonNull View itemView,final onHorizonItemClickListener mHitemClickListener) {
            super(itemView);
            placeName = itemView.findViewById(R.id.place_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mHitemClickListener != null) {
                        mHitemClickListener.onHorizonItemClick(view, getLayoutPosition());
                    }
                }
            });
        }

        void setPlaceName(String placeNameText) { placeName.setText(placeNameText); }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            default: {
                View horizonView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_horizontal, parent, false);
                return new HorizonViewHolder(horizonView, mHitemClickListener);
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            default: {
                HorizonViewHolder horizonViewHolder = (HorizonViewHolder) holder;
                horizonViewHolder.setPlaceName(mList.get(position).getName());
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

    public interface onHorizonItemClickListener { void onHorizonItemClick(View view, int hPosition);}

    public void setOnHorizonItemClickListener(final onHorizonItemClickListener itemClickListener) { this.mHitemClickListener = itemClickListener; }
}
