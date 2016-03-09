package com.example.yeonjun.simplecalendar;
//https://www.youtube.com/watch?v=WnTKJKNB4kc
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button selectBtn,addBtn,deleteBtn;
    ListView lv;
    int year_x, month_x, day_x;
    Date currDate;
    ArrayList<Date> dateList;
    ArrayList<Event> eventsList;
    EventAdapter adapter;

    static final int DILOG_ID = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.container);
        selectBtn = (Button)findViewById(R.id.dateSelectButton);
        addBtn = (Button)findViewById(R.id.dateSelectButton);
        deleteBtn=(Button)findViewById(R.id.dateSelectButton);


        final Calendar cal = Calendar.getInstance();
        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH)+1;
        day_x = cal.get(Calendar.DAY_OF_MONTH);
        currDate = null;
        dateList = new ArrayList<Date>();
        eventsList = new ArrayList<Event>();
//        eventsList.add(new Event("title11","des","st","endd"));
        adapter=new EventAdapter(this, R.layout.row_layout,eventsList);
        lv.setAdapter(adapter);
        showDialogOnButtonClick();
    }

    public void showDialogOnButtonClick(){


        selectBtn.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
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
            TextView title = (TextView)findViewById(R.id.title);
            TextView description= (TextView)findViewById(R.id.description);
            TextView start= (TextView)findViewById(R.id.startTime);
            TextView end= (TextView)findViewById(R.id.endTime);

            title.setText(eventList.get(position).title);
            description.setText(eventList.get(position).description);
            start.setText(eventList.get(position).timeStart);
            end.setText(eventList.get(position).timeEnd);
            return convertView;
        }
    }


}

