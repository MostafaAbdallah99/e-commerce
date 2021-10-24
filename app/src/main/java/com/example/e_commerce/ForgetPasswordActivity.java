package com.example.e_commerce;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import Database.Database;
import static com.example.e_commerce.SignUpActivity.EMAIL_HINT;
import static com.example.e_commerce.SignUpActivity.EMPTY;

public class ForgetPasswordActivity extends AppCompatActivity {

    final String EMPTY_EMAIL = "Please provide a valid email to send the password";
    final String INVALID_EMAIL = "Your email is not found, please provide a valid email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        TextInputEditText emailText = findViewById(R.id.emailForgetPasswordText);
        TextInputLayout emailLayout = findViewById(R.id.emailForgetPasswordLayout);
        TextView register = findViewById(R.id.registerForgetPasswordText);
        Button getPasswordButton = findViewById(R.id.sendPasswordBtn);
        Database eCommerceDatabase = Database.getInstance(this);

        emailText.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                emailLayout.setHint(EMPTY);
            } else if(emailText.getText().toString().equals(EMPTY)) {
                emailLayout.setHint(EMAIL_HINT);
            }
        });

        register.setOnClickListener(v -> {
            Intent intent = new Intent(ForgetPasswordActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

        getPasswordButton.setOnClickListener(view -> {
            if(emailText.getText().toString().isEmpty()){
                Toast.makeText(ForgetPasswordActivity.this, EMPTY_EMAIL, Toast.LENGTH_LONG).show();
            }
            else {
                if(eCommerceDatabase.customersDAO().checkEmail(emailText.getText().toString()) != 0){

                    Intent intent = new Intent(ForgetPasswordActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(ForgetPasswordActivity.this, INVALID_EMAIL, Toast.LENGTH_LONG).show();
                }
            }
        });
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
}