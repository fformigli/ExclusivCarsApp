package com.fformigli.exclusivcarsapp.api;

import com.fformigli.exclusivcarsapp.data.model.LoggedInUser;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ExclusivCarsApi {
    @GET("api/auth/login")
    Call<LoggedInUser> login();
}
