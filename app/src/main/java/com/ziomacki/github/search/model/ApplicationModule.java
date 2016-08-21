package com.ziomacki.github.search.model;

import android.content.Context;
import org.greenrobot.eventbus.EventBus;
import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private final Context appContext;
    private final EventBus eventBus;

    public ApplicationModule(Context appContext) {
        this.appContext = appContext;
        eventBus = EventBus.getDefault();
    }

    @Provides
    public Context provideApplicationContext() {
        return appContext;
    }

}
