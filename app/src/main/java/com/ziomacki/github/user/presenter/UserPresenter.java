package com.ziomacki.github.user.presenter;

import android.os.Bundle;
import com.ziomacki.github.user.model.User;
import com.ziomacki.github.user.model.UserFetch;
import com.ziomacki.github.user.model.UserRepository;
import com.ziomacki.github.user.view.UserView;
import javax.inject.Inject;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class UserPresenter {
    public static final String KEY_ID = "KEY_ID";

    private UserView userView;
    private UserRepository userRepository;
    private UserFetch userFetch;
    private User user;
    private long userId;
    private CompositeSubscription subscriptions = new CompositeSubscription();

    private Action1<User> fetchUserSuccessful = new Action1<User>() {
        @Override
        public void call(User user) {
            handleFetchedUser(user);
        }
    };

    private Action1<Throwable> fetchUserError = new Action1<Throwable>() {
        @Override
        public void call(Throwable throwable) {
            userView.displayErrorMessage();
        }
    };

    @Inject
    public UserPresenter(UserRepository userRepository, UserFetch userFetch) {
        this.userRepository = userRepository;
        this.userFetch = userFetch;
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

    private void handleLoadedUser(User user) {
        this.user = user;
        displayUserData();
        fetchUserData();
    }

    private void handleFetchedUser(User user) {
        this.user = user;
        displayUserData();
    }

    private void fetchUserData() {
        userFetch.fetchUser(user.login).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(fetchUserSuccessful, fetchUserError);
    }

    public void onStop() {
        subscriptions.clear();
    }

    private void displayUserData() {
        displayUserAvatar();
        if (user.isAllDataFetched) {
            userView.displayName(user.getDisplayName());
            userView.displayFollowersCount(user.followers);
            userView.displayRepositoriesCount(user.publicRepos);
        }
    }

    private void displayUserAvatar() {
        if (isNotEmpty(user.avatarUrl)) {
            userView.displayAvatar(user.avatarUrl);
        } else {
            userView.displayAvatarPlaceholder();
        }
    }

    private boolean isNotEmpty(String string) {
        return string!=null && !string.equals("");
    }
}
