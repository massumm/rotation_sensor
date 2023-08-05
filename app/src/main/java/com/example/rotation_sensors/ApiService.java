package com.example.rotation_sensors;

import com.google.gson.annotations.SerializedName;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/")
    Call<Void> sendData(@Body DataObject dataObject);
}
