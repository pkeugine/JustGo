package com.example.allotehrreviews00.RecyclerView;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.allotehrreviews00.R;

public class PostViewHolder extends RecyclerView.ViewHolder {
    public ImageView ivImg, ivLike;
    public ImageButton ivShare;
    public TextView tvLikeCount, tvUserName, tvPostText;

    public PostViewHolder(@NonNull View itemView) {
        super(itemView);
        this.ivImg = (ImageView)itemView.findViewById(R.id.iv_post_img);
        this.ivLike = (ImageView) itemView.findViewById(R.id.iv_like);
        this.ivShare = (ImageButton) itemView.findViewById(R.id.iv_share);

        this.tvLikeCount = (TextView)itemView.findViewById(R.id.tv_LikeCount);
        this.tvUserName = (TextView)itemView.findViewById(R.id.tv_userName);
        this.tvPostText = (TextView)itemView.findViewById(R.id.tv_PostText);
    }
}
