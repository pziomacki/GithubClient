package com.ziomacki.github.repository.model;

import com.google.gson.annotations.SerializedName;
import com.ziomacki.github.search.model.SearchableItem;
import io.realm.RealmObject;

public class GitRepo extends RealmObject implements SearchableItem {

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
