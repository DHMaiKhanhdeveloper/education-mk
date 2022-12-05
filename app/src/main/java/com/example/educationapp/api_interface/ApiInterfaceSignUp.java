package com.example.educationapp.api_interface;

import androidx.annotation.NonNull;

import com.example.educationapp.models.token.Token;
import com.example.educationapp.models.introduce.UserRegister;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterfaceSignUp {

    Gson gson = new GsonBuilder()
            .setDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz").create();

    Interceptor interceptor = new Interceptor() {
        @NonNull
        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request original = chain.request();


            Request.Builder requestBuilder = original.newBuilder()
                    .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJNYWlLaGFuaCIsInN1YiI6IjYzNjhlMTYwM2QxNGJlZjBjZTFkZjE0NCIsImlhdCI6MTY2NzgxNzgyNDc2MywiZXhwIjoxNjY4MDc3MDI0NzYzfQ.nIiOf1lLVeqsGOzNVN6Y0GQ_SRg-ONFeaJqx-TF7Inw");

//            Request request = requestBuilder.build();
            return chain.proceed(requestBuilder.build());
        }
    };
    HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    OkHttpClient.Builder okhttpClient = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .connectTimeout(5, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(interceptor);

    ApiInterfaceSignUp apiInterfaceSignUp =  new Retrofit.Builder()
            .baseUrl("https://education-mk.herokuapp.com/users")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okhttpClient.build())
            .build()
            .create(ApiInterfaceSignUp.class);

    @POST("/SignUp")
    Call<UserRegister> signUp(@Body Token authenticationToken);
}
