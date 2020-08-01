package com.pkeugine.whewigo.middleblock;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pkeugine.whewigo.data.PoiRepo;
import com.pkeugine.whewigo.detail.DetailActivity;
import com.pkeugine.whewigo.common.BaseActivity;
import com.pkeugine.whewigo.data.HorizonRepo;
import com.pkeugine.whewigo.R;
import com.pkeugine.whewigo.common.StartSnapHelper;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MiddleblockActivity extends BaseActivity {

    @BindView(R.id.linearLayoutTmap2)
    LinearLayout linearLayoutTmap;
    @BindView(R.id.middle_recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.cate_name)
    TextView cateName;

    private static final int DETAILVIEW_REQUEST_CODE = 4000;


    private LinearLayoutManager layoutManager;
    private MiddleRecyclerAdapter mAdapter;
    private HashMap<String, HorizonRepo>[] horizontalList;
    private StartSnapHelper snapHelper;
    private HorizonRepo mHr;
    private TMapView tMapView;
    private String TmapapiKey;
    private int mvPosition;
    private int mFirstCate;
    private boolean sw;
    private TMapMarkerItem focusMarker;
    private View currentSpinnerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_middleblock);
        ButterKnife.bind(this);
        findViewById(R.id.title_back).setOnClickListener(v -> finish());
        Intent intent = this.getIntent();
        if (intent != null) {
            // 공통
            if (intent.getSerializableExtra("list") != null) {
                horizontalList = (HashMap<String, HorizonRepo>[]) intent.getSerializableExtra("list");
            } else {
                Log.e("getData error", "======================================");
                onBackPressed();
            }
            // 추가 버튼
            if (intent.getIntExtra("addposition", -1) != -1) {
                mvPosition = intent.getIntExtra("addposition", 0) + 1;
            }
            // 카드
            if (intent.getSerializableExtra("horizonrepo") != null) {
                mHr = (HorizonRepo) intent.getSerializableExtra("horizonrepo");
            }
        }
        TmapapiKey = getMetadata(this, "com.pkeugine.jhw.TmapKey");
        tMapView = new TMapView(this);
        tMapView.setSKTMapApiKey(TmapapiKey);
        linearLayoutTmap.addView(tMapView);

        sw = false;
        CustomSpinner[] spinners = new CustomSpinner[4];
        ArrayList[] sItemLists = new ArrayList[4];
        ArrayAdapter[] sAdapterList = new ArrayAdapter[4];
        spinners[0] = findViewById(R.id.accommodation);
        spinners[1] = findViewById(R.id.restaurant);
        spinners[2] = findViewById(R.id.leisure);
        spinners[3] = findViewById(R.id.festival);

        for (int i = 0; i < 4; i++) {

            sItemLists[i] = new ArrayList<>(horizontalList[i].keySet());
            for (int j = 0; j < sItemLists[i].size(); j++) {
                Log.d("TAG", "onCreate: " + sItemLists[i].get(j).toString());
            }
            sAdapterList[i] = new ArrayAdapter<>(getApplicationContext(), R.layout.view_spinner, sItemLists[i]);
            sAdapterList[i].setDropDownViewResource(R.layout.view_spinner_item);
            spinners[i].setAdapter(sAdapterList[i]);
            int finalI = i;

            spinners[i].setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    sw = true;
                    return false;
                }
            });

            spinners[i].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int index, long l) {
                    if (sw) {
                        String secondCate = (String) adapterView.getItemAtPosition(index);
                        cateName.setText(secondCate);
                        mFirstCate = finalI;
                        mAdapter.updateList(horizontalList[mFirstCate].get(secondCate).getPoiList());
                        if (currentSpinnerView != null)
                            currentSpinnerView.setBackground(getDrawable(R.drawable.button_selector));
                        currentSpinnerView = view;
                        currentSpinnerView.setBackground(getDrawable(R.drawable.button_selector_white));//setBackgroundColor(Color.WHITE);
                        getTmapMarker(mFirstCate, secondCate, layoutManager.findLastCompletelyVisibleItemPosition());
                        sw = false;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
        }


        snapHelper = new StartSnapHelper();
        mAdapter = new MiddleRecyclerAdapter();
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        Log.d("VVV", "onCreate: "+layoutManager.findFirstCompletelyVisibleItemPosition());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                // 0 : 초기, 1 : 눌렀을 때, 2 : 뗏을 때
                if (mRecyclerView.getScrollState() != 2) {
                    int firstPos = layoutManager.findLastCompletelyVisibleItemPosition();
                } else {
                    if (layoutManager.findLastCompletelyVisibleItemPosition() != -1) {
                        int index = layoutManager.findLastCompletelyVisibleItemPosition();
                        if (focusMarker.getAutoCalloutVisible())
                            focusMarker.setAutoCalloutVisible(false);
                        focusMarker = tMapView.getMarkerItemFromID("placepos" + index);
                        focusMarker.setAutoCalloutVisible(true);
                        tMapView.bringMarkerToFront(focusMarker);
                        tMapView.setCenterPoint(focusMarker.getTMapPoint().getLongitude(), focusMarker.getTMapPoint().getLatitude());

                    }
                }
            }
        });
        snapHelper.attachToRecyclerView(mRecyclerView);

        spinners[0].setSelection(sItemLists[0].indexOf("숙박"));
        spinners[1].setSelection(sItemLists[1].indexOf("TV맛집"));
        spinners[2].setSelection(sItemLists[2].indexOf("놀거리"));
        spinners[3].setSelection(sItemLists[3].indexOf("행사"));
        if (mHr != null) {
            mFirstCate = mHr.getFirstCategory();
            mvPosition = mHr.getvIndex();
            layoutManager.scrollToPosition(mHr.gethIndex());
            mAdapter.updateList(mHr.getPoiList());
            cateName.setText(mHr.getSecondCategory());
            spinners[mFirstCate].setSelection(sItemLists[mFirstCate].indexOf(mHr.getSecondCategory()));
            getTmapMarker(mFirstCate, mHr.getSecondCategory(), mHr.gethIndex());
        } else {
            mFirstCate = 0;
            mAdapter.updateList(horizontalList[mFirstCate].get("숙박").getPoiList());
            cateName.setText("숙박");
            getTmapMarker(0, "숙박", 0);
        }


        mAdapter.setOnClickListener(new MiddleRecyclerAdapter.OnClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (view.getId()) {
                    case R.id.addBtn:
                        HorizonRepo hr = new HorizonRepo(mFirstCate, cateName.getText().toString(), mAdapter.getList(), mvPosition, position);
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("returnHR", hr);
                        setResult(RESULT_OK, resultIntent);
                        finish();
                        break;
                    case R.id.cardview:
                        Intent intent = new Intent(MiddleblockActivity.this, DetailActivity.class);
                        intent.putExtra("position", position);
                        intent.putExtra("keytype", mFirstCate);
                        intent.putExtra("poiId", mAdapter.getList().get(position).getPoiId());
                        intent.putExtra("addressname", mAdapter.getList().get(position).getAddress());
                        startActivityForResult(intent, DETAILVIEW_REQUEST_CODE);
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "default " + position, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });


    }

    private void getTmapMarker(int firstCate, String secondCate, int index) {

        tMapView.removeAllMarkerItem();
        ArrayList<PoiRepo> poiList = horizontalList[firstCate].get(secondCate).getPoiList();
        for (int i = 0; i < poiList.size(); i++) {
            double placeLat = poiList.get(i).getLat();
            double placeLon = poiList.get(i).getLon();
            TMapMarkerItem placeMarker = new TMapMarkerItem();
            placeMarker.setTMapPoint(new TMapPoint(placeLat, placeLon));
            placeMarker.setCanShowCallout(true);
            placeMarker.setCalloutTitle(poiList.get(i).getName());
            if (i == index) {
                focusMarker = placeMarker;
            }
            tMapView.addMarkerItem("placepos" + i, placeMarker);
        }

        focusMarker.setAutoCalloutVisible(true);
        focusMarker.getTMapPoint().getLatitude();
        tMapView.bringMarkerToFront(focusMarker);
        tMapView.setCenterPoint(focusMarker.getTMapPoint().getLongitude(), focusMarker.getTMapPoint().getLatitude());

//
//        if(focusMarker.getAutoCalloutVisible()) focusMarker.setAutoCalloutVisible(false);
//        //tMapView.all
//        focusMarker = tMapView.getMarkerItemFromID("placepos"+index);
//
//        focusMarker.setAutoCalloutVisible(true);
//        focusMarker.getTMapPoint().getLatitude();
//        tMapView.setCenterPoint(focusMarker.getTMapPoint().getLongitude(),focusMarker.getTMapPoint().getLatitude());

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case DETAILVIEW_REQUEST_CODE:
                    int position = -1;
                    if (data.getIntExtra("returnPos", 0) != 0)
                        position = data.getIntExtra("returnPos", 0);
                    HorizonRepo hr = new HorizonRepo(mFirstCate, cateName.getText().toString(), mAdapter.getList(), mvPosition, position);
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("returnHR", hr);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                    break;
            }
        }

    }

}
