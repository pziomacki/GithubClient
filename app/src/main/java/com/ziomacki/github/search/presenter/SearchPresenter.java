package com.ziomacki.github.search.presenter;

import com.ziomacki.github.search.model.Search;
import com.ziomacki.github.search.model.SearchableItem;
import java.util.List;
import javax.inject.Inject;
import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

public class SearchPresenter {

    private Search search;
    private String query = "";
    private CompositeSubscription subscriptions = new CompositeSubscription();

    @Inject
    public SearchPresenter(Search search) {
        this.search = search;
    }

    public void searchAction(String query) {
        this.query = query;
        search();
    }

    private void search() {
        Subscription subscription = search.search(query).subscribe(
                new Action1<List<SearchableItem>>() {
                    @Override
                    public void call(List<SearchableItem> searchableItems) {
                        //TODO: implement
                    }
                },
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
