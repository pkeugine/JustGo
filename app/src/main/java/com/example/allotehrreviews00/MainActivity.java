package com.example.allotehrreviews00;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.allotehrreviews00.RecyclerView.PostAdapter;
import com.example.allotehrreviews00.model.PostItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public RecyclerView.LayoutManager layoutManager;
    private RecyclerView rvList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<PostItem> listItem = new ArrayList<>();
        rvList = (RecyclerView)findViewById(R.id.rv_list);


        for ( int i = 0;  i < 5; i++) {
            PostItem item = new PostItem(true,"125","","김동현", "좋습니다");
            listItem.add(i,item);
        }

        PostAdapter adapter = new PostAdapter(this,listItem);
        rvList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        rvList.setAdapter(adapter);
    }
}
