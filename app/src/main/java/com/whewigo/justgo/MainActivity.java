package com.whewigo.justgo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;

import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapPOIItem;
import com.skt.Tmap.TMapView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String Appkey = "0d7d31d9-eb6b-4540-a1cc-864ca37b8bf8";
    private WebView mWebView; // 웹뷰 선언
    private WebSettings mWebSettings; //웹뷰세팅
    private Button carButton;
    private Button walkButton;
    private Button carsButton;
    private Button nextButton;

    private final Handler handler = new Handler();
/*
    private class AndroidBridge {
        @JavascriptInterface
        public void setDeviceInfo(final String arg) { // must be final
            handler.post(new Runnable() {
                public void run() {


                }
            });
        }
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        LinearLayout linearLayoutTmap = (LinearLayout)findViewById(R.id.linearLayoutTmap);

        TMapView tMapView = new TMapView(this);
        tMapView.setSKTMapApiKey(Appkey);
        linearLayoutTmap.addView(tMapView);*/

        // 웹뷰 시작
        mWebView = (WebView) findViewById(R.id.webView);
        //mWebView.addJavascriptInterface(new AndroidBridge(), "MyApp");
        mWebView.setWebViewClient(new WebViewClient()); // 클릭시 새창 안뜨게
        mWebSettings = mWebView.getSettings(); //세부 세팅 등록
        mWebSettings.setJavaScriptEnabled(true); // 웹페이지 자바스클비트 허용 여부
        mWebSettings.setSupportMultipleWindows(false); // 새창 띄우기 허용 여부
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(false); // 자바스크립트 새창 띄우기(멀티뷰) 허용 여부
        mWebSettings.setUseWideViewPort(true); // 화면 사이즈 맞추기 허용 여부
        mWebSettings.setLoadWithOverviewMode(true); // 메타태그 허용 여부

        mWebSettings.setSupportZoom(false); // 화면 줌 허용 여부
        mWebSettings.setBuiltInZoomControls(false); // 화면 확대 축소 허용 여부
        mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);//.SINGLE_COLUMN);//.SINGLE_COLUMN); // 컨텐츠 사이즈 맞추기
        mWebSettings.setCacheMode(WebSettings.LOAD_NO_CACHE); // 브라우저 캐시 허용 여부
        mWebSettings.setDomStorageEnabled(true); // 로컬저장소 허용 여부
        mWebSettings.setAllowUniversalAccessFromFileURLs(true);
        mWebView.loadUrl("file:///android_asset/www/tmap.html"); // 웹뷰에 표시할 웹사이트 주소, 웹뷰 시작


        nextButton = findViewById(R.id.next);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),tmapDemo.class);
                startActivity(intent);
            }
        });

        carsButton = findViewById(R.id.change);
        carsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWebView.loadUrl("javascript:msgback('" + 126.9850380932383 + "','" + 37.566567545861645 +
                        "','" + 127.10331814639885 + "','" + 37.403049076341794 + "')");
            }
        });
        carButton = findViewById(R.id.car);
        carButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mWebView.loadUrl("file:///android_asset/www/tmap.html"); // 웹뷰에 표시할 웹사이트 주소, 웹뷰 시작
                mWebView.loadUrl("javascript:msgback('" + 126.9850380932383 + "','" + 37.566567545861645 +
                        "','" + 127.10331814639885 + "','" + 37.403049076341794 + "')");


            }
        });
        walkButton = findViewById(R.id.walk);
        walkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //mWebView.loadUrl("javascript:inputLocation()");
                mWebView.loadUrl("file:///android_asset/www/tmap0.html"); // 웹뷰에 표시할 웹사이트 주소, 웹뷰 시작
            }
        });








/*
        TMapData tmapdata = new TMapData();
        String strData = "서울 편의점";
        tmapdata.findAllPOI(strData, new TMapData.FindAllPOIListenerCallback() {
            @Override
            public void onFindAllPOI(ArrayList poiItem) {
                for(int i = 0; i < poiItem.size(); i++) {
                    TMapPOIItem item = (TMapPOIItem) poiItem.get(i);
                    Log.d("POI Name: ", item.getPOIName().toString() + ", " +
                            "Address: " + item.getPOIAddress().replace("null", "")  + ", " +
                            "Point: " + item.getPOIPoint().toString());
                }
            }
        });*/


    }
}
