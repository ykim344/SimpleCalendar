package com.example.yeonjun.simplecalendar;
//https://www.youtube.com/watch?v=WnTKJKNB4kc
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button selectBtn,addBtn,deleteBtn;
    ListView lv;
    int year_x, month_x, day_x;
    static Date currDate;
    static ArrayList<Date> dateList;
    static ArrayList<Event> eventsList;
    EventAdapter adapter;
    static boolean isDeleteSelected;

    static final int DILOG_ID = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.container);
        //lv.setChoiceMode(lv.CHOICE_MODE_SINGLE);
        selectBtn = (Button)findViewById(R.id.dateSelectButton);
        addBtn = (Button)findViewById(R.id.addEventButton);
        deleteBtn=(Button)findViewById(R.id.removeEventButton);

        //make the list clickable
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(isDeleteSelected){
                    eventsList.remove(position);
                    adapter.notifyDataSetChanged();
                }
            }
        });

        //currDate = null;
        if(currDate==null){
            isDeleteSelected = false;
            dateList = new ArrayList<Date>();
            eventsList = new ArrayList<Event>();
            final Calendar cal = Calendar.getInstance();
            year_x = cal.get(Calendar.YEAR);
            month_x = cal.get(Calendar.MONTH)+1;
            day_x = cal.get(Calendar.DAY_OF_MONTH);
        }else{
            year_x=currDate.year;
            month_x=currDate.month;
            day_x=currDate.day;
        }

        adapter=new EventAdapter(this, R.layout.row_layout,eventsList);
        lv.setAdapter(adapter);

        buttonClickhandler();

    }


    public void buttonClickhandler(){


        selectBtn.setOnClickListener(

                new View.OnClickListener() {
                    public void onClick(View v) {
                        isDeleteSelected = false;
                        showDialog(DILOG_ID);
                    }
                }

        );

        addBtn.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        isDeleteSelected = false;
                        if(currDate!=null){
                            Intent i = new Intent(MainActivity.this, EventAddActivity.class);
                            startActivity(i);
                        }else{
                            Toast.makeText(MainActivity.this,"Select a day first",Toast.LENGTH_SHORT).show();
                        }


                    }
                }

        );

        deleteBtn.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        isDeleteSelected = true;
                        Toast.makeText(getApplicationContext(),"Choose Event(s)",Toast.LENGTH_SHORT).show();

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

                }
            }

            if(!visitedDate){
                currDate = new Date(year_x,month_x,day_x);
                dateList.add(currDate);

            }

            eventsList = currDate.eventList;
            adapter=new EventAdapter(MainActivity.this, R.layout.row_layout,eventsList);
            lv.setAdapter(adapter);

        }
    };

    public class EventAdapter extends ArrayAdapter{

        private List<Event> eventList;
        private int resourse;
        private LayoutInflater inflater;

        public EventAdapter(Context context, int resourse,List objects){
            super(context,resourse,objects);
            eventList = objects;
            this.resourse = resourse;
            inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        }

        public View getView(int position, View convertView, ViewGroup parent){

            if(convertView == null){
                convertView = inflater.inflate(R.layout.row_layout,null);
            }

            TextView title = (TextView)convertView.findViewById(R.id.title);
            TextView description= (TextView)convertView.findViewById(R.id.description);
            TextView start= (TextView)convertView.findViewById(R.id.startTime);
            TextView end= (TextView)convertView.findViewById(R.id.endTime);
            String startText;
            String endText;

            title.setText(eventList.get(position).title);
            description.setText(eventList.get(position).description);


            startText = timeFormat(eventList.get(position).timeStartHr,eventList.get(position).timeStartMin);
            endText = timeFormat(eventList.get(position).timeEndHr,eventList.get(position).timeEndMin);

            start.setText(startText);
            end.setText(endText);
            return convertView;
        }

        public String timeFormat(int hr, int min){
            if(min<10)
                return hr+" : 0"+min;
            else
                return hr+" : "+min;
        }
    }


}

