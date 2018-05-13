package ru.startandroid.coursework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.content.Intent;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity implements OnClickListener{
    Button buttonNext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness);
        buttonNext=(Button) findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.buttonNext:
                Intent intent = new Intent(this, ChoiceOfAction.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
