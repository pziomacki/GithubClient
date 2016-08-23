package com.ziomacki.github.repository.model

import com.ziomacki.github.R
import com.ziomacki.github.search.eventbus.OnGitRepoOpenEvent
import com.ziomacki.github.search.eventbus.SearchableItemOpenEvent
import spock.lang.Specification

class GitRepoSpec extends Specification {

    def "getIconId returns repo icon"() {
        given:
            GitRepo gitRepo = new GitRepo()
        when:
            int iconId = gitRepo.getItemIconId()
        then:
            iconId == R.drawable.ic_repo
    }

    def "getSearchableItemOpenEvent returns OnGitRepoOpenEvent with proper id"() {
        given:
            GitRepo gitRepo = new GitRepo()
            gitRepo.id = 12
        when:
            SearchableItemOpenEvent event = gitRepo.getSearchableItemOpenEvent()
        then:
            event instanceof OnGitRepoOpenEvent
            OnGitRepoOpenEvent onGitRepoOpenEvent = (OnGitRepoOpenEvent) event
            onGitRepoOpenEvent.id == 12
    }

}
