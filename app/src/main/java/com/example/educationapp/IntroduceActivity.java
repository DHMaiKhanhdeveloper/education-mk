package com.example.educationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IntroduceActivity extends AppCompatActivity {

    private Button btnLoginIntro, btnSignUpIntro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);

        init();
    }
    private void init(){
        btnLoginIntro = findViewById(R.id.btn_login_intro);
        btnSignUpIntro = findViewById(R.id.btn_signup_intro);

        btnLoginIntro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroduceActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        btnSignUpIntro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroduceActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}