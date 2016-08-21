package com.ziomacki.github.search.model;

import com.ziomacki.github.repository.model.GitRepo;
import com.ziomacki.github.user.model.User;
import javax.inject.Inject;
import rx.Observable;

public class SearchService {

    private SearchApiService searchApiService;

    @Inject
    public SearchService(SearchApiService searchApiService) {
        this.searchApiService = searchApiService;
    }

    public Observable<SearchResults<User>> searchForUsers(String query) {
        return searchApiService.searchUsers(query);
    }

    public Observable<SearchResults<GitRepo>> searchForRepositories(String query) {
        return searchApiService.searchRespositories(query);
    }

}
