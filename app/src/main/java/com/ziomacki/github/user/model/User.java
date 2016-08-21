package com.ziomacki.github.user.model;

import com.google.gson.annotations.SerializedName;
import com.ziomacki.github.search.eventbus.OnUserOpenEvent;
import com.ziomacki.github.search.eventbus.SearchableItemOpenEvent;
import com.ziomacki.github.search.model.SearchableItem;
import io.realm.RealmObject;

public class User extends RealmObject implements SearchableItem{

    @SerializedName("login")
    public String login;
    @SerializedName("id")
    public long id;

    @Override
    public String getDisplayName() {
        return login;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public SearchableItemOpenEvent getSearchableItemOpenEvent() {
        return new OnUserOpenEvent(id);
    }
}
