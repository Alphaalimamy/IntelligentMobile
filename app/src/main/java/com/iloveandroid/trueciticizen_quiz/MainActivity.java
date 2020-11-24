package com.iloveandroid.trueciticizen_quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button falseButton;
    private Button trueButton;
    private TextView questionTextView;
    private ImageButton nextButton;
    private ImageButton prevButton;
    public   int currentQuestionIndex = 0;

    private final Question[] questionBank = new Question[]{
            new Question(R.string.question_amendments,false),
            new Question(R.string.question_constitution, true),
            new Question(R.string.question_declaration, true),
            new Question(R.string.question_government, false),
            new Question(R.string.question_government_feds, false),
            new Question(R.string.question_government_senators, true),
            new Question(R.string.question_independence_rights, true),
            new Question(R.string.question_religion, true),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        falseButton = findViewById( R.id.false_button );
        trueButton = findViewById( R.id.true_button );
        nextButton = findViewById( R.id.nex_button );
        prevButton = findViewById( R.id.prev_button );

        questionTextView = findViewById( R.id.answer_text_view );

        trueButton.setOnClickListener( this );
        falseButton.setOnClickListener( this );
        nextButton.setOnClickListener( this );
        prevButton.setOnClickListener( this );
    }
    public void onClick(View v){

        switch (v.getId()){
            case R.id.false_button:
                checkAnswer(false);
                break;
            case R.id.true_button:
                checkAnswer(true);
                break;
            case R.id.nex_button:
                currentQuestionIndex = (currentQuestionIndex + 1) % questionBank.length;
                updateQuestion();
                break;
            case R.id.prev_button:
                if (currentQuestionIndex > 0){
                    currentQuestionIndex = (currentQuestionIndex) % questionBank.length;
                    updateQuestion();
                }

        }

    }
    private void updateQuestion(){

        Log.d("Current", "onClick: " + currentQuestionIndex);
        questionTextView.setText(questionBank[currentQuestionIndex].getAnswerResId());
        //Toast.makeText(this, "Next", Toast.LENGTH_SHORT).show();
    }

    public void checkAnswer(boolean userChooseCoreect){
        boolean answerTrue = questionBank[currentQuestionIndex].isAnswerTrue();

        int toastMessageId = 0;
        if (userChooseCoreect == answerTrue){
            toastMessageId = R.string.correct_answer;
        } else {
            toastMessageId = R.string.wrong_answer;
        }
        Toast.makeText(this, toastMessageId, Toast.LENGTH_LONG).show();
    }
}