package com.ziomacki.github.search.model;

import com.ziomacki.github.repository.model.GitRepo;
import com.ziomacki.github.user.model.User;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface SearchApiService {
    @GET("search/repositories")
    Observable<SearchResults<GitRepo>> searchRespositories(@Query("q") String query);
    @GET("search/users")
    Observable<SearchResults<User>> searchUsers(@Query("q") String query);
}
