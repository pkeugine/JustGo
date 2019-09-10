package com.whewigo.justgo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.graphics.Color;
import android.net.Network;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.skt.Tmap.TMapData;

import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapInfo;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapPolyLine;
import com.skt.Tmap.TMapView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

import static com.skt.Tmap.TMapData.*;

public class tmapDemo extends AppCompatActivity {

    TMapView tMapView;
    TMapPoint tMapPointStart;
    TMapPoint tMapPointEnd;
    TMapData tmapData;
    TextView timeText;
    TextView costText;
    Button carButton;
    Button walkButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tmap_demo);

        timeText = findViewById(R.id.astime);
        costText = findViewById(R.id.ascost);
        carButton = findViewById(R.id.carpath);
        carButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                costText.setVisibility(View.VISIBLE);
                findcarPath();
            }
        });
        walkButton = findViewById(R.id.walkpath);
        walkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                costText.setVisibility(View.INVISIBLE);
                findwalkPath();
            }
        });

        LinearLayout linearLayoutTmap = (LinearLayout) findViewById(R.id.linearLayoutTmap);
        tMapView = new TMapView(this);

        tMapView.setSKTMapApiKey("0d7d31d9-eb6b-4540-a1cc-864ca37b8bf8");
        linearLayoutTmap.addView(tMapView);

        double startLat = 37.570841;
        double endLat = 37.551135;
        double startLon = 126.985302;
        double endLon = 126.988205;


        tMapPointStart = new TMapPoint(startLat, startLon); // SKT타워(출발지)
        tMapPointEnd = new TMapPoint(endLat, endLon); // N서울타워(목적지)\

        ArrayList<TMapPoint> list = new ArrayList<>();
        list.add(tMapPointStart);
        list.add(tMapPointEnd);
        TMapInfo info = tMapView.getDisplayTMapInfo(list);
        tMapView.setCenterPoint(info.getTMapPoint().getLongitude(),info.getTMapPoint().getLatitude());
        //tMapView.setZoomLevel(info.getTMapZoomLevel());



        tMapView.zoomToSpan(Math.abs(endLat-startLat),Math.abs(endLon-startLon));
        tmapData = new TMapData();

        findcarPath();

/*
        try {
            TMapPolyLine tMapPolyLine = new TMapData().findPathData(tMapPointStart, tMapPointEnd);
            tMapPolyLine.setLineColor(Color.BLUE);
            tMapPolyLine.setLineWidth(2);
            //tMapView.addTMapPolyLine("Line1", tMapPolyLine);

        }catch(Exception e) {
            e.printStackTrace();
        }*/

        //NetworkTask networkTask = new NetworkTask();
        //networkTask.execute();
    }


    void findcarPath() {
        tmapData.findPathDataWithType(TMapPathType.CAR_PATH, tMapPointStart, tMapPointEnd,new FindPathDataListenerCallback() {
            @Override
            public void onFindPathData(TMapPolyLine polyLine) {
                polyLine.setLineColor(Color.BLUE);
                polyLine.setLineWidth(6);
                tMapView.addTMapPath(polyLine);
            }
        });

        tmapData.findPathDataAllType(TMapPathType.CAR_PATH, tMapPointStart, tMapPointEnd, new FindPathDataAllListenerCallback() {
            @Override
            public void onFindPathDataAll(Document document) {
                Element root = document.getDocumentElement();
                NodeList totaltime = root.getElementsByTagName("tmap:totalTime");

                int time = Integer.parseInt(totaltime.item(0).getTextContent());
                String sec = String.format("%02d",time % 60);
                String min = String.format("%02d",(time/60)%60);
                String hour = String.format("%02d",(time/3600));
                timeText.setText(hour+":"+min+":"+sec);

                NodeList taxicost = root.getElementsByTagName("tmap:taxiFare");
                costText.setText(taxicost.item(0).getTextContent().trim()+"원");
            }
        });
    }

    void findwalkPath() {
        tmapData.findPathDataWithType(TMapPathType.PEDESTRIAN_PATH, tMapPointStart, tMapPointEnd,new FindPathDataListenerCallback() {
            @Override
            public void onFindPathData(TMapPolyLine polyLine) {
                polyLine.setLineColor(Color.BLUE);
                polyLine.setLineWidth(6);
                tMapView.addTMapPath(polyLine);
            }
        });

        tmapData.findPathDataAllType(TMapPathType.PEDESTRIAN_PATH, tMapPointStart, tMapPointEnd, new FindPathDataAllListenerCallback() {
            @Override
            public void onFindPathDataAll(Document document) {
                Element root = document.getDocumentElement();
                NodeList totaltime = root.getElementsByTagName("tmap:totalTime");

                int time = Integer.parseInt(totaltime.item(0).getTextContent());
                String sec = String.format("%02d",time % 60);
                String min = String.format("%02d",(time/60)%60);
                String hour = String.format("%02d",(time/3600));
                timeText.setText(hour+":"+min+":"+sec);


            }
        });
    }



    public class NetworkTask extends AsyncTask<Void, Void, TMapPolyLine> {

        @Override
        protected TMapPolyLine doInBackground(Void... voids) {
            TMapPolyLine result = null;
            try {
                TMapData tmapData = new TMapData();
                TMapPolyLine tMapPolyLine = tmapData.findPathDataWithType(TMapData.TMapPathType.CAR_PATH, tMapPointStart, tMapPointEnd);
                tMapPolyLine.setLineColor(Color.BLUE);
                tMapPolyLine.setLineWidth(2);
                result = tMapPolyLine;
            } catch (Exception e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(TMapPolyLine s) {
            super.onPostExecute(s);
            tMapView.addTMapPolyLine("Line1", s);
        }
    }
}
