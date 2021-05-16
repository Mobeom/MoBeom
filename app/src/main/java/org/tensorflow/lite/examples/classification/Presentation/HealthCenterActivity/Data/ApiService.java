package org.tensorflow.lite.examples.classification.Presentation.HealthCenterActivity.Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiService{

    @GET("clinics/")
    Call<List<SelectiveClinicJson>> getSelectiveClinics();

}
