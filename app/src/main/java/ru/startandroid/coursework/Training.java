package ru.startandroid.coursework;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Training extends AppCompatActivity {

    Button start;
    TextView timer;
    public int seconds_performance, value_exercise, value_circle_int, seconds_rest,
            seconds_rest_exercise, current_value_circle, current_value_exercise;
    public View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_training);

        Bundle extras = getIntent().getExtras();
        int minutes_performance_int = extras.getInt("minutes_performance_int");
        int second_performance_int = extras.getInt("second_performance_int");
        int minutes_rest_int = extras.getInt("minutes_rest_int");
        int seconds_rest_int = extras.getInt("seconds_rest_int");
        int minutes_rest_exercise_int = extras.getInt("minutes_rest_exercise_int");
        int seconds_rest_exercise_int = extras.getInt("seconds_rest_exercise_int");
        value_circle_int = extras.getInt("value_circle_int");
        value_exercise = extras.getInt("valueExercise");

        timer = (TextView) findViewById(R.id.timer);
        start = (Button) findViewById(R.id.start);

        seconds_performance = minutes_performance_int * 60 + second_performance_int;
        seconds_rest = minutes_rest_int * 60 + seconds_rest_int;
        seconds_rest_exercise = minutes_rest_exercise_int * 60 + seconds_rest_exercise_int;

        current_value_circle = value_circle_int;

        show_timer();

    }

    public void show_timer() {
        CountDownTimer countDownTimer1 = new CountDownTimer(seconds_performance * 1000, 1000) {

            @Override
            public void onTick(long l) {
                timer.setText(" " + l / 60000 + ":" + (l % 60000) / 1000);
            }

            @Override
            public void onFinish() {
                while (current_value_circle != 0) {
                    current_value_exercise = value_exercise;
                    while (current_value_exercise != 0) {
                        current_value_exercise--;
                        go_to_rest(view);
                    }
                    current_value_circle--;
                }
            }

        }.start();
    }

    public void go_to_rest(View view) {
        Intent intentRestBetweenExercise = new Intent(this, RestBetweenExercise.class);
        intentRestBetweenExercise.putExtra("remaining_quantity", current_value_circle);//оставшиеся круги
        intentRestBetweenExercise.putExtra("value_circle", value_circle_int);//общие круги
        intentRestBetweenExercise.putExtra("value_exercise", value_exercise);//кол-во упражнений всего
        intentRestBetweenExercise.putExtra("current_value_exercise", current_value_exercise);//кол-во упражнений оставшееся
        intentRestBetweenExercise.putExtra("seconds_rest", seconds_rest);//секунды отдыха между кругами
        intentRestBetweenExercise.putExtra("seconds_rest_exercise", seconds_rest_exercise);//секунды отдыха между упражнениями
        startActivity(intentRestBetweenExercise);
    }
}
