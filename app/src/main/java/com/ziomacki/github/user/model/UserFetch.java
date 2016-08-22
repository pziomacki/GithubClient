package com.ziomacki.github.user.model;

import javax.inject.Inject;
import rx.Observable;
import rx.functions.Action1;

public class UserFetch {

    private UserService userService;
    private UserRepository userRepository;

    @Inject
    public UserFetch(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    public Observable<User> fetchUser(String userLogin) {
        return userService.fetchUser(userLogin).doOnNext(new Action1<User>() {
            @Override
            public void call(User user) {
                user.isAllDataFetched = true;
                userRepository.copyOrUpdateUser(user);
            }
        });
    }
}
