package com.ziomacki.github.search.model;

import javax.inject.Inject;

public class Search {

    private SearchService searchService;

    @Inject
    public Search(SearchService searchService) {
        this.searchService = searchService;
    }
}
