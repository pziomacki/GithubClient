package com.ziomacki.github.repository.model;

import com.google.gson.annotations.SerializedName;
import com.ziomacki.github.R;
import com.ziomacki.github.search.eventbus.OnGitRepoOpenEvent;
import com.ziomacki.github.search.eventbus.SearchableItemOpenEvent;
import com.ziomacki.github.search.model.SearchableItem;
import io.realm.RealmObject;

public class GitRepo extends RealmObject implements SearchableItem {

    private static final String TYPE = "GIT_REPO";

    @SerializedName("id")
    public long id;
    @SerializedName("full_name")
    public String fullName;

    @Override
    public String getNameForList() {
        return fullName;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public SearchableItemOpenEvent getSearchableItemOpenEvent() {
        return new OnGitRepoOpenEvent(id);
    }

    @Override
    public int getItemIconId() {
        return R.drawable.ic_repo;
    }
}
