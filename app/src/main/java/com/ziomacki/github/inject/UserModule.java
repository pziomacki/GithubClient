package com.ziomacki.github.inject;

import com.ziomacki.github.user.model.UserApiService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class UserModule {

    @Provides
    public UserApiService provideUserApiService(Retrofit retrofit) {
        return retrofit.create(UserApiService.class);
    }
}