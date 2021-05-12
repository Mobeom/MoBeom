package org.tensorflow.lite.examples.classification.Presentation.HealthCenterActivity;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.tensorflow.lite.examples.classification.Presentation.HealthCenterActivity.Data.ApiService;
import org.tensorflow.lite.examples.classification.Presentation.HealthCenterActivity.Data.ConstantValue;
import org.tensorflow.lite.examples.classification.Presentation.HealthCenterActivity.Data.SelectiveClinicJson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HealthCenterController implements Callback<List<SelectiveClinicJson>> {

    static final String BASE_URL = ConstantValue.Url.BASE_URL;

    public void start() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        Call<List<SelectiveClinicJson>> call = apiService.getSelectiveClinics();
        call.enqueue(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onResponse(Call<List<SelectiveClinicJson>> call, Response<List<SelectiveClinicJson>> response) {
        if(response.isSuccessful()) {
            List<SelectiveClinicJson> changesList = response.body();
            changesList.forEach(change -> System.out.println(change.getX()));
        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<List<SelectiveClinicJson>> call, Throwable t) {
        t.printStackTrace();
    }
}
