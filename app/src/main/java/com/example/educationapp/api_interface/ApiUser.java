package com.example.educationapp.api_interface;

import com.example.educationapp.models.introduce.UserLogin;
import com.example.educationapp.models.token.Token;
import com.example.educationapp.models.introduce.UserRegister;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiUser {

    Gson gson = new GsonBuilder()
            .setDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz").create();
    HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    OkHttpClient.Builder okhttpClient = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .connectTimeout(5, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(httpLoggingInterceptor);
    ApiUser apiUser =  new Retrofit.Builder()
            .baseUrl("https://education-mk.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okhttpClient.build())
            .build()
            .create(ApiUser.class);

    @POST("users/SignUp")
    Call<Token> signUp(@Body UserRegister user);

    @POST("users/SignIn")
    Call<Token> signIn(@Body UserLogin userLogin);























}
