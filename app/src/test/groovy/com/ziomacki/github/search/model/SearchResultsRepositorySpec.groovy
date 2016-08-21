package com.ziomacki.github.search.model

import com.ziomacki.github.component.RealmWrapper
import com.ziomacki.github.repository.model.GitRepo
import com.ziomacki.github.user.model.User
import rx.observers.TestSubscriber
import spock.lang.Specification

class SearchResultsRepositorySpec extends Specification {
    def "get results if only Users are stored in db and GitRepos are not"() {
        given:
            RealmWrapper realmWrapperStub = Stub(RealmWrapper)
            List<User> userResults = new ArrayList<>()
            User user = getNewUser(1)
            userResults.add(user)
            List<GitRepo> repoResults = new ArrayList<>()
            realmWrapperStub.getAllItems(User.class) >> userResults
            realmWrapperStub.getAllItems(GitRepo.class) >> repoResults
            SearchResultsRepository sut = new SearchResultsRepository(realmWrapperStub)
            TestSubscriber<List<User>> testSubscriber = new TestSubscriber<>()
        when:
            sut.readSearchableItemsFromDB().subscribe(testSubscriber)
            List<SearchableItem> results = testSubscriber.getOnNextEvents().get(0)
        then:
            results.size() == 1
            results.get(0).getId() == 1
    }

    def "get results sorted by id"() {
            RealmWrapper realmWrapperStub = Stub(RealmWrapper)
            List<User> userResults = new ArrayList<>()
            User user = getNewUser(3)
            userResults.add(user)

            List<GitRepo> repoResults = new ArrayList<>()
            GitRepo gitRepo1 = getNewGitRepo(10)
            GitRepo gitRepo2 = getNewGitRepo(1)
            repoResults.add(gitRepo1)
            repoResults.add(gitRepo2)

            realmWrapperStub.getAllItems(User.class) >> userResults
            realmWrapperStub.getAllItems(GitRepo.class) >> repoResults
            SearchResultsRepository sut = new SearchResultsRepository(realmWrapperStub)
            TestSubscriber<List<User>> testSubscriber = new TestSubscriber<>()
        when:
            sut.readSearchableItemsFromDB().subscribe(testSubscriber)
            List<SearchableItem> results = testSubscriber.getOnNextEvents().get(0)
        then:
            results.size() == 3
            results.get(0).getId() == 1
            results.get(1).getId() == 3
            results.get(2).getId() == 10
    }

    GitRepo getNewGitRepo(long id) {
        GitRepo repo = new GitRepo()
        repo.id = id
        return repo
    }

    User getNewUser(long id) {
        User user = new User()
        user.id = id
        return user
    }
}
