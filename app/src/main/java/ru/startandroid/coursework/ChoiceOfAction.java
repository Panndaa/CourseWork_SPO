package ru.startandroid.coursework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.view.View.OnClickListener;

public class ChoiceOfAction extends AppCompatActivity implements OnClickListener {
    Button buttonExercise;
    Button buttonRun;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_of_action);
        buttonRun=(Button) findViewById(R.id.buttonRun);
        buttonRun.setOnClickListener(this);
        buttonExercise =(Button) findViewById(R.id.buttonExercise);
        buttonExercise.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.buttonRun:
                Intent intentRun = new Intent(this, ChoiceOfAction.class);
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


