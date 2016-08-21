package com.ziomacki.github.search.presenter;

import com.ziomacki.github.search.model.Search;
import javax.inject.Inject;

public class SearchPresenter {

    private Search search;

    @Inject
    public SearchPresenter(Search search) {
        this.search = search;
    }
}
