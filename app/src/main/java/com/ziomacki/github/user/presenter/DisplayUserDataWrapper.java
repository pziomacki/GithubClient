package com.ziomacki.github.user.presenter;

import android.text.TextUtils;
import com.ziomacki.github.user.model.User;
import com.ziomacki.github.user.view.UserView;
import javax.inject.Inject;

public class DisplayUserDataWrapper {

    @Inject
    DisplayUserDataWrapper() {}

    void displayUserData(UserView userView, User user) {
        displayUserAvatar(userView, user.avatarUrl);
        userView.displayLogin(user.login);
        displayDetaisIfDownloaded(userView, user);
    }

    private void displayDetaisIfDownloaded(UserView userView, User user) {
        if (user.isAllDataFetched) {
            userView.displayName(user.name);
            userView.displayFollowersCount(user.followers);
            userView.displayRepositoriesCount(user.publicRepos);
        }
    }

    private void displayUserAvatar(UserView userView, String avatarUrl) {
        if (!TextUtils.isEmpty(avatarUrl)) {
            userView.displayAvatar(avatarUrl);
        } else {
            userView.displayAvatarPlaceholder();
        }
    }
}
