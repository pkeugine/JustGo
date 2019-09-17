package com.whewigo.justgo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String>[] subList;
    private ArrayList<ArrayList<String>> list;
    private VerticalRecyclerAdapter mAdapter = null;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager layoutManager;
    private CustomDialog customDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//      세부 리스트
//      ArrayList<Integer>[] subList = new ArrayList[4];
        subList = new ArrayList[5];
        for (int i = 1; i < subList.length; i++) {
            subList[i] = new ArrayList<>();
        }
        for (int i = 0; i < 10; i++) {
            subList[1].add("음식점" + i);
        }
        for (int i = 0; i < 10; i++) {
            subList[2].add("놀거리" + i);
        }
        for (int i = 0; i < 10; i++) {
            subList[3].add("축제" + i);
        }
        for (int i = 0; i < 10; i++) {
            subList[4].add("숙박" + i);
        }

//      큰 리스트
//      ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        list = new ArrayList<>();
        for (int i = 0; i <= 4; i++) {
            list.add(subList[i]);
        }


        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true); // 성능 개선
        layoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(layoutManager);
        // layoutManager.setStackFromEnd(true);
        mAdapter = new VerticalRecyclerAdapter(list);
        mRecyclerView.setAdapter(mAdapter);
        customDialog = new CustomDialog(this);
        customDialog.SetOnClickListener(new CustomDialog.OnClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                switch (view.getId()) {
                    case R.id.button1:
                        list.add(position + 1, subList[1]);
                        break;
                    case R.id.button2:
                        list.add(position + 1, subList[2]);
                        break;
                    case R.id.button3:
                        list.add(position + 1, subList[3]);
                        break;
                    case R.id.button4:
                        list.add(position + 1, subList[4]);
                        break;
                }
                if (position + 1 == list.size() - 1) mRecyclerView.scrollToPosition(position + 1);
                else mRecyclerView.scrollToPosition(position);
                mAdapter.notifyItemInserted(position + 1);
                customDialog.dismiss();
            }
        });

        mAdapter.SetOnClickListener(new VerticalRecyclerAdapter.OnClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (view.getId()) {
                    case R.id.del:
                        Log.d("pos", "onItemClick: " + position);
                        list.remove(position);
                        mAdapter.notifyItemRemoved(position);
                        break;
                    case R.id.add:
                        customDialog.setPosition(position);
                        customDialog.show();
                        /*
                        list.add(position + 1, subList[1]);
                        if (position + 1 == list.size() - 1) mRecyclerView.scrollToPosition(position + 1);
                        else mRecyclerView.scrollToPosition(position);
                        mAdapter.notifyItemInserted(position + 1);*/
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "button " + position, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        mAdapter.SetOnItemClickListener(new HorizontalRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
               /* switch(view.getId()) {
                    case R.id.delete :
                        list.remove(position+1);
                        mAdapter.notifyItemChanged(position+1);
                        break;
                    default:

                        break;
                }*/
                Toast.makeText(getApplicationContext(), "click " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(getApplicationContext(), "long click " + position, Toast.LENGTH_SHORT).show();
            }
        });


    }

    public static class MainActivity extends AppCompatActivity {

        private ArrayList<String>[] subList;
        private ArrayList<ArrayList<String>> list;
        private VerticalRecyclerAdapter mAdapter = null;
        private RecyclerView mRecyclerView;
        private LinearLayoutManager layoutManager;
        private CustomDialog customDialog;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
    //      세부 리스트
    //      ArrayList<Integer>[] subList = new ArrayList[4];
            subList = new ArrayList[5];
            for (int i = 1; i < subList.length; i++) {
                subList[i] = new ArrayList<>();
            }
            for (int i = 0; i < 10; i++) {
                subList[1].add("음식점" + i);
            }
            for (int i = 0; i < 10; i++) {
                subList[2].add("놀거리" + i);
            }
            for (int i = 0; i < 10; i++) {
                subList[3].add("축제" + i);
            }
            for (int i = 0; i < 10; i++) {
                subList[4].add("숙박" + i);
            }

    //      큰 리스트
    //      ArrayList<ArrayList<Integer>> list = new ArrayList<>();
            list = new ArrayList<>();
            for (int i = 0; i <= 4; i++) {
                list.add(subList[i]);
            }


            mRecyclerView = findViewById(R.id.recycler_view);
            mRecyclerView.setHasFixedSize(true); // 성능 개선
            layoutManager = new LinearLayoutManager(this);

            mRecyclerView.setLayoutManager(layoutManager);
            // layoutManager.setStackFromEnd(true);
            mAdapter = new VerticalRecyclerAdapter(list);
            mRecyclerView.setAdapter(mAdapter);
            customDialog = new CustomDialog(this);
            customDialog.SetOnClickListener(new CustomDialog.OnClickListener() {
                @Override
                public void onItemClick(View view, int position) {

                    switch (view.getId()) {
                        case R.id.button1:
                            list.add(position + 1, subList[1]);
                            break;
                        case R.id.button2:
                            list.add(position + 1, subList[2]);
                            break;
                        case R.id.button3:
                            list.add(position + 1, subList[3]);
                            break;
                        case R.id.button4:
                            list.add(position + 1, subList[4]);
                            break;
                    }
                    if (position + 1 == list.size() - 1) mRecyclerView.scrollToPosition(position + 1);
                    else mRecyclerView.scrollToPosition(position);
                    mAdapter.notifyItemInserted(position + 1);
                    customDialog.dismiss();
                }
            });

            mAdapter.SetOnClickListener(new VerticalRecyclerAdapter.OnClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    switch (view.getId()) {
                        case R.id.del:
                            Log.d("pos", "onItemClick: " + position);
                            list.remove(position);
                            mAdapter.notifyItemRemoved(position);
                            break;
                        case R.id.add:
                            customDialog.setPosition(position);
                            customDialog.show();
                            /*
                            list.add(position + 1, subList[1]);
                            if (position + 1 == list.size() - 1) mRecyclerView.scrollToPosition(position + 1);
                            else mRecyclerView.scrollToPosition(position);
                            mAdapter.notifyItemInserted(position + 1);*/
                            break;
                        default:
                            Toast.makeText(getApplicationContext(), "button " + position, Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });
            mAdapter.SetOnItemClickListener(new HorizontalRecyclerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                   /* switch(view.getId()) {
                        case R.id.delete :
                            list.remove(position+1);
                            mAdapter.notifyItemChanged(position+1);
                            break;
                        default:

                            break;
                    }*/
                    Toast.makeText(getApplicationContext(), "click " + position, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onItemLongClick(View view, int position) {
                    Toast.makeText(getApplicationContext(), "long click " + position, Toast.LENGTH_SHORT).show();
                }
            });


        }
    }
}

