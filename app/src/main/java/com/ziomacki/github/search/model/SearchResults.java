package com.ziomacki.github.search.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class SearchResults<SearchableItem> {

    @SerializedName("total_count")
    public int totalCount;

    @SerializedName("items")
    public List<SearchableItem> items;

}
