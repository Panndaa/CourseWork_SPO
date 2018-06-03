package ru.startandroid.coursework;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ChoiceOfAction extends AppCompatActivity implements OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_of_action);
        Button buttonRun = (Button) findViewById(R.id.buttonRun);
        buttonRun.setOnClickListener(this);
        Button buttonExercise = (Button) findViewById(R.id.buttonExercise);
        buttonExercise.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonRun:
                Intent intentRun = new Intent(this, Geolocation.class);
                startActivity(intentRun);
                break;
            case R.id.buttonExercise:
                Intent intentExercise = new Intent(this, Exercise.class);
                startActivity(intentExercise);
                break;
            default:
                break;
        }
    }
}


