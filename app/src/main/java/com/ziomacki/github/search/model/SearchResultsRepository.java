package com.ziomacki.github.search.model;

import com.ziomacki.github.component.RealmWrapper;
import com.ziomacki.github.repository.model.GitRepo;
import com.ziomacki.github.user.model.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscriber;

public class SearchResultsRepository {

    private RealmWrapper realmWrapper;

    @Inject
    public SearchResultsRepository(RealmWrapper realmWrapper) {
        this.realmWrapper = realmWrapper;
    }

    public Observable<List<SearchableItem>> readSearchableItemsFromDB() {
        return Observable.create(new Observable.OnSubscribe<List<SearchableItem>>() {
            @Override
            public void call(Subscriber<? super List<SearchableItem>> subscriber) {
                List<SearchableItem> results = new ArrayList();
                results.addAll(realmWrapper.getAllItems(User.class));
                results.addAll(realmWrapper.getAllItems(GitRepo.class));
                Collections.sort(results, new SearchableItemComparatorByIdAsc());
                subscriber.onNext(results);
            }
        });
    }
}
