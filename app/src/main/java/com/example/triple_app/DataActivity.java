package com.example.triple_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DataActivity extends AppCompatActivity {

    private ListView inputList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        inputList = findViewById(R.id.inputList);

        List<QuestionOrTask> questions = JSONHelper.importFromJSON(this);
        List<String> questionsStr = new ArrayList<>(questions.size());
        for (QuestionOrTask q : questions) {
            questionsStr.add(Objects.toString(q));
        }
        ArrayAdapter<String> questionsAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                questionsStr
        );

        inputList.setAdapter(questionsAdapter);
//        ArrayList<QuestionOrTask>
    }
}