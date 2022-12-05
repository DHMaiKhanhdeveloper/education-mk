package com.example.educationapp.intro;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.educationapp.R;
import com.example.educationapp.api_interface.ApiUser;
import com.example.educationapp.data_local.ShareDataLocalManager;
import com.example.educationapp.models.introduce.UserLogin;
import com.example.educationapp.models.token.Token;
import com.example.educationapp.utils_service.UtilService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText edtEmail, edtPassword;
    private String strEmail, strPassword;
    private ProgressBar progressBar;
    private UtilService utilService;
    private UserLogin user;
    private String tokenjwt;
    private boolean passwordVisible;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void init(){
        btnLogin = findViewById(R.id.btn_login);
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);
        progressBar = findViewById(R.id.progress_bar);
        utilService = new UtilService();
        edtPassword.setOnTouchListener((v, event) -> {
            final int Right = 2;
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= edtPassword.getRight() - edtPassword.getCompoundDrawables()[Right].getBounds().width()) {
                    int selection = edtPassword.getSelectionEnd();
                    if (passwordVisible) {
                        edtPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_off_24, 0);
                        edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        passwordVisible = false;
                    } else {
                        edtPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_24, 0);
                        edtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        passwordVisible = true;
                    }
                    edtPassword.setSelection(selection);
                    return true;
                }
            }

            return false;
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                utilService.hideKeyboard(v, LoginActivity.this);
                clickSignIn(v);
            }
        });


    }


    private void clickSignIn(View view) {

        strEmail = edtEmail.getText().toString().trim();
        strPassword = edtPassword.getText().toString().trim();
        if (TextUtils.isEmpty(strEmail)) {
            Toast.makeText(LoginActivity.this, "Vui lòng nhập email của bạn", Toast.LENGTH_SHORT).show();
            utilService.showSnackBar(view,"Vui lòng nhập email của bạn");
            edtEmail.setError("Bắt buộc phải nhập email");
            edtEmail.requestFocus();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(strEmail).matches()) {
            Toast.makeText(LoginActivity.this, "Vui lòng nhập lại email của bạn", Toast.LENGTH_LONG).show();
            utilService.showSnackBar(view,"Vui lòng nhập email của bạn");
            edtEmail.setError("Bắt buộc phải nhập email");
            edtEmail.requestFocus();
        } else if (TextUtils.isEmpty(strPassword)) {
            Toast.makeText(LoginActivity.this, "Vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show();
            utilService.showSnackBar(view,"Vui lòng nhập mật khẩu");
            edtEmail.setError("Bắt buộc phải nhập mật khẩu");
            edtEmail.requestFocus();
        } else {
            CallApiLogin(strEmail,strPassword);
        }




    }

    private void CallApiLogin(String email, String password) {
        user = new UserLogin(email,password);
        ApiUser.apiUser.signIn(user).enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                Token token = response.body();

                tokenjwt = token.getToken();
                Toast.makeText(LoginActivity.this, tokenjwt, Toast.LENGTH_LONG).show();
                ShareDataLocalManager.setJwtToken(tokenjwt);
                Log.e("TAG", tokenjwt);
                progressBar.setVisibility(View.GONE);
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {

            }
        });
    }

    public void onLoginClick(View View){
        startActivity(new Intent(this,RegisterActivity.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);

    }
}