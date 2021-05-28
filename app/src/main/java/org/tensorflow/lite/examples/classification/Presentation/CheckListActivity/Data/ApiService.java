package org.tensorflow.lite.examples.classification.Presentation.CheckListActivity.Data;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface ApiService{

    @GET("userInfos/{id}")
    Call<UserInfoJson> getUserInfo(@Path("id") String id);

    @POST("userInfos")
    Call<UserInfoJson> postUserInfo(@Body UserInfoJson userInfo);

}
