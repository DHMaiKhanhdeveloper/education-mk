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
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.educationapp.R;
import com.example.educationapp.api_interface.ApiUser;
import com.example.educationapp.data_local.ShareDataLocalManager;
import com.example.educationapp.models.token.Token;
import com.example.educationapp.models.introduce.UserRegister;
import com.example.educationapp.utils_service.UtilService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText edtName,  edtEmail, edtPhone, edtPassword, edtConfirmPassword;
    private String strName, strEmail, strJob, strPhone, strPassword , strConfirmPassword;
    private Button btnRegister;
    private String mobileRegex;
    private Matcher mobileMatcher;
    private Pattern mobilePattern;
    private RadioButton radiobtnJobSelected;
    private RadioGroup radioGroupJob;
    private ProgressBar progressBar;
    private UtilService utilService;
    private boolean passwordVisible;
    private ShareDataLocalManager shareDataLocalManager;


    private String tokenjwt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        changeStatusBarColor();
        init();

    }
    @SuppressLint("ClickableViewAccessibility")
    private void init(){
        edtName = findViewById(R.id.edt_name);
        radioGroupJob = findViewById(R.id.radio_group_register_job);
        radioGroupJob.clearCheck();
        edtEmail = findViewById(R.id.edt_email);
        edtPhone = findViewById(R.id.edt_phone);
        edtPassword = findViewById(R.id.edt_password);
        edtConfirmPassword = findViewById(R.id.edt_confirm_password);
        btnRegister =  findViewById(R.id.btn_register);
        progressBar = findViewById(R.id.progress_bar);
        utilService = new UtilService();
        edtPassword.setOnTouchListener((v, event) -> {
            final int Right = 2;
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= edtPassword.getRight() - edtPassword.getCompoundDrawables()[Right].getBounds().width()) {
                    @SuppressLint("ClickableViewAccessibility") int selection = edtPassword.getSelectionEnd();
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
        edtConfirmPassword.setOnTouchListener((v, event) -> {
            final int Right = 2;
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= edtConfirmPassword.getRight() - edtConfirmPassword.getCompoundDrawables()[Right].getBounds().width()) {
                    @SuppressLint("ClickableViewAccessibility") int selection = edtConfirmPassword.getSelectionEnd();
                    if (passwordVisible) {
                        edtConfirmPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_off_24, 0);
                        edtConfirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        passwordVisible = false;
                    } else {
                        edtConfirmPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_24, 0);
                        edtConfirmPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        passwordVisible = true;
                    }
                    edtConfirmPassword.setSelection(selection);
                    return true;
                }
            }

            return false;
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                utilService.hideKeyboard(v, RegisterActivity.this);
                clickSignUp(v);
            }
        });
    }
    private void clickSignUp(View v) {
        int selectedJobId = radioGroupJob.getCheckedRadioButtonId();
        radiobtnJobSelected = findViewById(selectedJobId);
        strJob = radiobtnJobSelected.getText().toString();
        strName = edtName.getText().toString().trim();
        strJob = radiobtnJobSelected.getText().toString().trim();
        strEmail = edtEmail.getText().toString().trim();
        strPhone = edtPhone.getText().toString().trim();
        strPassword = edtPassword.getText().toString().trim();
        strConfirmPassword = edtConfirmPassword.getText().toString().trim();
        Toast.makeText(RegisterActivity.this,strPassword,Toast.LENGTH_LONG).show();

        mobileRegex = "[0][0-9]{9}";
        mobilePattern = Pattern.compile(mobileRegex); // x??c ?????nh m???u di ?????ng
        mobileMatcher = mobilePattern.matcher(strPhone);




        if (TextUtils.isEmpty(strName)) {
            Toast.makeText(RegisterActivity.this, "Vui l??ng nh???p ?????y ????? h??? v?? t??n c???a b???n", Toast.LENGTH_LONG).show();
            edtName.setError("B???t bu???c nh???p h??? v?? t??n");
            utilService.showSnackBar(v,"Vui l??ng nh???p ?????y ????? h??? v?? t??n c???a b???n");
            edtName.requestFocus();// yeu cau nhap lai

        } else if (TextUtils.isEmpty((strEmail))) {
            Toast.makeText(RegisterActivity.this, "Vui l??ng nh???p email c???a b???n", Toast.LENGTH_LONG).show();
            edtEmail.setError("B???t bu???c nh???p email");
            utilService.showSnackBar(v,"Vui l??ng nh???p email c???a b???n");
            edtEmail.requestFocus();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(strEmail).matches()) { // kh??c true
            Toast.makeText(RegisterActivity.this, "Vui l??ng nh???p l???i email c???a b???n", Toast.LENGTH_LONG).show();
            edtEmail.setError(" email kh??ng h???p l???");
            utilService.showSnackBar(v,"Vui l??ng nh???p email c???a b???n");
            edtEmail.requestFocus();

        }  else if (radioGroupJob.getCheckedRadioButtonId() == -1) {
            Toast.makeText(RegisterActivity.this, "Vui l??ng ch???n gi???i t??nh c???a b???n", Toast.LENGTH_LONG).show();
            radiobtnJobSelected.setError("B???t bu???c ph???i ch???n gi???i t??nh");
            utilService.showSnackBar(v,"Vui l??ng ch???n gi???i t??nh c???a b???n");
            radiobtnJobSelected.requestFocus();
        }
        else if (TextUtils.isEmpty(strPhone)) {
            Toast.makeText(RegisterActivity.this, "Vui l??ng nh???p s??? ??i???n tho???i c???a b???n ", Toast.LENGTH_LONG).show();
            edtPhone.setError("B???t bu???c nh???p s??? ??i???n tho???i");
            utilService.showSnackBar(v,"Vui l??ng nh???p s??? ??i???n tho???i c???a b???n");
            edtPhone.requestFocus();
        } else if (strPhone.length() != 10) {
            Toast.makeText(RegisterActivity.this, "Vui l??ng nh???p s??? ??i???n tho???i c???a b???n ", Toast.LENGTH_LONG).show();
            edtPhone.setError("??i???n tho???i di ?????ng ph???i c?? 10 ch??? s???");
            utilService.showSnackBar(v,"Vui l??ng nh???p s??? ??i???n tho???i c???a b???n");
            edtPhone.requestFocus();
        } else if (!mobileMatcher.find()) {
            Toast.makeText(RegisterActivity.this, "Vui l??ng nh???p s??? ??i???n tho???i c???a b???n ", Toast.LENGTH_LONG).show();
            edtPhone.setError("??i???n tho???i di ?????ng kh??ng h???p l???");
            utilService.showSnackBar(v,"Vui l??ng nh???p s??? ??i???n tho???i c???a b???n");
            edtPhone.requestFocus();
        }   if (TextUtils.isEmpty(strEmail)) {
            Toast.makeText(RegisterActivity.this, "Vui l??ng nh???p email c???a b???n", Toast.LENGTH_SHORT).show();
            edtEmail.setError("B???t bu???c ph???i nh???p email");
            utilService.showSnackBar(v,"Vui l??ng nh???p email c???a b???n");
            edtEmail.requestFocus();
        }  else if (TextUtils.isEmpty(strPassword)) {
            Toast.makeText(RegisterActivity.this, "Vui l??ng nh???p m???t kh???u", Toast.LENGTH_SHORT).show();
            edtPassword.setError("B???t bu???c ph???i nh???p m???t kh???u");
            utilService.showSnackBar(v,"Vui l??ng nh???p m???t kh???u");
            edtPassword.requestFocus();
        } else if (strPassword.length() < 6) {
            Toast.makeText(RegisterActivity.this, "M???t kh???u ph???i c?? ??t nh???t 6 k?? t??? ", Toast.LENGTH_LONG).show();
            edtPassword.setError("M???t kh???u qu?? y???u");
            utilService.showSnackBar(v,"M???t kh???u ph???i c?? ??t nh???t 6 k?? t???");
            edtPassword.requestFocus();
        } else if (TextUtils.isEmpty(strConfirmPassword)) {
            Toast.makeText(RegisterActivity.this, "Vui l??ng nh???p l???i m???t kh???u ", Toast.LENGTH_LONG).show();
            edtConfirmPassword.setError("B???t bu???c ph???i nh???p m???t kh???u x??c nh???n");
            utilService.showSnackBar(v,"Vui l??ng nh???p l???i m???t kh???u");
            edtConfirmPassword.requestFocus();
        } else if (!strPassword.equals(strConfirmPassword)) {
            Toast.makeText(RegisterActivity.this, "Vui l??ng nh???p c??ng m???t m???t kh???u ", Toast.LENGTH_LONG).show();
            edtConfirmPassword.setError("B???t bu???c ph???i nh???p m???t kh???u x??c nh???n");
            utilService.showSnackBar(v,"Vui l??ng nh???p c??ng m???t m???t kh???u ");
            edtPassword.clearComposingText();
            edtConfirmPassword.clearComposingText();

        } else {
            callApiRegister(strName,strJob,strEmail,strPhone,strPassword);

        }

    }

    private void callApiRegister(String name, String job, String email, String phone, String password) {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://education-mk.herokuapp.com/")
//
//                .addConverterFactory(GsonConverterFactory.create())
//
//                .build();
//
//        ApiSignUp apiSignUp = retrofit.create(ApiSignUp.class);
        UserRegister user = new UserRegister(name,job,email,phone,password);
        progressBar.setVisibility(View.VISIBLE);
        Log.e("TAG",user.toString());
        Toast.makeText(RegisterActivity.this,user.toString(),Toast.LENGTH_LONG).show();
//        Call<Token> call = apiSignUp.signUp(user);
        ApiUser.apiUser.signUp(user).enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {


                    Token token = response.body();

                        tokenjwt = token.getToken();
                        Toast.makeText(RegisterActivity.this, tokenjwt, Toast.LENGTH_LONG).show();
                        ShareDataLocalManager.setJwtToken(tokenjwt);
                        Log.e("TAG", tokenjwt);
                        progressBar.setVisibility(View.GONE);

                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("user_register",user);
                        intent.putExtras(bundle);
                        startActivity(intent);


            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
//        ApiSignUp.apiSignUp.signUp(user).enqueue(new Callback<AuthenticationToken>() {
//            @Override
//            public void onResponse(Call<AuthenticationToken> call, Response<AuthenticationToken> response) {
//
//                AuthenticationToken authenticationToken = response.body();
//                Log.e("TAG",response.body().toString());
//                    token = response.body().getToken();
//                    Toast.makeText(RegisterActivity.this,token,Toast.LENGTH_LONG).show();
//                    Log.e("TAG",token);
//
//            }
//
//            @Override
//            public void onFailure(Call<AuthenticationToken> call, Throwable t) {
//                Toast.makeText(RegisterActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
//            }
//        });

    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }

    public void onLoginClick(View view){
        startActivity(new Intent(this,LoginActivity.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);

    }
}