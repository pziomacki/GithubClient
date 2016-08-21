package com.ziomacki.github.search.presenter;

import android.text.TextUtils;
import com.ziomacki.github.search.model.Search;
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
    private String query = "";
    private CompositeSubscription subscriptions = new CompositeSubscription();
    private SearchView searchView;

    private Action1 fetchSuccesfulResponseAction = new Action1<List<SearchableItem>>() {
        @Override
        public void call(List<SearchableItem> searchResults) {
            if (searchResults.size() > 0) {
                displayResults(searchResults);
            } else {
                searchView.displayNoResultsMessage();
            }
        }
    };

    @Inject
    public SearchPresenter(Search search) {
        this.search = search;
    }

    public void attachView(SearchView searchView) {
        this.searchView = searchView;
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
        Subscription subscription = search.search(query)
                .subscribeOn(Schedulers.io())
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
}
