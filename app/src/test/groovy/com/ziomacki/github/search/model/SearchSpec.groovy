package com.ziomacki.github.search.model

import com.ziomacki.github.repository.model.GitRepo
import com.ziomacki.github.repository.model.GitRepoRepository
import com.ziomacki.github.user.model.User
import com.ziomacki.github.user.model.UserRepository
import rx.Observable
import rx.observers.TestSubscriber
import spock.lang.Specification

class SearchSpec extends Specification {
    def "get search results sorted asc by id"() {
        given:
            TestSubscriber<SearchableItem> testSubscriber = new TestSubscriber<>()
            SearchService searchServiceStub = Stub(SearchService)
            searchServiceStub.searchForRepositories("test") >> Observable.just(getGitRepositoryResults())
            searchServiceStub.searchForUsers("test") >> Observable.just(getUserResults())
            Search sut = new Search(searchServiceStub, getUserRepositoryMock(), getGitRepoRepositoryMock())
        when:
            sut.search("test").subscribe(testSubscriber)
            List<SearchableItem> results = testSubscriber.getOnNextEvents().get(0)
        then:
            results.get(0).getId() == 1
            results.get(1).getId() == 3
            results.get(2).getId() == 4
            results.get(3).getId() == 4
    }

    def "get empty list if no results were found"() {
        given:
            TestSubscriber<SearchableItem> testSubscriber = new TestSubscriber<>()
            SearchService searchServiceStub = Stub(SearchService)
            searchServiceStub.searchForRepositories("test") >> Observable.just(new SearchResults<GitRepo>())
            searchServiceStub.searchForUsers("test") >> Observable.just(new SearchResults<User>())
            Search sut = new Search(searchServiceStub, getUserRepositoryMock(), getGitRepoRepositoryMock())
        when:
            sut.search("test").subscribe(testSubscriber)
            List<SearchableItem> results = testSubscriber.getOnNextEvents().get(0)
        then:
            results.size() == 0
    }

    def "get non empty list if only users were found"() {
        given:
            TestSubscriber<SearchableItem> testSubscriber = new TestSubscriber<>()
            SearchService searchServiceStub = Stub(SearchService)
            searchServiceStub.searchForRepositories("test") >> Observable.just(new SearchResults<GitRepo>())
            searchServiceStub.searchForUsers("test") >> Observable.just(getUserResults())
            Search sut = new Search(searchServiceStub, getUserRepositoryMock(), getGitRepoRepositoryMock())
        when:
            sut.search("test").subscribe(testSubscriber)
            List<SearchableItem> results = testSubscriber.getOnNextEvents().get(0)
        then:
            results.size() == 2
            results.get(0).getId() == 1
            results.get(1).getId() == 4
    }

    def "get non empty list if repositories were found"() {
        given:
            TestSubscriber<SearchableItem> testSubscriber = new TestSubscriber<>()
            SearchService searchServiceStub = Stub(SearchService)
            searchServiceStub.searchForRepositories("test") >> Observable.just(getGitRepositoryResults())
            searchServiceStub.searchForUsers("test") >> Observable.just(new SearchResults<User>())
            Search sut = new Search(searchServiceStub, getUserRepositoryMock(), getGitRepoRepositoryMock())
        when:
            sut.search("test").subscribe(testSubscriber)
            List<SearchableItem> results = testSubscriber.getOnNextEvents().get(0)
        then:
            results.size() == 2
            results.get(0).getId() == 3
            results.get(1).getId() == 4
    }

    SearchResults<GitRepo> getGitRepositoryResults() {
        GitRepo gitRepository1 = new GitRepo()
        GitRepo gitRepository2 = new GitRepo()
        gitRepository1.id = 4
        gitRepository2.id = 3
        SearchResults<GitRepo> results = new SearchResults<>()
        results.items.add(gitRepository1)
        results.items.add(gitRepository2)
        return results
    }

    SearchResults<User> getUserResults() {
        User user1 = new User()
        User user2 = new User()
        user1.id = 4
        user2.id = 1
        SearchResults<User> results = new SearchResults<>()
        results.items.add(user1)
        results.items.add(user2)
        return results
    }

    UserRepository getUserRepositoryMock() {
        return Mock(UserRepository)
    }

    GitRepoRepository getGitRepoRepositoryMock() {
        return Mock(GitRepoRepository)
    }
}
