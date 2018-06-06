package ru.startandroid.coursework;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness);

        Button buttonNext = (Button) findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
            if( view.getId()==R.id.buttonNext) {
                Intent intent = new Intent(this, ChoiceOfAction.class);
                startActivity(intent);
            }
        }
    }

