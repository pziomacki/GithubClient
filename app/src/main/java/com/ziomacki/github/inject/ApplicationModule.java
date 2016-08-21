package com.ziomacki.github.inject;

import android.content.Context;
import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private final Context appContext;

    public ApplicationModule(Context appContext) {
        this.appContext = appContext;
    }

    @Provides
    public Context provideApplicationContext() {
        return appContext;
    }

}
