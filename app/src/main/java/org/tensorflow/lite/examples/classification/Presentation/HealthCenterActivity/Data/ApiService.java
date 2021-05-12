package org.tensorflow.lite.examples.classification.Presentation.HealthCenterActivity.Data;

import org.tensorflow.lite.examples.classification.Presentation.HealthCenterActivity.Data.ConstantValue;
import org.tensorflow.lite.examples.classification.Presentation.HealthCenterActivity.Data.SelectiveClinicJson;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService{

    @GET(ConstantValue.Url.GET_URL)
    Call<List<SelectiveClinicJson>> getSelectiveClinics();

}
