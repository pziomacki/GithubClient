package com.ziomacki.github.repository.model

import com.ziomacki.github.component.RealmWrapper
import spock.lang.Specification

class GitRepoRepositorySpec extends Specification {

    def "store new results list"() {
        given:
            RealmWrapper realmWrapperMock = Mock(RealmWrapper)
            GitRepoRepository sut = new GitRepoRepository(realmWrapperMock)
            List<GitRepo> list = new ArrayList<GitRepo>()
        when:
            sut.deleteOldAndStoreNewList(list)
        then:
            1 * realmWrapperMock.deleteOldAndStoreNewList(GitRepo.class, list)
    }

    def "dont store null list"() {
        given:
            RealmWrapper realmWrapperMock = Mock(RealmWrapper)
            GitRepoRepository sut = new GitRepoRepository(realmWrapperMock)
            List<GitRepo> list = null
        when:
            sut.deleteOldAndStoreNewList(list)
        then:
            thrown IllegalArgumentException
    }

}
