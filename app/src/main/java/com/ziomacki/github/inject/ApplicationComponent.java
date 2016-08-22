package com.ziomacki.github.inject;

import com.ziomacki.github.GithubApplication;
import dagger.Component;

@ApplicationScope
@Component(modules = {ApplicationModule.class, NetworkModule.class, StorageModule.class})
public interface ApplicationComponent {
    void inject(GithubApplication application);

    SearchComponent searchComponent(SearchModule searchModule);
    UserComponent userComponent();
}
