package com.ziomacki.github.search.model;

import com.ziomacki.github.repository.model.GitRepository;
import com.ziomacki.github.user.model.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import rx.functions.Func2;

public class Search {

    private SearchService searchService;

    @Inject
    public Search(SearchService searchService) {
        this.searchService = searchService;
    }

    public Observable<List<SearchableItem>> search(String query) {
        final Observable<SearchResults<User>> userSearchObservable = searchService.searchForUsers(query);
        Observable<SearchResults<GitRepository>> repoSearchObservable = searchService.searchForRepositories(query);
        return Observable.zip(userSearchObservable, repoSearchObservable, new ZipSearchResults());
    }

    private static class ZipSearchResults
            implements Func2<SearchResults<User>, SearchResults<GitRepository>, List<SearchableItem>> {

        @Override
        public List<SearchableItem> call(SearchResults<User> userSearchResults,
                                         SearchResults<GitRepository> gitRepositorySearchResults) {
            List<SearchableItem> result = new ArrayList();
            result.addAll(userSearchResults.items);
            result.addAll(gitRepositorySearchResults.items);
            Collections.sort(result, new SearchableItemComparatorByIdAsc());
            return result;
        }

    }
}
