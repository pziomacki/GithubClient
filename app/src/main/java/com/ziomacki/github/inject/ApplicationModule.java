package com.ziomacki.github.inject;

import android.content.Context;
import com.ziomacki.github.GithubBusIndex;
import com.ziomacki.github.component.ResourceProvider;
import org.greenrobot.eventbus.EventBus;
import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private final Context appContext;
    private final EventBus eventBus;

    public ApplicationModule(Context appContext) {
        this.appContext = appContext;
        setupBusIndex();
        eventBus = EventBus.getDefault();
    }

    private void setupBusIndex() {
        EventBus.builder().addIndex(new GithubBusIndex()).installDefaultEventBus();
    }

    @Provides
    public Context provideApplicationContext() {
        return appContext;
    }

    @Provides
    @ApplicationScope
    public EventBus provideEventBus() {
        return eventBus;
    }

    @Provides
    public ResourceProvider provideResourceProvider() {
        return new ResourceProvider(appContext);
    }
}
