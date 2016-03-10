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
    int startHrInt;
    int startMinInt;
    int endHrInt;
    int endMinInt;
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


                        try{
                            if(title.getText().toString().length()==0){
                                throw new IllegalArgumentException();
                            }
                            startHrInt=Integer.parseInt(startHr.getText().toString());
                            startMinInt=Integer.parseInt(startMin.getText().toString());
                            endHrInt = Integer.parseInt(endHr.getText().toString());
                            endMinInt=Integer.parseInt(endMin.getText().toString());

                            if(startMinInt<0||startMinInt>59){
                                throw new NumberFormatException();
                            }
                            if(endMinInt<0||endMinInt>59){
                                throw new NumberFormatException();
                            }
                            if(startHrInt<0||startHrInt>23){
                                throw new NumberFormatException();
                            }
                            if(endHrInt<0||endHrInt>23){
                                throw new NumberFormatException();
                            }

                            if(endHrInt<startHrInt){
                                throw new NumberFormatException();
                            }
                            if(endHrInt==startHrInt&&endMinInt<startMinInt){
                                throw new NumberFormatException();
                            }



                            Event newEvent = new Event(title.getText().toString(),description.getText().toString(),
                                    startHrInt,startMinInt,endHrInt,endMinInt);

                            int pos=0;
                            for(Event temp:MainActivity.eventsList){
                                if(temp.timeStartHr<startHrInt){
                                    pos++;
                                }
                                else if(temp.timeStartHr==startHrInt&&temp.timeStartMin<startMinInt){
                                    pos++;
                                }
                                else
                                    break;
                            }

                            MainActivity.eventsList.add(pos,newEvent);
                            Intent i = new Intent(EventAddActivity.this, MainActivity.class);
                            startActivity(i);
                        }catch (NumberFormatException e){
                            Toast.makeText(EventAddActivity.this,"invalid time input, try again",Toast.LENGTH_SHORT).show();
                        }catch (IllegalArgumentException e){
                            Toast.makeText(EventAddActivity.this,"please type event title, try again",Toast.LENGTH_SHORT).show();
                        }



                    }
                }

        );
    }


}
