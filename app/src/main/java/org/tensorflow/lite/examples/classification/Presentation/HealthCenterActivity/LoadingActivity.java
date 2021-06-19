package org.tensorflow.lite.examples.classification.Presentation.HealthCenterActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.tensorflow.lite.examples.classification.Presentation.HealthCenterActivity.Data.ApiService;
import org.tensorflow.lite.examples.classification.Presentation.HealthCenterActivity.Data.SelectiveClinicJson;
import org.tensorflow.lite.examples.classification.R;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoadingActivity extends AppCompatActivity {        //@copyright for 이동우
    public List<SelectiveClinicJson> arrayList;
    public List<SelectiveClinicJson> resource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {        //@copyright for 이동우
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        String BASE_URL = getResources().getString(R.string.BASE_URL);

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
            public void onResponse(Call<List<SelectiveClinicJson>> call, Response<List<SelectiveClinicJson>> response) {        //@copyright for 이동우
                if (response.isSuccessful()) {
                    Log.e("TAG", "Server response code :: " + response.code() + " ");
                    resource = response.body();
                    Intent intent = new Intent(LoadingActivity.this, HealthCenterActivity.class);
                    intent.putExtra("clinics", (Serializable)resource);
                    startActivity(intent);
                    finish();
                } else {

                    Toast.makeText(getApplicationContext(), "서버에 접속할 수 없습니다 \n서버 관리자에게 문의하시길 바랍니다", Toast.LENGTH_LONG).show();
                    System.out.println(response.errorBody());
                    finish();
                }
            }

            @Override
            public void onFailure(Call<List<SelectiveClinicJson>> call, Throwable t) {      //@copyright for 이동우
                call.cancel();
                t.printStackTrace();
            }
        });
    }
}