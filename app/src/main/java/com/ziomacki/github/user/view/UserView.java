package com.ziomacki.github.user.view;

public interface UserView {
    void displayAvatar(String url);
    void displayName(String name);
    void displayStarsCount(int starsCount);
    void displayFollowersCount(int followersCount);
    void displayAvatarPlaceholder();
    void displayErrorMessage();

}
