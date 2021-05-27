package org.tensorflow.lite.examples.classification.Presentation.CheckListActivity.Data;

import org.tensorflow.lite.examples.classification.Presentation.CheckListActivity.Data.SelectiveCheckListJson;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiService{

    @GET("clinics/")
    Call<List<SelectiveCheckListJson>> getSelectiveClinics();

}
