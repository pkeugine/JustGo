package com.example.allotehrreviews00.RecyclerView;

/*
어답터는 리사이클러뷰로 넘기기 전에 정렬해서 보내주는 개념으로 생각하면 좋아용
자세한 내용은 이미지적으로 받아들이는 것은 아니여서 설명은 불가하지만, 붙여서 넣을 때 문제는 없을거고! 문제 생기면 같이 조정해줄겡
 */


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.allotehrreviews00.R;
import com.example.allotehrreviews00.model.PostItem;
import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> {
    private Context mContext;
    private ArrayList<PostItem> postItems;
    public PostAdapter(Context context, ArrayList<PostItem> listItem) {
        mContext = context;
        postItems = listItem;
    }
    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View baseView = View.inflate(mContext,R.layout.item_post,null);
        PostViewHolder postViewHolder = new PostViewHolder(baseView);
        return postViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        PostItem item = postItems.get(position);

        holder.tvUserName.setText(item.getUserName());
        holder.tvPostText.setText(item.getPostText());
        holder.tvLikeCount.setText(item.getPostLikesCount());
    }

    @Override
    public int getItemCount() {
        return postItems.size();
    }
}
