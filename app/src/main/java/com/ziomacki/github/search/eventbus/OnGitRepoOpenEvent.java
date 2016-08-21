package com.ziomacki.github.search.eventbus;

public class OnGitRepoOpenEvent implements SearchableItemOpenEvent {

    public long id;

    public OnGitRepoOpenEvent(long id) {
        this.id = id;
    }
}
