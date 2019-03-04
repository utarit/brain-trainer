package com.example.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    TextView response;
    TextView scoreView;
    TextView question;
    TextView timerView;

    int locOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;

    Button goButton;
    Button button1;
    Button button2;
    Button button3;
    Button button4;

    CountDownTimer timer;


    ArrayList<Integer> answers = new ArrayList<>();

    public void start(View view) {

        goButton.setVisibility(View.INVISIBLE);
        response.setText("");
        scoreView.setText("0/0");
        score = 0;
        numberOfQuestions = 0;
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);
        newQuestion();

        timer = new CountDownTimer(30*1000 + 100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerView.setText(String.format("%ds", millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {
                response.setText(String.format("You made %d out of %d questions.", score, numberOfQuestions));
                goButton.setVisibility(View.VISIBLE);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                button4.setEnabled(false);
            }
        }.start();

    }

    public void newQuestion(){
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        question.setText(getString(R.string.question, a, b));

        locOfCorrectAnswer = rand.nextInt(4);

        answers.clear();
        for(int i = 0; i < 4; i++){
            if(i == locOfCorrectAnswer){
                answers.add(a+b);
            } else {
                int wrongAnswer = rand.nextInt(41);
                while(wrongAnswer == a + b){
                    wrongAnswer = rand.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }

        button1.setText(Integer.toString(answers.get(0)));
        button2.setText(Integer.toString(answers.get(1)));
        button3.setText(Integer.toString(answers.get(2)));
        button4.setText(Integer.toString(answers.get(3)));
    }

    public void chooseAnswer(View view){
        if(view.getTag().toString().equals(Integer.toString(locOfCorrectAnswer))){
            response.setText("Correct!");
            score++;
        } else {
            response.setText("Wrong :(");
        }
        numberOfQuestions++;
        scoreView.setText(String.format("%d/%d", score, numberOfQuestions));
        newQuestion();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = findViewById(R.id.startButton);
        question = findViewById(R.id.question);
        response = findViewById(R.id.response);
        scoreView = findViewById(R.id.result);
        button1 = findViewById(R.id.answer1);
        button2 = findViewById(R.id.answer2);
        button3 = findViewById(R.id.answer3);
        button4 = findViewById(R.id.answer4);
        timerView = findViewById(R.id.timer);

        newQuestion();


    }
}
