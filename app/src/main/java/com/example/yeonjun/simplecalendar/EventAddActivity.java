package com.example.yeonjun.simplecalendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class EventAddActivity extends AppCompatActivity {

    EditText title;
    EditText description;
    EditText startHr;
    EditText startMin;
    EditText endHr;
    EditText endMin;
    Button addButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_add);

        title = (EditText) findViewById(R.id.actTitle);
        description = (EditText)findViewById(R.id.actDescription);
        startHr = (EditText)findViewById(R.id.startingHour);
        startMin = (EditText)findViewById(R.id.startingMin);
        endHr = (EditText)findViewById(R.id.endingHour);
        endMin = (EditText)findViewById(R.id.endingMin);
        addButton = (Button)findViewById(R.id.eventaddbutton);

        addButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
//                        adapter.add(new Event("a","B","c","d"));
//                        adapter.notifyDataSetChanged();
                        String start = startHr.getText().toString()+":"+startMin.getText().toString();
                        String end = endHr.getText().toString()+":"+endMin.getText().toString();
                        Toast.makeText(EventAddActivity.this,start+"hmm",Toast.LENGTH_SHORT).show();

                        Event newEvent = new Event(title.getText().toString(),description.getText().toString(),start,end);
                        MainActivity.eventsList.add(newEvent);
                        Intent i = new Intent(EventAddActivity.this, MainActivity.class);
                        startActivity(i);

                    }
                }

        );
    }
}
