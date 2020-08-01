package com.pkeugine.whewigo.common;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import androidx.appcompat.app.AppCompatActivity;


public class BaseActivity extends AppCompatActivity {

    // AndroidManifest에 있는 Metadata 가져오기
    public String getMetadata(final Context context, final String key) {
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(
                    context.getPackageName(), PackageManager.GET_META_DATA);
            if (ai == null)
                return null;
            else if (ai.metaData == null)
                return null;
            else
                return ai.metaData.getString(key);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    // 로딩 다이얼로그 보여주기
    protected void showWaitingDialog() {
        WaitingDialog.showWaitingDialog(this, true, new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                finish();
            }
        });
    }

    // 로딩 다이얼로그 없애기
    protected void cancelWaitingDialog() {
        WaitingDialog.cancelWaitingDialog();
    }
}
