package ru.startandroid.coursework;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class Training extends AppCompatActivity implements View.OnClickListener {

    private Button start, stop, finish;
    public TextView timer, current_exercise;
    public int seconds_performance, value_exercise, value_circle_int, seconds_rest,
            seconds_rest_exercise, current_value_exercise;
    public View view;
    private boolean on_timer, conversion, f;
    public int time, k;
    private MediaPlayer sound;
    public int[] image, fr;
    public String[] name_exercise;
    public int[] list_choice_exercise;
    public ImageView exercise;
    public String name;

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

        sound = MediaPlayer.create(this, R.raw.pip);

        start = (Button) findViewById(R.id.start_training_exercise);
        start.setOnClickListener(this);
        stop = (Button) findViewById(R.id.stop);
        stop.setOnClickListener(this);
        finish = (Button) findViewById(R.id.finish);
        finish.setOnClickListener(this);
        seconds_performance = minutes_performance_int * 60 + second_performance_int;
        seconds_rest = minutes_rest_int * 60 + seconds_rest_int;
        seconds_rest_exercise = minutes_rest_exercise_int * 60 + seconds_rest_exercise_int;
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_training_exercise:
                cycleTimer(true);
                break;
            case R.id.stop:
                cycleTimer(false);
                break;
            case R.id.finish:
                on_timer = false;
                cycleTimer(false);
                timer.setText("00:00");
                break;
            default:
                break;
        }
    }

    public void cycleTimer(final boolean control) {
        int i = 0;
        //  while (value_circle_int != 0) {

//        current_value_exercise = value_exercise;
//        while (current_value_exercise != 0) {

            k = list_choice_exercise[i];
            name = name_exercise[k];

       //     on_timer = control;

            exercise.setImageResource(image[k]);
            current_exercise.setText(name);

            runTimer(seconds_performance);
            i++;
          //  current_value_exercise--;
      //  }
    }


    public void runTimer(final int counter) {
        timer = (TextView) findViewById(R.id.timer);
        final Handler handler = new Handler();

        time = counter;

        // if (conversion) {
        handler.post(new Runnable() {

            public void run() {
                on_timer=true;
                do {
                    int minutes = (time % 3600) / 60;
                    int second = time % 60;
                    String value_time = String.format("%02d:%02d", minutes, second);
                    timer.setText(value_time);

                    if (time<3){
                        sound.start();
                    }
                    if (time < 0) {
                        on_timer = false;
                    }
                    time--;


                    handler.postDelayed(this, 1000);
                } while (!on_timer);

            }
        });
    }
}




