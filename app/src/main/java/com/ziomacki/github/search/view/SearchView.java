package com.ziomacki.github.search.view;

import com.ziomacki.github.search.model.SearchableItem;
import java.util.List;

public interface SearchView {
    void displayResults(List<SearchableItem> resultItemList);
    void displayNoResultsMessage();
    void displayErrorMessage();
    void displayDataLoading();
    void hideDataLoading();
}
