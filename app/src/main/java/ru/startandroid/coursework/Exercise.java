package ru.startandroid.coursework;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.view.View.OnClickListener;


public class Exercise  extends AppCompatActivity implements OnClickListener  {
    Button buttonBack;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        buttonBack=(Button) findViewById(R.id.exerciseToChoice);
        buttonBack.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.exerciseToChoice:
                Intent intentExerciseToChoice = new Intent(this, ChoiceOfAction.class);
                startActivity(intentExerciseToChoice);
                break;
            default:
                break;
        }
    }
}
