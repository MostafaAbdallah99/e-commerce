package com.example.e_commerce;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import Database.Customers;
import Database.Database;

public class CompleteSignUpActivity extends AppCompatActivity {
    String birthDate;
    final String FAILED_SIGN_UP = "Failed Sign Up";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_signup);

        Bundle extras = getIntent().getExtras();
        Spinner genderSpinner = findViewById(R.id.genderSpinner);
        String[] genders = getResources().getStringArray(R.array.gender);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, genders);
        Customers customer = new Customers();
        CalendarView calendarView = findViewById(R.id.calenderBirthDate);
        Button signUpButton = findViewById(R.id.compSignUp);
        TextView loginText = findViewById(R.id.compLogin);
        Database eCommerceDatabase = Database.getInstance(getApplicationContext());

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(adapter);
        birthDate = "";
        customer.setUsername(extras.getString(SignUpActivity.EMAIL_KEY));
        customer.setJob(extras.getString(SignUpActivity.JOB_KEY));
        customer.setPassword(extras.getString(SignUpActivity.PASSWORD_KEY));
        customer.setCutName(extras.getString(SignUpActivity.CUSTOMER_NAME_KEY));
        customer.setGender(genderSpinner.getSelectedItem().toString());
        customer.setFirstSecurityQuestion("Home");
        customer.setSecondSecurityQuestion("Home");

        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            birthDate = dayOfMonth + "/" + (month + 1) + "/" + year;
            customer.setBirthDate(birthDate);
        });

        signUpButton.setOnClickListener(view -> {
            try {
                eCommerceDatabase.customersDAO().insertCustomer(customer);
                Intent sign = new Intent(CompleteSignUpActivity.this , SecurityQuestionsActivity.class);
                sign.putExtra(SignUpActivity.EMAIL_KEY, customer.getUsername());
                startActivity(sign);
            }
            catch (Exception e){
                Toast.makeText(getApplicationContext(),FAILED_SIGN_UP, Toast.LENGTH_LONG).show();
            }
        });

        loginText.setOnClickListener(v -> {
            Intent sign = new Intent(CompleteSignUpActivity.this , LoginActivity.class);
            startActivity(sign);
        });
    }
}