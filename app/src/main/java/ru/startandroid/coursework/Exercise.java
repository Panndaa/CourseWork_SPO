package ru.startandroid.coursework;

import android.content.Intent;
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

public class Exercise extends AppCompatActivity implements OnClickListener {

    private int[] positionName;
    private ListView list;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        positionName = new int[10];
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

        Button buttonSetTimer = (Button) findViewById(R.id.setTimer);
        buttonSetTimer.setOnClickListener(this);

        String[]  namesExercise = getResources().getStringArray(R.array.listexercise);
    }

    private void onClickList() {
        SparseBooleanArray sbArray = list.getCheckedItemPositions();
        count = 0;
        int position_in_array_of_elements = 0;
        for (int i = 0; i < sbArray.size(); i++) {
            int key = sbArray.keyAt(i);
            if (sbArray.get(key)) {
                count++;
                positionName[position_in_array_of_elements] = key;
                position_in_array_of_elements++;
            }
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.setTimer) {
            if (count != 0) {
                Intent intentSetTimer = new Intent(this, SetTimer.class);
                intentSetTimer.putExtra("value", count);
                intentSetTimer.putExtra("listId", positionName);
                startActivity(intentSetTimer);
            } else Toast.makeText(this, "Вы ничего не выбрали", Toast.LENGTH_LONG).show();
        }
    }
}
