package com.ziomacki.github.inject;

import com.ziomacki.github.search.model.SearchApiService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class SearchModule {

    @Provides
    public SearchApiService provideSearchApiService(Retrofit retrofit) {
        return retrofit.create(SearchApiService.class);
    }

}
