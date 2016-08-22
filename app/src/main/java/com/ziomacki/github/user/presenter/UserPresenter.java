package com.ziomacki.github.user.presenter;

import android.os.Bundle;
import com.ziomacki.github.user.model.UserRepository;
import com.ziomacki.github.user.view.UserView;
import javax.inject.Inject;

public class UserPresenter {
    public static final String KEY_ID = "KEY_ID";

    private UserView userView;
    private UserRepository userRepository;
    private long userId;

    @Inject
    public UserPresenter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void attachView(UserView userView) {
        this.userView = userView;
    }

    public void readIntentExtras(Bundle bundle) {
        userId = bundle.getLong(KEY_ID);
    }
}
