package com.ziomacki.github;

import android.app.Application;
import com.ziomacki.github.inject.ApplicationComponent;
import com.ziomacki.github.inject.ApplicationModule;
import com.ziomacki.github.inject.DaggerApplicationComponent;

public class GithubApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initApplicationComponent();
    }

    private void initApplicationComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

}
