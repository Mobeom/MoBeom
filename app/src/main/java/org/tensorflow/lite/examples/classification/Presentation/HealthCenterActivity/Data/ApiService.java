package org.tensorflow.lite.examples.classification.Presentation.HealthCenterActivity.Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiService{

    @GET("clinics/")
    Call<List<SelectiveClinicJson>> getSelectiveClinics();

    @GET("near/")
    Call<List<SelectiveClinicJson>> getNearbyClinics(@Query("x") double x, @Query("y") double y);
}
