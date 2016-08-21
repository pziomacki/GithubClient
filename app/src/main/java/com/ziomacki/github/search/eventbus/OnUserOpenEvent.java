package com.ziomacki.github.search.eventbus;

public class OnUserOpenEvent implements SearchableItemOpenEvent{

    public final long id;

    public OnUserOpenEvent(long id) {
        this.id = id;
    }
}
