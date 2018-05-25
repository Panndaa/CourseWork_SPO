package ru.startandroid.coursework;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Exercise extends AppCompatActivity implements OnClickListener {

    protected Button buttonBack, buttonSetTimer;
    protected ListView list;
    public String[] namesExercise;
    private int count;
    private SharedPreferences preferences;
    public ArrayList<Integer> positionName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        positionName = new ArrayList <>();
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
        count = 0;
        for (int i = 0; i < sbArray.size(); i++) {
            int key = sbArray.keyAt(i);
            if (sbArray.get(key)) {
                count++;
                positionName.add(i);
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setTimer:
                if (count != 0) {
                    Intent intentSetTimer = new Intent(this, SetTimer.class);
                    intentSetTimer.putExtra("value", count);
                    intentSetTimer.putIntegerArrayListExtra("listId", positionName);

                    startActivity(intentSetTimer);
                } else Toast.makeText(this, "Вы ничего не выбрали", Toast.LENGTH_LONG).show();
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
