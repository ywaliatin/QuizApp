package com.example.yenisquiz;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    private ProgressBar progressBar;
    private TextView questionTextView;
    private TextView answerATextView;
    private TextView answerBTextView;
    private TextView answerCTextView;
    private Button submitButton;

    private List<Question> questionList;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quizactivity);

        // Initialize the views
        progressBar = findViewById(R.id.progress_bar);
        questionTextView = findViewById(R.id.question_textview);
        answerATextView = findViewById(R.id.answer_a_textview);
        answerBTextView = findViewById(R.id.answer_b_textview);
        answerCTextView = findViewById(R.id.answer_c_textview);
        submitButton = findViewById(R.id.submit_button);
        submitButton.setBackgroundColor(getResources().getColor(R.color.blue));

        // Set click listeners for the answer TextViews
        answerATextView.setOnClickListener(this);
        answerBTextView.setOnClickListener(this);
        answerCTextView.setOnClickListener(this);

        // Load the question list
        questionList = new ArrayList<>();
        questionList.add(new Question("1. What is Yeni favourite colour?", "Blue"));
        questionList.add(new Question("2. What is Yeni AFL team?", "Eagles"));
        questionList.add(new Question("3. What is Yeni Favourite Italian food?", "Spaghetti"));
        questionList.add(new Question("4. What is Yeni scare of?", "Big dog"));
        questionList.add(new Question("5. Who is Yeni AFL player crush?", "Michael Voss"));

        // Get the name passed from the MainActivity
        Intent intent = getIntent();
        name = intent.getStringExtra("name");

        // Display the first question
        displayQuestion();
    }

    private void endQuiz() {
        // Calculate the percentage score
        double percentageScore = ((double) score / questionList.size()) * 100;

        // Create an Intent to display the score activity
        Intent intent = new Intent(QuizActivity.this, ActivityScore.class);
        intent.putExtra("percentageScore", percentageScore);
        intent.putExtra("totalQuestions", questionList.size());
        intent.putExtra("totalCorrectAnswers", score);
        intent.putExtra("name", name);
        startActivity(intent);

        // Finish the quiz activity
        finish();
    }



    private void displayQuestion() {
        // Get the current question
        Question currentQuestion = questionList.get(currentQuestionIndex);

        // Set the question text and answer options
        questionTextView.setText(currentQuestion.getQuestion());
        if (currentQuestionIndex == 0) {
            answerATextView.setText("A. " + "Blue");
            answerBTextView.setText("B. " + "Red");
            answerCTextView.setText("C. " + "Green");
        } else if (currentQuestionIndex == 1) {
            answerATextView.setText("A. " + "Eagles");
            answerBTextView.setText("B. " + "Brisbane Lions");
            answerCTextView.setText("C. " + "Geelong");
        } else if (currentQuestionIndex == 2) {
            answerATextView.setText("A. " + "Lasagna");
            answerBTextView.setText("B. " + "Pizza");
            answerCTextView.setText("C. " + "Spaghetti");
        } else if (currentQuestionIndex == 3) {
            answerATextView.setText("A. " + "Heights");
            answerBTextView.setText("B. " + "Thunder");
            answerCTextView.setText("C. " + "Big dog");
        } else if (currentQuestionIndex == 4) {
            answerATextView.setText("A. " + "Dane Swan");
            answerBTextView.setText("B. " + "Chris Judd");
            answerCTextView.setText("C. " + "Michael Voss");
        }

        // Set the background of all answer TextViews to the border drawable
        //answerATextView.setBackgroundResource(R.drawable.border);
        //answerBTextView.setBackgroundResource(R.drawable.border);
        //answerCTextView.setBackgroundResource(R.drawable.border);

        // Reset the submit button text
        submitButton.setText("Submit Answer");

        // Update the progress bar
        int progress = (currentQuestionIndex + 1) * 10;
        progressBar.setProgress(progress);
    }


    @Override
    public void onClick(View v) {
        // Check which answer was selected
        String selectedAnswer = "";
        TextView selectedTextView = (TextView) v;


        switch (v.getId()) {
            case R.id.answer_a_textview:
                selectedAnswer = answerATextView.getText().toString().substring(3);
                break;
            case R.id.answer_b_textview:
                selectedAnswer = answerBTextView.getText().toString().substring(3);
                break;
            case R.id.answer_c_textview:
                selectedAnswer = answerCTextView.getText().toString().substring(3);
                break;
        }

        // Check if the selected answer is correct
        String correctAnswer = questionList.get(currentQuestionIndex).getCorrectAnswer();
        boolean isCorrect = selectedAnswer.equals(correctAnswer);

        // Update the score
        if (isCorrect) {
            score++;
            selectedTextView.setBackgroundColor(getResources().getColor(R.color.green));

            //LinearLayout parentLayout = (LinearLayout) selectedTextView.getParent();
            //parentLayout.setBackgroundResource(R.drawable.green_border);
            //selectedTextView.setBackgroundResource(R.drawable.red_background);
            //selectedTextView.setTextColor(getResources().getColor(R.color.white));



            //selectedTextView.setBackgroundResource(R.drawable.red_background);

        } else {
            selectedTextView.setBackgroundColor(getResources().getColor(R.color.red));

        }

        // Set the submit button text to show if the answer was correct or not
        if (isCorrect) {
            submitButton.setText("Correct!");
            submitButton.setTextColor(getResources().getColor(R.color.green));

        } else {
            submitButton.setText("Incorrect!");
            submitButton.setTextColor(getResources().getColor(R.color.red));
        }

        // Disable the answer TextViews so the user can't change their answer
        answerATextView.setClickable(false);
        answerBTextView.setClickable(false);
        answerCTextView.setClickable(false);

        // Check if there are more questions to display
        if (currentQuestionIndex < questionList.size() - 1) {
            // Display the next question after a short delay
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    currentQuestionIndex++;
                    displayQuestion();

                    // Reset the submit button text and color
                    submitButton.setText("Submit Answer");
                    submitButton.setTextColor(getResources().getColor(R.color.black));

                    // Reset the background color of the answer text views
                    answerATextView.setBackgroundColor(getResources().getColor(R.color.white));
                    answerBTextView.setBackgroundColor(getResources().getColor(R.color.white));
                    answerCTextView.setBackgroundColor(getResources().getColor(R.color.white));

                    // Enable the answer TextViews
                    answerATextView.setClickable(true);
                    answerBTextView.setClickable(true);
                    answerCTextView.setClickable(true);
                }
            }, 1000);
        } else {
            // The quiz is over
            endQuiz();
        }
    }

}
