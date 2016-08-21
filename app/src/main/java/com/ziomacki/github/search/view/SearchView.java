package com.ziomacki.github.search.view;

import com.ziomacki.github.model.SearchResultItem;
import java.util.List;

public interface SearchView {
    void displayResults(List<SearchResultItem> resultItemList);
    void displayNoResultsMessage();
    void displayErrorMessage();
}
