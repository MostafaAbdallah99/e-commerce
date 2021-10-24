package com.example.e_commerce;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import Database.Database;

public class AnswerQuestionsActivity extends AppCompatActivity {

    final String ANSWERS = "Please, provide a valid answers";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_questions);

        TextView register = findViewById(R.id.registerLogin);
        Database eCommerceDatabase = Database.getInstance(getApplicationContext());
        EditText firstQuestion = findViewById(R.id.firstQuestionAnswer2);
        EditText secondQuestion = findViewById(R.id.secondQuestionAnswer2);
        Button resetButton = findViewById(R.id.resetPasswordBtn);
        Bundle bundle = getIntent().getExtras();

        resetButton.setOnClickListener(view -> {
            if(firstQuestion.getText().toString().isEmpty() || secondQuestion.getText().toString().isEmpty()){
                Toast.makeText(AnswerQuestionsActivity.this, SignUpActivity.FAIL_SIGN_UP, Toast.LENGTH_LONG).show();
            }
            else{
                if(eCommerceDatabase.customersDAO().checkQuestions(firstQuestion.getText().toString().toLowerCase(),
                        secondQuestion.getText().toString().toLowerCase(), bundle.getString(SignUpActivity.EMAIL_KEY)) != 0){
                    Intent intent = new Intent(AnswerQuestionsActivity.this, ResetPasswordActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(AnswerQuestionsActivity.this, ANSWERS, Toast.LENGTH_LONG).show();
                }
            }
        });

        register.setOnClickListener(v -> {
            Intent intent = new Intent(AnswerQuestionsActivity.this, SignUpActivity.class);
            startActivity(intent);
        });
    }
}