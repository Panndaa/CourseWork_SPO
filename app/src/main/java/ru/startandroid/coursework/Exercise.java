package ru.startandroid.coursework;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Exercise extends AppCompatActivity implements OnClickListener {

    protected  Button buttonBack, buttonSetTimer;
    protected ListView list;
    public String[] namesExercise;
    public int c;
    int count;
    int[] positionName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        list = (ListView) findViewById(R.id.listView);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.listexercise, android.R.layout.simple_list_item_multiple_choice);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
             onClickList();
            }
        });

        buttonBack = (Button) findViewById(R.id.exerciseToChoice);
        buttonBack.setOnClickListener(this);

        buttonSetTimer = (Button) findViewById(R.id.setTimer);
        buttonSetTimer.setOnClickListener(this);

        namesExercise = getResources().getStringArray(R.array.listexercise);
    }

    public void onClickList() {
        SparseBooleanArray sbArray = list.getCheckedItemPositions();
        count=0;
        for (int i = 0; i < sbArray.size(); i++) {
            int key= sbArray.keyAt(i);
            if (sbArray.get(key)) {
                count++;
                c=count;
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setTimer:
                Intent intentSetTimer = new Intent(this, SetTimer.class);
                intentSetTimer.putExtra("value", c);
                startActivity(intentSetTimer);
                break;
            case R.id.exerciseToChoice:
                Intent intentExerciseToChoice = new Intent(this, ChoiceOfAction.class);
                startActivity(intentExerciseToChoice);
                break;
            default:
                break;
        }
    }
}
