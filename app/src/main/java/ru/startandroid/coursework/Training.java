package ru.startandroid.coursework;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;


public class Training extends AppCompatActivity implements View.OnClickListener {

    private TextView timer, current_exercise;
    private MediaPlayer finish_sound, tuk;
    private ImageView exercise;
    private CountDownTimer countDownTimer;
    private int seconds_performance, value_exercise, value_circle_int, seconds_rest,
            seconds_rest_exercise, current_value_exercise, k, i;
    private String name, text_timer;
    private int[] image, list_choice_exercise;
    private String[] name_exercise;

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
        list_choice_exercise = extras.getIntArray("Array");

        exercise = (ImageView) findViewById(R.id.view_exercise);
        current_exercise = (TextView) findViewById(R.id.current_exercise);
        name_exercise = getResources().getStringArray(R.array.listexercise);

        image = new int[]{R.drawable.otjimaniaback,
                R.drawable.otjimaniakoleni,
                R.drawable.planka,
                R.drawable.podtiagivanie,
                R.drawable.ruki,
                R.drawable.pres,
                R.drawable.skakalka,
                R.drawable.spina,
                R.drawable.voshojdenia,
                R.drawable.vupadu
        };

        finish_sound = MediaPlayer.create(this, R.raw.pip);
        tuk = MediaPlayer.create(this, R.raw.tuk);

        timer = (TextView) findViewById(R.id.timer);
        k = list_choice_exercise[0];
        name = name_exercise[k];

        exercise.setImageResource(image[k]);
        current_exercise.setText(name);
        Button start = (Button) findViewById(R.id.start_training_exercise);
        start.setOnClickListener(this);
        Button stop = (Button) findViewById(R.id.stop);
        stop.setOnClickListener(this);
        Button finish = (Button) findViewById(R.id.finish);
        finish.setOnClickListener(this);
        seconds_performance = minutes_performance_int * 60 + second_performance_int;
        seconds_rest = minutes_rest_int * 60 + seconds_rest_int;
        seconds_rest_exercise = minutes_rest_exercise_int * 60 + seconds_rest_exercise_int;

        i = 0;

    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_training_exercise:
                current_value_exercise = value_exercise;
                cycleTimer(seconds_performance);
                break;
            case R.id.stop:
                cancel();
                break;
            case R.id.finish:
                cancel();
                Intent intentExercise = new Intent(this, Exercise.class);
                startActivity(intentExercise);
                break;
            default:
                break;
        }
    }


    private void cycleTimer(int sec) {
        k = list_choice_exercise[i];
        name = name_exercise[k];

        exercise.setImageResource(image[k]);
        current_exercise.setText(name);
        countDownTimer = new CountDownTimer(sec * 1000, 1000) {
            @Override
            public void onTick(long l) {
                tuk.start();
                int minutes = (int) ((l / 1000) / 60);
                int seconds = +(int) ((l / 1000) % 60);
                text_timer = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
                timer.setText(text_timer);

            }

            @Override
            public void onFinish() {
                finish_sound.start();
                if (value_circle_int != 0) {

                    if (current_value_exercise > 1) {
                        cycleTimerRest(seconds_rest_exercise);
                        current_value_exercise--;
                        i++;
                    } else if (value_circle_int > 1) {
                        current_value_exercise = value_exercise;
                        i = 0;
                        cycleTimerRest(seconds_rest);
                        value_circle_int--;
                    } else if (value_circle_int == 1) {
                        value_circle_int--;
                        current_exercise.setText("Поздравляю с окончание тренировки");
                        timer.setText(R.string.template_speed_time);
                        exercise.setImageResource(R.drawable.finish);
                    }
                }


            }
        }.start();
    }

    private void cycleTimerRest(int sec) {
        exercise.setImageResource(R.drawable.tried);
        current_exercise.setText("Отдых");

        countDownTimer = new CountDownTimer(sec * 1000, 1000) {
            @Override
            public void onTick(long l) {
                int minutes = (int) ((l / 1000) / 60);
                int seconds = +(int) ((l / 1000) % 60);
                text_timer = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
                timer.setText(text_timer);
            }

            @Override
            public void onFinish() {
                finish_sound.start();
                cycleTimer(seconds_performance);

            }
        }.start();
    }

    private void cancel() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
            timer.setText(R.string.template_speed_time);
        }
    }
}










