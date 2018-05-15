package ru.startandroid.coursework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.widget.ListView;



public class Exercise  extends AppCompatActivity implements OnClickListener  {

    Button buttonBack, buttonSetTimer;
    ListView list;
    String[] namesExercise;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        list=(ListView)findViewById(R.id.listView);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(
                this,R.array.listexercise, android.R.layout.simple_list_item_multiple_choice);
        list.setAdapter(adapter);

        buttonBack=(Button) findViewById(R.id.exerciseToChoice);
        buttonBack.setOnClickListener(this);

        buttonSetTimer=(Button) findViewById(R.id.setTimer);
        buttonBack.setOnClickListener(this);

        namesExercise=getResources().getStringArray(R.array.listexercise);
    }
    public void onClickList(View arg0) {
        int count = list.getCheckedItemCount();
//        SparseBooleanArray sbArray=list.getCheckedItemPositions();
//        for(int i=0;i<sbArray.size();i++){
//            int positionInArray=sbArray.keyAt(i);
//            if(sbArray.get(positionInArray))
//
//        }
//    }
    }
    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.setTimer:
                Intent intentSetTimer = new Intent(this, SetTimer.class);
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
