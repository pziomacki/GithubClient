package com.ziomacki.github.search.model;

import android.os.Bundle;

public class SearchSavedInstanceHelper {

    private static final String KEY_QUERY = "KEY_QUERY";
    private static final String KEY_IS_SEARCH_MADE = "KEY_IS_SEARCH_MADE";

    public void saveInstance(Bundle outState, String query, boolean isSearchMade) {
        outState.putString(KEY_QUERY, query);
        outState.putBoolean(KEY_IS_SEARCH_MADE, isSearchMade);
    }

    public String getQuery(Bundle savedState) {
        if (savedState == null) {
            return "";
        } else {
            return savedState.getString(KEY_QUERY, "");
        }
    }

    public boolean isSearchMade(Bundle savedState) {
        if (savedState == null) {
            return false;
        } else {
            return savedState.getBoolean(KEY_IS_SEARCH_MADE, false);
        }
    }
}
