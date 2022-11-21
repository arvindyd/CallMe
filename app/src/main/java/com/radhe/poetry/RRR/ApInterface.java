package com.radhe.poetry.RRR;

import com.radhe.poetry.Modelss.DeleteResponse;
import com.radhe.poetry.Modelss.GetPoetryResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApInterface {

    @GET("getApi.php")
    Call<GetPoetryResponse>getpoetry();

    @FormUrlEncoded
    @POST("deleteApi.php")
    Call<DeleteResponse>deletepoetry(@Field("poetry_id") String poetry_id );

}
