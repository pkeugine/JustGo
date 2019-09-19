package com.example.eoyeoga_placeblock;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Recycler_item> accommodationList, restaurantList, leisureList, festivalList;
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter = null;
    private SnapHelper snapHelper;
    private LinearLayoutManager linearLayoutManager;
    private Button accommodationBtn, restaurantBtn, leisureBtn, festivalBtn;
    private BtnOnClickListener onClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //초기화
        accommodationBtn = (Button) findViewById(R.id.accommodation);
        restaurantBtn = (Button) findViewById(R.id.restaurant);
        leisureBtn = (Button) findViewById(R.id.leisure);
        festivalBtn = (Button) findViewById(R.id.festival);

        recyclerView = (RecyclerView) findViewById(R.id.recycler1);
        recyclerView.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)) ;

        //snaphelper 설정 : 액티블록을 스와이핑할 때 블록하나가 중심에 위치하도록 해줌
        snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        //분류 버튼 리스너 설정
        onClickListener = new BtnOnClickListener() ;
        accommodationBtn.setOnClickListener(onClickListener);
        restaurantBtn.setOnClickListener(onClickListener);
        leisureBtn.setOnClickListener(onClickListener);
        festivalBtn.setOnClickListener(onClickListener);

        //분류 별 리스트 생성, 리스트 목록 추가
        accommodationList = new ArrayList<>();
        for(int i=1; i<=10; i++){
            accommodationList.add(new Recycler_item("호텔" + i, "별점:" + i, "가격:" + i, "리뷰수:" + i));
        }

        restaurantList = new ArrayList<>();
        for(int i=1; i<=10; i++){
            restaurantList.add(new Recycler_item("식당" + i, "별점:" + i, "가격:" + i, "리뷰수:" + i));
        }

        leisureList = new ArrayList<>();
        for(int i=1; i<=10; i++){
            leisureList.add(new Recycler_item("레져" + i, "별점:" + i, "가격:" + i, "리뷰수:" + i));
        }

        festivalList = new ArrayList<>();
        for(int i=1; i<=10; i++){
            festivalList.add(new Recycler_item("행사" + i, "별점:" + i, "가격:" + i, "리뷰수:" + i));
        }

        //디폴트로 숙박 액티블록이 표시되도록 설정
        recyclerView.setAdapter(new RecyclerAdapter(getApplicationContext(),accommodationList,R.layout.activity_main));

        /*
        //DB로 불러올 땐 이런식으로 코드 줄일 수 있을 것 같아서 일단.. 보류!
        accommodationList = createList(accommodationList);
        restaurantList = createList(restaurantList);
        leisureList = createList(leisureList);
        festivalList = createList(festivalList);
        */
    }

    /*
    private ArrayList<Recycler_item> createList(ArrayList<Recycler_item> list){
        list = new ArrayList<>();
        for(int i=1; i<=10; i++){
            list.add(new Recycler_item("이름" + i, "별점:" + i, "가격:" + i, "리뷰수:" + i));
        }
        return list;
    }
    */

    //버튼 클릭 리스너 클래스
    class BtnOnClickListener implements Button.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.accommodation:
                    recyclerView.setAdapter(new RecyclerAdapter(getApplicationContext(),accommodationList,R.layout.activity_main));
                    break;
                case R.id.restaurant:
                    recyclerView.setAdapter(new RecyclerAdapter(getApplicationContext(),restaurantList,R.layout.activity_main));
                    break;
                case R.id.leisure:
                    recyclerView.setAdapter(new RecyclerAdapter(getApplicationContext(),leisureList,R.layout.activity_main));
                    break;
                case R.id.festival:
                    recyclerView.setAdapter(new RecyclerAdapter(getApplicationContext(),festivalList,R.layout.activity_main));
                    break;
            }
        }
    }
}
