package com.example.yenisquiz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityScore extends AppCompatActivity implements View.OnClickListener {

    private TextView scoreTextView;
    private TextView congratulationsTextView;
    private Button startButton;
    private Button finishButton;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityscore);

        // Get the score TextView and congratulations TextView
        scoreTextView = findViewById(R.id.score_textview);
        congratulationsTextView = findViewById(R.id.congratulations_textview);

        // Get the percentage score, total questions, and total correct answers passed from the QuizActivity
        Intent intent = getIntent();
        double percentageScore = intent.getDoubleExtra("percentageScore", 0);
        int totalQuestions = intent.getIntExtra("totalQuestions", 0);
        int totalCorrectAnswers = intent.getIntExtra("totalCorrectAnswers", 0);

        // Get the name passed from the MainActivity
        name = intent.getStringExtra("name");
        Log.d("ActivityScore", "Name received: " + name);


        // Display the congratulations message and name in the congratulations TextView
        //congratulationsTextView.setText(String.format("Congratulations, %s!", name));
        // Display the congratulations message and name in the congratulations TextView, with red color
        congratulationsTextView.setText(String.format("Congratulations, %s!", name));
        congratulationsTextView.setTextColor(getResources().getColor(R.color.red));


        // Display the total number of correct answers out of the total questions in the score TextView
        scoreTextView.setText(String.format("YOUR SCORE: \n\n %d correct of %d questions", totalCorrectAnswers, totalQuestions));
        //scoreTextView.setText(String.format("Your score: %d/%d\n\nCongratulations, %s!", totalCorrectAnswers, totalQuestions, name));


        // Set click listeners for the buttons
        startButton = findViewById(R.id.start_button);
        startButton.setBackgroundColor(getResources().getColor(R.color.blue));
        startButton.setOnClickListener(this);
        finishButton = findViewById(R.id.finish_button);
        finishButton.setBackgroundColor(getResources().getColor(R.color.blue));
        finishButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == startButton) {
            // Return to the QuizActivity to start the quiz again
            Intent intent = new Intent(ActivityScore.this, QuizActivity.class);
            intent.putExtra("name", name);
            startActivity(intent);
            finish();
        } else if (v == finishButton) {
            // Exit the app
            finishAffinity();
        }
    }
}
