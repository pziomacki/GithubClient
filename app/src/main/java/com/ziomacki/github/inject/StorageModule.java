package com.ziomacki.github.inject;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import io.realm.RealmConfiguration;

@Module
public class StorageModule {
    public static final String DB_NAME = "githubdb";

    @Provides
    @ApplicationScope
    public RealmConfiguration provideRealmConfiguration(Context context) {
        return new RealmConfiguration.Builder(context).name(DB_NAME).build();
    }
}