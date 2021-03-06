package com.ziomacki.github.inject;

import com.ziomacki.github.user.view.UserActivity;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = {UserModule.class})
public interface UserComponent {
    void inject(UserActivity userActivity);
}
