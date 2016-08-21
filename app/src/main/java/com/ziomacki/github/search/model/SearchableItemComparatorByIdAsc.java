package com.ziomacki.github.search.model;

import java.util.Comparator;

public class SearchableItemComparatorByIdAsc implements Comparator<SearchableItem> {

    @Override
    public int compare(SearchableItem searchableItemOne, SearchableItem searchableItemTwo) {
        return searchableItemOne.getId() - searchableItemTwo.getId();
    }
}
