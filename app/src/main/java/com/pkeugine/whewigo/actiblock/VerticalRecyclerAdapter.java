package com.pkeugine.whewigo.actiblock;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pkeugine.whewigo.data.HorizonRepo;
import com.pkeugine.whewigo.data.PoiRepo;
import com.pkeugine.whewigo.R;
import com.pkeugine.whewigo.common.StartSnapHelper;

import java.util.ArrayList;

public class VerticalRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<HorizonRepo> mList;
    private OnVerticalClickListener mVclickListener;
    private OnHorizontalClickListener mHclickListener;

    public VerticalRecyclerAdapter(ArrayList<HorizonRepo> list) { this.mList = list; }

    private static class VerticalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView blockCate;
        private ImageView addBlock;
        private Button delBlock;
        private RecyclerView mRecyclerView;
        private LinearLayoutManager layoutManager;
        private HorizontalRecyclerAdapter adapter;
        private StartSnapHelper snapHelper;
        private VerticalRecyclerAdapter.OnVerticalClickListener mVclickListener;
        private VerticalRecyclerAdapter.OnHorizontalClickListener mHclickListener;


        VerticalViewHolder(@NonNull View itemView, Context mContext) {
            super(itemView);
            blockCate = itemView.findViewById(R.id.block_cate);
            addBlock = itemView.findViewById(R.id.add_block);
            delBlock = itemView.findViewById(R.id.del_block);
            if(getAdapterPosition()==0) delBlock.setVisibility(View.GONE);
            mRecyclerView = itemView.findViewById(R.id.horizontal_recycler_view);


            layoutManager = new LinearLayoutManager(mContext);
            adapter = new HorizontalRecyclerAdapter();
            snapHelper = new StartSnapHelper();

            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.setNestedScrollingEnabled(false);
            mRecyclerView.setAdapter(adapter);
            snapHelper.attachToRecyclerView(mRecyclerView);

            addBlock.setOnClickListener(this);
            delBlock.setOnClickListener(this);
            adapter.setOnHorizonItemClickListener(new HorizontalRecyclerAdapter.onHorizonItemClickListener() {
                @Override
                public void onHorizonItemClick(View view, int hPosition) {
                    Log.d("second", "Second1: "+hPosition+", "+getAdapterPosition());
                    mHclickListener.onHitemClick(view,hPosition,getAdapterPosition());
                }
            });

        }

        @Override
        public void onClick(View view) {
            if (mVclickListener != null) {
                mVclickListener.onVitemClick(view, getLayoutPosition()); // onItemClick 실행 ( 메인액티비티에 정의 )
            }
        }

        void setData(ArrayList<PoiRepo> list) { adapter.updateList(list); }
        void setPosition(int position) { mRecyclerView.scrollToPosition(position);}
        void setCate(String cate) { blockCate.setText(cate);}

        void setOnItemClickListener(VerticalRecyclerAdapter.OnVerticalClickListener mVclickListener, VerticalRecyclerAdapter.OnHorizontalClickListener mHclickListener) {
            this.mVclickListener = mVclickListener;
            this.mHclickListener = mHclickListener;
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            default: {
                View verticalnView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vertical, parent, false);
                VerticalViewHolder holder = new VerticalViewHolder(verticalnView, parent.getContext());
                holder.setOnItemClickListener(mVclickListener,mHclickListener);
                return holder;
            }
        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) { // + - 2 변경 일어날시 호출
        Log.d("onB", "onBindViewHolder: "+position);
        switch (holder.getItemViewType()) {
            default: {
                VerticalViewHolder verticalViewHolder = (VerticalViewHolder) holder;
                if(position==0) {
                    verticalViewHolder.delBlock.setVisibility(View.GONE);
                    verticalViewHolder.blockCate.setVisibility(View.GONE);
                    verticalViewHolder.setData(null);
                    break;
                }
                if(verticalViewHolder.delBlock.getVisibility()==View.GONE) {
                    if(position!=0) {
                        verticalViewHolder.delBlock.setVisibility(View.VISIBLE);
                        verticalViewHolder.blockCate.setVisibility(View.VISIBLE);
                    }
                }

                verticalViewHolder.setData(mList.get(position).getPoiList()); // 수평 데이터를 set
                verticalViewHolder.setPosition(mList.get(position).gethIndex());
                verticalViewHolder.setCate(mList.get(position).getSecondCategory());
                // 확정 버튼 필요?
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


    public void setOnVclickListener(final OnVerticalClickListener mVclickListener) { this.mVclickListener = mVclickListener; }
    public interface OnVerticalClickListener { void onVitemClick(View view, int vposition); }

    public void setOnHclickListener(final OnHorizontalClickListener mHclickListener) { this.mHclickListener = mHclickListener; }
    public interface OnHorizontalClickListener { void onHitemClick(View view, int hposition, int vposition); }

}
