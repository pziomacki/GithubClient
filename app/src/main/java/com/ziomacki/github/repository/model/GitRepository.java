package com.ziomacki.github.repository.model;

import com.google.gson.annotations.SerializedName;
import com.ziomacki.github.search.model.SearchableItem;

public class GitRepository implements SearchableItem {

    //TODO: implement
    @SerializedName("id")
    public long id;

    @Override
    public String getDisplayName() {
        //TODO: implement
        return null;
    }

    @Override
    public long getId() {
        return id;
    }
}
