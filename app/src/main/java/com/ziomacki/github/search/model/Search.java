package com.ziomacki.github.search.model;

import com.ziomacki.github.repository.model.GitRepo;
import com.ziomacki.github.repository.model.GitRepoRepository;
import com.ziomacki.github.user.model.User;
import com.ziomacki.github.user.model.UserRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func2;

public class Search {

    private SearchService searchService;
    private UserRepository userRepository;
    private GitRepoRepository gitRepoRepository;

    @Inject
    public Search(SearchService searchService, UserRepository userRepository, GitRepoRepository gitRepoRepository) {
        this.searchService = searchService;
        this.userRepository = userRepository;
        this.gitRepoRepository = gitRepoRepository;
    }

    public Observable<List<SearchableItem>> search(String query) {
        Observable<SearchResults<User>> userSearchObservable = searchService.searchForUsers(query)
                .doOnNext(new StoreUsers());
        Observable<SearchResults<GitRepo>> repoSearchObservable = searchService.searchForRepositories(query)
                .doOnNext(new StoreGitRepos());
        return Observable.zip(userSearchObservable, repoSearchObservable, new ZipSearchResults());
    }

    private class ZipSearchResults
            implements Func2<SearchResults<User>, SearchResults<GitRepo>, List<SearchableItem>> {

        @Override
        public List<SearchableItem> call(SearchResults<User> userSearchResults,
                                         SearchResults<GitRepo> gitRepositorySearchResults) {
            List<SearchableItem> result = new ArrayList();
            result.addAll(userSearchResults.items);
            result.addAll(gitRepositorySearchResults.items);
            Collections.sort(result, new SearchableItemComparatorByIdAsc());
            return result;
        }
    }

    private class StoreUsers implements Action1<SearchResults<User>> {
        @Override
        public void call(SearchResults<User> userSearchResults) {
            userRepository.deleteOldAndStoreNewUserList(userSearchResults.items);
        }
    }

    private class StoreGitRepos implements Action1<SearchResults<GitRepo>> {
        @Override
        public void call(SearchResults<GitRepo> gitRepoSearchResults) {
            gitRepoRepository.deleteOldAndStoreNewList(gitRepoSearchResults.items);
        }
    }
}
