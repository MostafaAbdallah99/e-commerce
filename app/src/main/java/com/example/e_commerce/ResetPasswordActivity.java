package com.example.e_commerce;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import Database.Database;

public class ResetPasswordActivity extends AppCompatActivity {

    final String ENTER_PASSWORD = "Please, enter your new password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        Database eCommerceDatabase = Database.getInstance(ResetPasswordActivity.this);
        EditText newPassword = findViewById(R.id.newPassword);
        Button resetButton = findViewById(R.id.resetPasswordBtn2);
        Bundle bundle = getIntent().getExtras();

        resetButton.setOnClickListener(view -> {
            if(newPassword.getText().toString().isEmpty()){
                Toast.makeText(ResetPasswordActivity.this, ENTER_PASSWORD, Toast.LENGTH_LONG).show();
            }
            else{
                eCommerceDatabase.customersDAO().updatePassword(newPassword.getText().toString(), bundle.getString(SignUpActivity.EMAIL_KEY));
                Intent intent = new Intent(ResetPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}