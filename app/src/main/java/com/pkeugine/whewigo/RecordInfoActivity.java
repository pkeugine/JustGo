package com.pkeugine.whewigo;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.pkeugine.whewigo.common.BaseActivity;
import com.skt.Tmap.TMapView;


public class RecordInfoActivity extends BaseActivity {

    private LinearLayout linearLayoutTmap;
    private TMapView tMapView;
    private String TmapapiKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_info);

        TmapapiKey = getMetadata(this, "com.pkeugine.jhw.TmapKey");

        tMapView = new TMapView(this);
        tMapView.setSKTMapApiKey(TmapapiKey);

        linearLayoutTmap = findViewById(R.id.linearLayoutTmap);
        linearLayoutTmap.addView(tMapView);


    }
}
