package com.example.yenisquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText nameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize your variables and views here
        nameEditText = findViewById(R.id.name_edittext);
        nameEditText.setBackgroundResource(R.drawable.border);

        Button startButton = findViewById(R.id.start_button);
        startButton.setBackgroundColor(getResources().getColor(R.color.blue));
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the user's name
                String name = nameEditText.getText().toString().trim();
                Log.d("MainActivity", "Name passed to QuizActivity: " + name);


                if (name.isEmpty()) {
                   Toast.makeText(MainActivity.this, "Please enter your name.", Toast.LENGTH_SHORT).show();

                } else {
                    Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                    intent.putExtra("name", name);
                    startActivity(intent);
                }
            }
        });
    }



    public void startQuiz(View view) {
        String name = nameEditText.getText().toString().trim();

        if (name.isEmpty()) {
            Toast.makeText(this, "Please enter your name.", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, QuizActivity.class);
            intent.putExtra("name", name);
            startActivity(intent);
        }
    }



}

