package com.ziomacki.github.user.presenter;

import com.ziomacki.github.user.model.UserRepository;
import com.ziomacki.github.user.view.UserView;
import javax.inject.Inject;

public class UserPresenter {

    private UserView userView;
    private UserRepository userRepository;

    @Inject
    public UserPresenter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void attachView(UserView userView) {
        this.userView = userView;
    }

}
