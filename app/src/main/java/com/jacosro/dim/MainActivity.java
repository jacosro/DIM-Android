package com.jacosro.dim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static com.jacosro.dim.ExerciseActivity.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.exercise1).setOnClickListener(v -> startExercise(EX1));
        findViewById(R.id.exercise2).setOnClickListener(v -> startExercise(EX2));
        findViewById(R.id.exercise3).setOnClickListener(v -> startExercise(EX3));
        findViewById(R.id.exercise4).setOnClickListener(v -> startExercise(EX4));
        findViewById(R.id.exercise5).setOnClickListener(v -> startExercise(EX5));
    }

    private void startExercise(int exercise) {
        Intent intent = new Intent(this, ExerciseActivity.class);
        intent.putExtra("exercise", exercise);
        startActivity(intent);
    }
}
