package com.ziomacki.github.user.model;

import com.google.gson.annotations.SerializedName;
import com.ziomacki.github.search.eventbus.OnUserOpenEvent;
import com.ziomacki.github.search.eventbus.SearchableItemOpenEvent;
import com.ziomacki.github.search.model.SearchableItem;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject implements SearchableItem {

    public static final String TYPE = "USER";
    public static final String KEY_ID = "id";

    @SerializedName("id")
    @PrimaryKey
    public long id;
    @SerializedName("login")
    public String login;
    @SerializedName("name")
    public String name;
    @SerializedName("avatar_url")
    public String avatarUrl;
    @SerializedName("followers")
    public int followers;
    @SerializedName("public_repos")
    public int publicRepos;

    public boolean isAllDataFetched = false;

    @Override
    public String getNameForList() {
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

    @Override
    public String getItemType() {
        return TYPE;
    }
}
