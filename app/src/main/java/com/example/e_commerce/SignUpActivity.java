package com.example.e_commerce;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import Database.Database;

public class SignUpActivity extends AppCompatActivity {

    final String INVALID_EMAIL = "Please provide a valid E-mail";
    final String CUSTOMER_NAME_HINT = "Name";
    final String JOB_HINT = "Job";
    final String TAKEN_EMAIL = "This email is already taken";
    public static final String FAIL_SIGN_UP = "Please complete the fields";
    public static final String EMPTY = "";
    public static final String EMAIL_HINT = "Email";
    public static final String PASSWORD_HINT = "Password";
    public static final String CUSTOMER_NAME_KEY = "CustomerNameKey";
    public static final String EMAIL_KEY = "EmailKey";
    public static final String JOB_KEY = "JobKey";
    public static final String PASSWORD_KEY = "PasswordKey";
    public static final String END_LINE = "\n";
    public static final String SPACE = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Button signUpButton = findViewById(R.id.signUpBtn);
        TextInputEditText customerName = findViewById(R.id.username);
        TextInputLayout customerNameLayout = findViewById(R.id.usernameLayout);
        TextInputEditText email = findViewById(R.id.email);
        TextInputLayout emailLayout = findViewById(R.id.emailLayout);
        TextInputEditText password = findViewById(R.id.password);
        TextInputLayout passwordLayout = findViewById(R.id.passwordLayout);
        TextInputEditText job = findViewById(R.id.job);
        TextInputLayout jobLayout = findViewById(R.id.jobLayout);
        TextView loginText = findViewById(R.id.bottom_txt);
        Database eCommerceDatabase = Database.getInstance(getApplicationContext());

        customerName.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                customerNameLayout.setHint(EMPTY);
            } else if(customerName.getText().toString().equals(EMPTY)) {
                customerNameLayout.setHint(CUSTOMER_NAME_HINT);
            }
        });

        email.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                emailLayout.setHint(EMPTY);
            } else if(email.getText().toString().equals(EMPTY)) {
                emailLayout.setHint(EMAIL_HINT);
            }
        });

        password.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                passwordLayout.setHint(EMPTY);
            } else if(password.getText().toString().equals(EMPTY)) {
                passwordLayout.setHint(PASSWORD_HINT);
            }
        });

        job.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                jobLayout.setHint(EMPTY);
            } else if(job.getText().toString().equals(EMPTY)) {
                jobLayout.setHint(JOB_HINT);
            }
        });

        signUpButton.setOnClickListener(v -> {
            if(customerName.getText().toString().isEmpty() || password.getText().toString().isEmpty() || job.getText().toString().isEmpty()
                    || email.getText().toString().isEmpty()){
                Toast.makeText(SignUpActivity.this, FAIL_SIGN_UP, Toast.LENGTH_LONG).show();
            }
            else{
                if(Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
                    if(eCommerceDatabase.customersDAO().checkEmail(email.getText().toString()) != 0){
                        Toast.makeText(SignUpActivity.this, TAKEN_EMAIL, Toast.LENGTH_LONG).show();
                    }
                    else{
                        Intent intent = new Intent(SignUpActivity.this, CompleteSignUpActivity.class);
                        Bundle extras = new Bundle();
                        extras.putString(CUSTOMER_NAME_KEY, customerName.getText().toString());
                        extras.putString(PASSWORD_KEY, password.getText().toString());
                        extras.putString(JOB_KEY, job.getText().toString());
                        extras.putString(EMAIL_KEY, email.getText().toString());
                        intent.putExtras(extras);
                        startActivity(intent);
                    }
                }
                else{
                    Toast.makeText(SignUpActivity.this, INVALID_EMAIL, Toast.LENGTH_LONG).show();
                }
            }
        });

        loginText.setOnClickListener(v -> {
            Intent sign = new Intent(SignUpActivity.this , LoginActivity.class);
            startActivity(sign);
        });
    }
}
