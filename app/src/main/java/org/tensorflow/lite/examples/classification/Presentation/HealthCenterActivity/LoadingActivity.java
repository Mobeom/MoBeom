package org.tensorflow.lite.examples.classification.Presentation.HealthCenterActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.tensorflow.lite.examples.classification.Presentation.HealthCenterActivity.Data.ApiService;
import org.tensorflow.lite.examples.classification.Presentation.HealthCenterActivity.Data.SecretValues;
import org.tensorflow.lite.examples.classification.Presentation.HealthCenterActivity.Data.SelectiveClinicJson;
import org.tensorflow.lite.examples.classification.R;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoadingActivity extends AppCompatActivity {
    public List<SelectiveClinicJson> arrayList;
    public List<SelectiveClinicJson> resource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        SecretValues secretValues = new SecretValues();
        String BASE_URL = secretValues.getUrl();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        Call<List<SelectiveClinicJson>> call = apiService.getSelectiveClinics();
        call.enqueue(new Callback<List<SelectiveClinicJson>>() {
            @Override
            public void onResponse(Call<List<SelectiveClinicJson>> call, Response<List<SelectiveClinicJson>> response) {
                if (response.isSuccessful()) {
                    Log.e("TAG", "Server response code :: " + response.code() + " ");
                    resource = response.body();
                    Intent intent = new Intent(LoadingActivity.this, HealthCenterActivity.class);
                    intent.putExtra("clinics", (Serializable)resource);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "서버 오류 :: 뒤로 가기를 눌러주세요", Toast.LENGTH_LONG).show();
                    System.out.println(response.errorBody());
                    finish();
                }
            }

            @Override
            public void onFailure(Call<List<SelectiveClinicJson>> call, Throwable t) {
                call.cancel();
                t.printStackTrace();
            }
        });
    }
}