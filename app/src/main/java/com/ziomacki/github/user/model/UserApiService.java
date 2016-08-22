package com.ziomacki.github.user.model;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface UserApiService {

    @GET("users/{user}")
    Observable<User> fetchUser(@Path("user") String userLogin);

}
