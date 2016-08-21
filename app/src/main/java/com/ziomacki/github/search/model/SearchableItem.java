package com.ziomacki.github.search.model;

import com.ziomacki.github.search.eventbus.SearchableItemOpenEvent;

public interface SearchableItem {

    String getDisplayName();
    long getId();
    SearchableItemOpenEvent getSearchableItemOpenEvent();

}
