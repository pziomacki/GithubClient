package com.ziomacki.github.search.model;

import com.ziomacki.github.search.eventbus.SearchableItemOpenEvent;

public interface SearchableItem {

    String getNameForList();
    long getId();
    SearchableItemOpenEvent getSearchableItemOpenEvent();
    int getItemIconId();

}
