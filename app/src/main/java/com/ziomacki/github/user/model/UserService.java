package com.ziomacki.github.user.model;

import javax.inject.Inject;
import rx.Observable;

public class UserService {

    private UserApiService userApiService;

    @Inject
    public UserService(UserApiService userApiService) {
        this.userApiService = userApiService;
    }

    public Observable<User> fetchUser(String userLogin) {
        return userApiService.fetchUser(userLogin);
    }
}
