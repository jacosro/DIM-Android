package com.jacosro.dim;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jacosro.dim.exercise1.Exercise1;
import com.jacosro.dim.exercise2.Exercise2;
import com.jacosro.dim.exercise3.Exercise3;
import com.jacosro.dim.exercise4.Exercise4;
import com.jacosro.dim.exercise5.Exercise5;

public class ExerciseActivity extends AppCompatActivity {

    public static final int EX1 = 1;
    public static final int EX2 = 2;
    public static final int EX3 = 3;
    public static final int EX4 = 4;
    public static final int EX5 = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        Intent intent = getIntent();
        
        int exercise = intent.getIntExtra("exercise", 1);
        
        View exerciseView;

        switch (exercise) {
            case EX1:
                exerciseView = new Exercise1(this);
                break;
            case EX2:
                exerciseView = new Exercise2(this);
                break;
            case EX3:
                exerciseView = new Exercise3(this);
                break;
            case EX4:
                exerciseView = new Exercise4(this);
                break;
            default:
                exerciseView = new Exercise5(this);
        }

        ConstraintLayout constraintLayout = findViewById(R.id.mainLayout);

        constraintLayout.addView(exerciseView);
    }
}
