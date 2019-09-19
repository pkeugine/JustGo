package com.example.eoyeoga_placeblock;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Recycler_item> subList;
    private RecyclerView.RecycledViewPool viewPool;
    int item_layout;

    //기본 생성자
    public RecyclerAdapter(){
    }

    //생성자를 통해 분류 리스트를 받아옴
    public RecyclerAdapter(Context context, ArrayList<Recycler_item> subList, int item_layout) {
        this.context = context;
        this.subList = subList;
        this.item_layout = item_layout;
        viewPool = new RecyclerView.RecycledViewPool();
    }

    //뷰홀더 : 화면에 표시될 아이템 뷰를 저장하는 클래스
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView addBtn;
        TextView tName, tStar, tPrice,tReview;
        CardView cardview;
        RecyclerView recyclerView;

        public ViewHolder(View itemView){
            super(itemView);
            addBtn = (ImageView)itemView.findViewById(R.id.addBtn);
            tName = (TextView)itemView.findViewById(R.id.tname);
            tStar = (TextView)itemView.findViewById(R.id.tstar);
            tPrice = (TextView)itemView.findViewById(R.id.tprice);
            tReview = (TextView)itemView.findViewById(R.id.treview);
            cardview = (CardView)itemView.findViewById(R.id.cardview);

            StartSnapHelper snapHelper = new StartSnapHelper();
            snapHelper.attachToRecyclerView(recyclerView);
        }
    }

    @NonNull
    @Override
    //뷰홀더 객체 생성하여 리턴
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.placeblock_item,null);
        return new ViewHolder(v);
    }

    @Override
    //position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        final Recycler_item item = subList.get(position);
        holder.tName.setText(item.getTname());
        holder.tStar.setText(item.getTstar());
        holder.tPrice.setText(item.getTprice());
        holder.tReview.setText(item.getTreview());
        holder.addBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(context, item.getTname()+"을 추가하였습니다.",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.subList.size();
    }
}