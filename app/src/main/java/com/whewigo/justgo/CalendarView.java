package com.whewigo.justgo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;
/*https://karrel.tistory.com/9 이거는 깃에서 다운받고 참고했던 자료*/
/*https://godog.tistory.com/entry/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-%EB%82%A0%EC%A7%9C%EC%8B%9C%EA%B0%84-%EB%8B%A4%EC%9D%B4%EB%A1%9C%EA%B7%B8dialog*/
public class CalendarView extends AppCompatActivity {

    int y=0, m=0, d=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_view);

        Button date = findViewById(R.id.date);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate();
            }
        });
    }

    void showDate() {
        Calendar c = Calendar.getInstance();
        int nYear = c.get(Calendar.YEAR);
        int nMon = c.get(Calendar.MONTH);
        int nDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog.OnDateSetListener mDateSetListener =
                new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String strDate = String.valueOf(year) + "년 ";
                        strDate += String.valueOf(monthOfYear+1) + "월 ";
                        strDate += String.valueOf(dayOfMonth) + "일";

                        Toast.makeText(getApplicationContext(), strDate, Toast.LENGTH_SHORT).show();
                    }
                };

        DatePickerDialog oDialog = new DatePickerDialog(this,
                android.R.style.Theme_DeviceDefault_Light_Dialog,
                mDateSetListener, nYear, nMon, nDay);
        oDialog.show();
    }
}
