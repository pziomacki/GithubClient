package com.ziomacki.github.user.presenter;

import android.os.Bundle;
import com.ziomacki.github.user.model.User;
import com.ziomacki.github.user.model.UserRepository;
import com.ziomacki.github.user.view.UserView;
import javax.inject.Inject;
import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

public class UserPresenter {
    public static final String KEY_ID = "KEY_ID";

    private UserView userView;
    private UserRepository userRepository;
    private User user;
    private long userId;
    private CompositeSubscription subscriptions = new CompositeSubscription();

    @Inject
    public UserPresenter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void attachView(UserView userView) {
        this.userView = userView;
    }

    public void readIntentExtras(Bundle bundle) {
        userId = bundle.getLong(KEY_ID);
        loadUserFromDB();
    }

    private void loadUserFromDB() {
        Subscription subscription = userRepository.getUserByIdObservable(userId).subscribe(new Action1<User>() {
            @Override
            public void call(User user) {
                handleLoadedUser(user);
            }
        });
        subscriptions.add(subscription);
    }

    public void handleLoadedUser(User user) {
        this.user = user;
        userView.displayName(user.getDisplayName());
    }

    public void onStop() {
        subscriptions.clear();
    }
}
