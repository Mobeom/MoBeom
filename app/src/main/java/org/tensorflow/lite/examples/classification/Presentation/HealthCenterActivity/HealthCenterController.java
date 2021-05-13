package org.tensorflow.lite.examples.classification.Presentation.HealthCenterActivity;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.google.android.gms.maps.MapFragment;
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
    public List<SelectiveClinicJson> resource;
    public MapFragment mapFragment;

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

    @Override
    public void onResponse(Call<List<SelectiveClinicJson>> call, Response<List<SelectiveClinicJson>> response) {
        if(response.isSuccessful()) {
            Log.e("TAG", response.code() + " ");
            resource = response.body();
            HealthCenterActivity hc = new HealthCenterActivity();
            hc.openMap(mapFragment);
        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<List<SelectiveClinicJson>> call, Throwable t) {
        call.cancel();
        t.printStackTrace();
    }
}
