package ru.startandroid.coursework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SetTimer extends AppCompatActivity  {

   protected TextView value;
   protected EditText minutes_performance, seconds_performance, value_circle, minutes_rest, seconds_rest,
           minutes_rest_exercise, seconds_rest_exercise ;
 //  public int  valueExerciseInt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_timer);

        value = (TextView) findViewById(R.id.value);


        try {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                int valueExerciseInt = extras.getInt("value");
                String valueExercise= String.valueOf(valueExerciseInt);
                value.setText(valueExercise);
            }
        }
        catch (Exception e) {
            Toast.makeText(this, "Неправильно введено число", Toast.LENGTH_LONG).show();
            }
//        Bundle extras = getIntent().getExtras();
//        int valueExerciseInt = extras.getInt("value");



        minutes_performance = (EditText) findViewById(R.id.minutes_performance);
        seconds_performance = (EditText) findViewById(R.id.seconds_performance);

        minutes_rest = (EditText) findViewById(R.id.minutes_rest);
        seconds_rest = (EditText) findViewById(R.id.seconds_rest);

        minutes_rest_exercise=(EditText) findViewById(R.id.minutes_rest_exercise);
        seconds_rest_exercise = (EditText) findViewById(R.id.seconds_rest_exercise);

        value_circle=(EditText)findViewById(R.id.value_circle);

//        value = (TextView) findViewById(R.id.value);
//        value.setText(valueExercise);
//
        int  minutesPerformance= Integer.parseInt(minutes_performance.getText().toString());
        //checkText(minutesPerformance, minutes_performance);
        int  secondsPerformance=Integer.parseInt(seconds_performance.getText().toString());
        //checkText(secondsPerformance, seconds_performance);
        int  minutesRest=Integer.parseInt(minutes_rest.getText().toString());
       // checkText(minutesRest, minutes_rest);
        int  secondsRest=Integer.parseInt(seconds_rest.getText().toString());
       // checkText(secondsRest,seconds_rest);
        int  minutesRest_exercise=Integer.parseInt(minutes_rest_exercise.getText().toString());
       // checkText(minutesRest_exercise,minutes_rest_exercise);
        int  secondsRest_exercise=Integer.parseInt(seconds_rest_exercise.getText().toString());
        //checkText(secondsRest_exercise,seconds_rest_exercise);
        int  valueCircle=Integer.parseInt(value_circle.getText().toString());
       // checkCircle(valueCircle,value_circle);

//        if (valueExerciseInt<2)
//        {
//            setContentView(R.layout.small_set_timer);
//        }

    }

//    public void checkCircle(int valuec, EditText name){
//        try {
//            if(valuec > 9 || valuec < 0){
//                Toast.makeText(this, "Значение не может быть больше 9", Toast.LENGTH_LONG).show();
//                name.setText(null);
//            }
//
//        } catch (Exception e) {
//            Toast.makeText(this, "Неправильно введено число", Toast.LENGTH_LONG).show();
//        }
//    }
//
//    public void checkText(int time, EditText name){
//        try {
//
//            if(time > 60 || time < 0){
//                Toast.makeText(this, "Значение не может быть больше 60", Toast.LENGTH_LONG).show();
//                name.setText(null);
//            }
//
//        } catch (Exception e) {
//            Toast.makeText(this, "Неправильно введено число", Toast.LENGTH_LONG).show();
//        }
//    }
//
}
