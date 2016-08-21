package com.ziomacki.github.inject;

import com.ziomacki.github.GithubApplication;
import dagger.Component;

@ApplicationScope
@Component(modules = {NetworkModule.class})
public interface ApplicationComponent {
    void inject(GithubApplication application);
}
