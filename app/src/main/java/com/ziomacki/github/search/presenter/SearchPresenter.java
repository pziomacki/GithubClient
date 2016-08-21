package com.ziomacki.github.search.presenter;

import android.os.Bundle;
import android.text.TextUtils;
import com.ziomacki.github.search.model.Search;
import com.ziomacki.github.search.model.SearchResultsRepository;
import com.ziomacki.github.search.model.SearchSavedInstanceHelper;
import com.ziomacki.github.search.model.SearchableItem;
import com.ziomacki.github.search.view.SearchView;
import java.util.List;
import javax.inject.Inject;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class SearchPresenter {
    private Search search;
    private CompositeSubscription subscriptions = new CompositeSubscription();
    private SearchView searchView;
    private SearchResultsRepository searchResultsRepository;
    private String query = "";
    private boolean isSearchSuccessful = false;

    private Action1 fetchSuccesfulResponseAction = new Action1<List<SearchableItem>>() {
        @Override
        public void call(List<SearchableItem> searchResults) {
            if (searchResults.size() > 0) {
                isSearchSuccessful = true;
                displayResults(searchResults);
            } else {
                searchView.displayNoResultsMessage();
            }
            searchView.hideDataLoading();
        }
    };

    private Action1 readResultsFromDB = new Action1<List<SearchableItem>>() {
        @Override
        public void call(List<SearchableItem> searchResults) {
            if (searchResults.size() > 0) {
                displayResults(searchResults);
            }
        }
    };

    @Inject
    public SearchPresenter(Search search, SearchResultsRepository searchResultsRepository) {
        this.search = search;
        this.searchResultsRepository = searchResultsRepository;
    }

    public void attachView(SearchView searchView) {
        this.searchView = searchView;
    }

    public void restoreState(Bundle savedInstance) {
        if (savedInstance != null) {
            SearchSavedInstanceHelper searchSavedInstanceHelper = new SearchSavedInstanceHelper();
            query = searchSavedInstanceHelper.getQuery(savedInstance);
            isSearchSuccessful = searchSavedInstanceHelper.isSearchMade(savedInstance);
            readResultsFromDBOnRestore();
        }
    }

    public void onSaveInstance(Bundle outState) {
        SearchSavedInstanceHelper searchSavedInstanceHelper = new SearchSavedInstanceHelper();
        searchSavedInstanceHelper.saveInstance(outState, query, isSearchSuccessful);
    }

    private void readResultsFromDBOnRestore() {
        if (isSearchSuccessful) {
            Subscription subscription = searchResultsRepository.readSearchableItemsFromDB()
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(readResultsFromDB);
            subscriptions.add(subscription);
        }
    }

    public void searchAction(String query) {
        if (!TextUtils.isEmpty(query)) {
            this.query = query;
            search();
        }
    }

    private void displayResults(List<SearchableItem> resultItemList) {
        searchView.displayResults(resultItemList);
    }

    private void search() {
        Subscription subscription = search.search(query).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                fetchSuccesfulResponseAction,
                new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        //TODO: implement
                    }
                });
        subscriptions.add(subscription);
    }

    public void onStop() {
        subscriptions.clear();
    }

    public void refresh() {
        if (!TextUtils.isEmpty(query)) {
            searchView.displayDataLoading();
            search();
        } else {
            searchView.hideDataLoading();
        }
    }
}
