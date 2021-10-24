package com.example.e_commerce;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread background = new Thread(() -> {
            try {
                Thread.sleep(2*1000);
                Intent intent = new Intent(SplashScreenActivity.this,EShoppingListActivity.class);
                startActivity(intent);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
            }
        });
        background.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
