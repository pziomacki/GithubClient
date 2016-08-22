package com.ziomacki.github.repository.model;

import com.google.gson.annotations.SerializedName;
import com.ziomacki.github.search.eventbus.OnGitRepoOpenEvent;
import com.ziomacki.github.search.eventbus.SearchableItemOpenEvent;
import com.ziomacki.github.search.model.SearchableItem;
import io.realm.RealmObject;

public class GitRepo extends RealmObject implements SearchableItem {

    @SerializedName("id")
    public long id;
    @SerializedName("name")
    public String name;

    @Override
    public String getNameForList() {
        return name;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public SearchableItemOpenEvent getSearchableItemOpenEvent() {
        return new OnGitRepoOpenEvent(id);
    }
}
