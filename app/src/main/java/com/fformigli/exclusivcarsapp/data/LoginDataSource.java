package com.fformigli.exclusivcarsapp.data;

import com.fformigli.exclusivcarsapp.api.ExclusivCarsApi;
import com.fformigli.exclusivcarsapp.data.model.LoggedInUser;
import com.fformigli.exclusivcarsapp.utils.Constants;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {
    private final String URL = "/auth/login";

    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.API_HOST)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ExclusivCarsApi api = retrofit.create(ExclusivCarsApi.class);

            Call<LoggedInUser> call = api.login();

            call.enqueue(new Callback<LoggedInUser>() {
                @Override
                public void onResponse(Call<LoggedInUser> call, Response<LoggedInUser> response) {
                    if(!response.isSuccessful()){
                        System.out.println(response.code());
                        return;
                    }
                    LoggedInUser user  = response.body();
                    System.out.println("user ------------------------------------------"+ user.getDisplayName());
                }

                @Override
                public void onFailure(Call<LoggedInUser> call, Throwable t) {
                    System.out.println("ploto");
                    System.out.println(call);
                    System.out.println(t);
                }
            });

            LoggedInUser fakeUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            "Jane Doe");

            return new Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}