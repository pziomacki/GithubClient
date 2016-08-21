package com.ziomacki.github.search.model;

import java.util.Comparator;

public class SearchableItemComparatorByIdAsc implements Comparator<SearchableItem> {

    @Override
    public int compare(SearchableItem searchableItemOne, SearchableItem searchableItemTwo) {
        if (searchableItemOne.getId() - searchableItemTwo.getId() < 0) {
            return -1;
        } else if (searchableItemOne.getId() - searchableItemTwo.getId() > 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
