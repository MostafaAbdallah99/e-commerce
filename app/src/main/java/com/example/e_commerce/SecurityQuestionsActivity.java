package com.example.e_commerce;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import Database.Database;

public class SecurityQuestionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_questions);

        TextView loginText = findViewById(R.id.securityLogin);
        EditText firstQuestion = findViewById(R.id.firstQuestionAnswer);
        EditText secondQuestion = findViewById(R.id.secondQuestionAnswer);
        Button signUpButton = findViewById(R.id.securitySignUpBtn);
        Bundle bundle = getIntent().getExtras();
        Database eCommerceDatabase = Database.getInstance(getApplicationContext());

        signUpButton.setOnClickListener(view -> {
            if(firstQuestion.getText().toString().isEmpty() || secondQuestion.getText().toString().isEmpty()){
                Toast.makeText(SecurityQuestionsActivity.this, SignUpActivity.FAIL_SIGN_UP, Toast.LENGTH_LONG).show();
            }
            else{
                eCommerceDatabase.customersDAO().updateCustomerSecurity(firstQuestion.getText().toString().toLowerCase(),
                        secondQuestion.getText().toString().toLowerCase(), bundle.getString(SignUpActivity.EMAIL_KEY));
                Intent intent = new Intent(SecurityQuestionsActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        loginText.setOnClickListener(v -> {
            Intent sign = new Intent(SecurityQuestionsActivity.this , LoginActivity.class);
            startActivity(sign);
        });
    }
}