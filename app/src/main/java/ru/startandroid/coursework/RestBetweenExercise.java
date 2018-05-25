package ru.startandroid.coursework;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class RestBetweenExercise extends AppCompatActivity {
    TextView remaining_circles_text;
    public int remaining_circles,all_circle,rest_between_circle,rest_between_exericise;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_between_exercise);
        Bundle extras = getIntent().getExtras();

        remaining_circles=extras.getInt("remaining_quantity");
        all_circle=extras.getInt("value_circle");
        rest_between_circle=extras.getInt("seconds_rest");
        rest_between_exericise=extras.getInt("seconds_rest_exercise");

        remaining_circles_text=(TextView) findViewById(R.id.remaining_circles);
        remaining_circles_text.setText("Сейчас идет "+ remaining_circles + " круг из "+ all_circle+" кругов.");

        show_timer_rest();


    }  public void show_timer_rest() {
            CountDownTimer countDownTimer1 = new CountDownTimer( * 1000, 1000) {

                @Override
                public void onTick(long l) {
                    timer.setText(" " + l / 60000 + ":" + (l % 60000) / 1000);
                }

                @Override
                public void onFinish() {
                    //while (current_value_circle != 0) {
                    current_value_circle--;
                    go_to_rest(view);
                    // }
                }

            }.start();
}
