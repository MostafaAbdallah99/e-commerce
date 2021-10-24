package com.example.e_commerce;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.util.HashMap;
import Database.Customers;
import UserSession.SessionManager;
import static com.example.e_commerce.SignUpActivity.PASSWORD_HINT;
import Database.Database;
import static com.example.e_commerce.SignUpActivity.EMAIL_HINT;
import static com.example.e_commerce.SignUpActivity.EMPTY;

public class LoginActivity extends AppCompatActivity {

    final String FAIL_LOGIN = "Please complete the fields";
    final String INVALID_USER = "Your email or password is wrong, please check them";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView forgetPassword = findViewById(R.id.forgetTxt);
        TextView register = findViewById(R.id.bottom_txt);
        Database eCommerceDatabase = Database.getInstance(this);
        TextInputEditText emailText = findViewById(R.id.emailText);
        TextInputLayout emailLayout = findViewById(R.id.emailLoginLayout);
        TextInputEditText passwordText = findViewById(R.id.password);
        TextInputLayout passwordLayout = findViewById(R.id.passwordLoginLayout);
        CheckBox isRememberMe = findViewById(R.id.rememberMe);
        Button loginButton = findViewById(R.id.signBtn);
        SessionManager rememberMe = new SessionManager(LoginActivity.this, SessionManager.REMEMBER_ME_SESSION);

        if(rememberMe.checkRememberMe()){
            HashMap<String, String> rememberMeDetails = rememberMe.getRememberMeDetailFromSession();
            emailLayout.setHint("");
            emailText.setHint("");
            passwordLayout.setHint("");
            passwordText.setHint("");
            emailText.setText(rememberMeDetails.get(SessionManager.KEY_SESSION_EMAIL));
            passwordText.setText(rememberMeDetails.get(SessionManager.KEY_SESSION_PASSWORD));
        }

        emailText.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                emailLayout.setHint(EMPTY);
            } else if(emailText.getText().toString().equals(EMPTY)) {
                emailLayout.setHint(EMAIL_HINT);
            }
        });

        passwordText.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                passwordLayout.setHint(EMPTY);
            } else if(passwordText.getText().toString().equals(EMPTY)) {
                passwordLayout.setHint(PASSWORD_HINT);
            }
        });

        register.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

        forgetPassword.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
            startActivity(intent);
        });

        loginButton.setOnClickListener(view -> {
            if(emailText.getText().toString().isEmpty() || passwordText.getText().toString().isEmpty()){
                Toast.makeText(LoginActivity.this, FAIL_LOGIN, Toast.LENGTH_LONG).show();
            }
            else{
                if(eCommerceDatabase.customersDAO().checkUser(emailText.getText().toString(), passwordText.getText().toString()) != 0){
                    Customers customer = eCommerceDatabase.customersDAO().getUser(emailText.getText().toString(), passwordText.getText().toString());
                    SessionManager sessionManager = new SessionManager(LoginActivity.this, SessionManager.USER_SESSION);
                    sessionManager.createLoginSession(customer.getCutName(), customer.getUsername(), customer.getPassword(), customer.getGender(),
                            customer.getBirthDate(), customer.getJob());
                    if(isRememberMe.isChecked()){
                        SessionManager rememberMe1 = new SessionManager(LoginActivity.this, SessionManager.REMEMBER_ME_SESSION);
                        rememberMe1.createRememberMeSession(emailText.getText().toString(), passwordText.getText().toString());
                    }
                    Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(LoginActivity.this, INVALID_USER, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
