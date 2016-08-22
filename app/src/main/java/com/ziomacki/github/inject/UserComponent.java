package com.ziomacki.github.inject;

import com.ziomacki.github.user.view.UserActivity;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent
public interface UserComponent {
    void inject(UserActivity userActivity);
}
