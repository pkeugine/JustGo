package com.pkeugine.whewigo.detail;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.pkeugine.whewigo.R;
import com.pkeugine.whewigo.common.BaseActivity;
import com.pkeugine.whewigo.common.GpsTracker;
import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapInfo;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapPolyLine;
import com.skt.Tmap.TMapView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PathSearchActivity extends BaseActivity {

    @BindView(R.id.tmap_path)
    LinearLayout linearLayoutTmap;


    private String TmapapiKey;
    private TMapView tMapView;
    private TMapData tmapData;
    private TMapPoint tMapPointStart;
    private TMapPoint tMapPointEnd;
    private GpsTracker gpsTracker;
    private double endLat;
    private double endLon;
    private TMapData.TMapPathType pathType;
    private TMapMarkerItem placeMarker;
    private boolean stateFlag;

    @OnClick({R.id.car_search,R.id.walk_search})
    void buttonEvents(View v) {
        switch (v.getId()) {
            case R.id.car_search:
                if(stateFlag) return;
                pathType = TMapData.TMapPathType.CAR_PATH;
                stateFlag = true;
                break;
            case R.id.walk_search:
                if(!stateFlag) return;
                pathType = TMapData.TMapPathType.PEDESTRIAN_PATH;
                stateFlag = false;
                break;
            default:
                break;
        }
        findPath(pathType);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_search);
        TmapapiKey = getMetadata(this, "com.pkeugine.jhw.TmapKey");

        Intent intent = this.getIntent();
        if (intent != null) {
            if (intent.getDoubleExtra("endLon", 0) != 0 && intent.getDoubleExtra("endLat", 0) != 0) {
                endLon = intent.getDoubleExtra("endLon", 0);
                endLat = intent.getDoubleExtra("endLat", 0);
            } else {
                Log.e("getData error", "======================================");
                onBackPressed();
            }
        }
        ButterKnife.bind(this);

        tMapView = new TMapView(this);
        tMapView.setSKTMapApiKey(TmapapiKey);
        tMapView.setCenterPoint(endLon, endLat);
        linearLayoutTmap.addView(tMapView);

        gpsTracker = new GpsTracker(PathSearchActivity.this);
        tmapData = new TMapData();
        tMapPointStart = new TMapPoint(gpsTracker.getLatitude(), gpsTracker.getLongitude());
        tMapPointEnd = new TMapPoint(endLat, endLon); // N서울타워(목적지)\
        pathType = TMapData.TMapPathType.PEDESTRIAN_PATH;
        findPath(pathType);

        placeMarker = new TMapMarkerItem();
        placeMarker.setTMapPoint(tMapPointEnd);
        placeMarker.setCanShowCallout(true);
        placeMarker.setPosition((float) 0.5, 1);
        placeMarker.setAutoCalloutVisible(true);
        placeMarker.setIcon(BitmapFactory.decodeResource(getResources(), R.drawable.empty));

        ArrayList<TMapPoint> arrays = new ArrayList<>();
        arrays.add(tMapPointEnd);
        arrays.add(tMapPointStart);

        TMapInfo info = tMapView.getDisplayTMapInfo(arrays);
        Log.d("result", "info : " + info.getTMapZoomLevel() + "," + tMapView.getZoomLevel());
        tMapView.setCenterPoint(info.getTMapPoint().getLongitude(), info.getTMapPoint().getLatitude());
        tMapView.zoomToSpan(Math.abs(gpsTracker.getLatitude()-endLat), Math.abs(gpsTracker.getLongitude()-endLon));
    }


    // 경로 탐색
    private void findPath(TMapData.TMapPathType pathType) {
        Log.d("result", "info : !!" + tMapView.getZoomLevel());
        tmapData.findPathDataWithType(pathType, tMapPointStart, tMapPointEnd, new TMapData.FindPathDataListenerCallback() {
            @Override
            public void onFindPathData(TMapPolyLine polyLine) {
                polyLine.setLineColor(Color.BLUE);
                polyLine.setLineWidth(6);
                tMapView.addTMapPath(polyLine);
            }
        });

        tmapData.findPathDataAllType(pathType, tMapPointStart, tMapPointEnd, new TMapData.FindPathDataAllListenerCallback() {
            @Override
            public void onFindPathDataAll(Document document) {
                Element root = document.getDocumentElement();
                NodeList totaltime = root.getElementsByTagName("tmap:totalTime");
                int time = Integer.parseInt(totaltime.item(0).getTextContent());

                StringBuilder sb = new StringBuilder();
                if(time/3600 > 0) sb.append(String.format("%d", (time / 3600))+"시간 ");
                if((time / 60) % 60 > 0) sb.append(String.format("%d", (time / 60) % 60)+"분 ");
                sb.append(String.format("%d", time % 60)+"초");

                if(pathType== TMapData.TMapPathType.CAR_PATH) {
                    placeMarker.setCalloutTitle("예상 소요시간 및 금액");
                    NodeList taxicost = root.getElementsByTagName("tmap:taxiFare");
                    sb.append(" / "+taxicost.item(0).getTextContent().trim() + "원");
                } else {
                    placeMarker.setCalloutTitle("예상 소요시간");
                }
                placeMarker.setCalloutSubTitle(sb.toString());
                tMapView.removeAllMarkerItem();
                tMapView.addMarkerItem("placepos", placeMarker);
            }
        });
    }

}
