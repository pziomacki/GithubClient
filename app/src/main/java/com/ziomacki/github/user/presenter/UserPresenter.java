package com.ziomacki.github.user.presenter;

import com.ziomacki.github.user.view.UserView;
import javax.inject.Inject;

public class UserPresenter {

    private UserView userView;

    @Inject
    public UserPresenter() {

    }

    public void attachView(UserView userView) {
        this.userView = userView;
    }

}
