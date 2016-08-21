package com.ziomacki.github.user.model;

import com.google.gson.annotations.SerializedName;
import com.ziomacki.github.search.model.SearchableItem;

public class User implements SearchableItem {

    @SerializedName("login")
    public String login;
    @SerializedName("id")
    public int id;

    @Override
    public String getDisplayName() {
        return login;
    }

    @Override
    public int getId() {
        return id;
    }
}
