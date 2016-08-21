package com.ziomacki.github.search.model;

import javax.inject.Inject;

public class SearchService {

    private SearchApiService searchApiService;

    @Inject
    public SearchService(SearchApiService searchApiService) {
        this.searchApiService = searchApiService;
    }

}
