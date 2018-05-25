package ru.startandroid.coursework;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class SetTimer extends AppCompatActivity implements OnClickListener {

    protected EditText minutes_performance, seconds_performance, value_circle, minutes_rest, seconds_rest,
            minutes_rest_exercise, seconds_rest_exercise;
    Button start_training, start_training1;
    TextView value, s;
    public int[] image;
    public int valueExerciseInt;
    public ArrayList list_choice_exercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_timer);

        Bundle list = getIntent().getExtras();
        ArrayList list_choice_exercise = list.getIntegerArrayList("listId");

        Bundle extras = getIntent().getExtras();
        valueExerciseInt = extras.getInt("value");

        int[] image = new int[]{R.drawable.otjimaniaback,
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

        minutes_performance = (EditText) findViewById(R.id.minutes_performance);
        seconds_performance = (EditText) findViewById(R.id.seconds_performance);

        minutes_rest = (EditText) findViewById(R.id.minutes_rest);
        seconds_rest = (EditText) findViewById(R.id.seconds_rest);

        minutes_rest_exercise = (EditText) findViewById(R.id.minutes_rest_exercise);
        seconds_rest_exercise = (EditText) findViewById(R.id.seconds_rest_exercise);

        value_circle = (EditText) findViewById(R.id.value_circle);

        start_training = (Button) findViewById(R.id.start_training);
        start_training.setOnClickListener(this);
        s = (TextView) findViewById(R.id.rest);

        String valueExercise = String.valueOf(valueExerciseInt);
        value = (TextView) findViewById(R.id.value);
        value.setText(valueExercise);

        if (valueExerciseInt < 2) {
            s.setText(null);
            minutes_rest_exercise.setEnabled(false);
            minutes_rest_exercise.setHint(null);
            minutes_rest_exercise.setEms(0);
            seconds_rest_exercise.setEnabled(false);
            seconds_rest_exercise.setHint(null);
            seconds_rest_exercise.setEms(0);
        }
    }

    private int checkCircle(EditText name) throws Exception {
        int valuec = Integer.parseInt(name.getText().toString());
        if (valuec > 9 || valuec < 0) {
            name.setText(null);
            name.setHintTextColor(Color.RED);
            throw new Exception();
        } else return valuec;
    }

    private int checkText(EditText name) throws Exception {
        if (name.getText().toString().equals("")) {
            return 0;
        }
        int time = Integer.parseInt(name.getText().toString());
        if (time > 59 || time < 0) {
            name.setText(null);
            name.setHintTextColor(Color.RED);
            throw new Exception();
        } else {
            return time;
        }
    }

    public void onClick(View view) {
        try {
            if (view.getId() == R.id.start_training) {
                initTraining(view);
            }
        } catch (Exception e) {
            Toast.makeText(this, "Не верное введено значение", Toast.LENGTH_LONG).show();
        }
    }

    private void initTraining(View view) throws Exception {

        int minutes_performance_int = checkText(minutes_performance);
        int second_performance_int = checkText(seconds_performance);
        int minutes_rest_int = checkText(minutes_rest);
        int seconds_rest_int = checkText(seconds_rest);
        int minutes_rest_exercise_int = checkText(minutes_rest_exercise);
        int seconds_rest_exercise_int = checkText(seconds_rest_exercise);
        int value_circle_int = checkCircle(value_circle);

        Intent intentStartTraining = new Intent(this, Training.class);
        intentStartTraining.putExtra("valueExercise",  valueExerciseInt);
        intentStartTraining.putExtra("minutes_performance_int", minutes_performance_int);
        intentStartTraining.putExtra("second_performance_int", second_performance_int);
        intentStartTraining.putExtra("minutes_rest_int", minutes_rest_int);
        intentStartTraining.putExtra("seconds_rest_int", seconds_rest_int);
        intentStartTraining.putExtra("minutes_rest_exercise_int", minutes_rest_exercise_int);
        intentStartTraining.putExtra("seconds_rest_exercise_int", seconds_rest_exercise_int);
        intentStartTraining.putExtra("value_circle_int", value_circle_int);
        startActivity(intentStartTraining);
    }
}
