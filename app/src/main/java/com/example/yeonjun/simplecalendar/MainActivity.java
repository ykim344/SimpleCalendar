package com.example.yeonjun.simplecalendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button btn;
    int year_x, month_x, day_x;
    Date currDate;
    ArrayList<Date> dateList;
    static final int DILOG_ID = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Calendar cal = Calendar.getInstance();
        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH)+1;
        day_x = cal.get(Calendar.DAY_OF_MONTH);
        currDate = null;
        dateList = new ArrayList<Date>();
        showDialogOnButtonClick();
    }

    public void showDialogOnButtonClick(){
        btn = (Button)findViewById(R.id.dateSelectButton);

        btn.setOnClickListener(
            new View.OnClickListener(){
                public void onClick(View v){
                    showDialog(DILOG_ID);
                }
            }

        );


    }

    protected Dialog onCreateDialog(int id){
        if(id==DILOG_ID)
            return new DatePickerDialog(this, dpickerListener, year_x, month_x,day_x);
        return null;
    }

    private DatePickerDialog.OnDateSetListener dpickerListener
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            year_x = year;
            month_x = month;
            day_x = day;
            boolean visitedDate = false;
            Toast.makeText(MainActivity.this,year_x+"/"+month_x+"/"+day_x,Toast.LENGTH_SHORT).show();

            for(Date temp: dateList){
                if(temp.year == year_x&&temp.month==month_x&&temp.day==day_x){
                    currDate = temp;
                    visitedDate=true;
                    Toast.makeText(MainActivity.this, "visted date", Toast.LENGTH_SHORT).show();
                }
            }

            if(!visitedDate){
                currDate = new Date(year_x,month_x,day_x);
                dateList.add(currDate);
                Toast.makeText(MainActivity.this, "new date created", Toast.LENGTH_SHORT).show();
            }



        }
    };



}

