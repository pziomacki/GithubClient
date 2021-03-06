package com.ziomacki.github.user.view;

public interface UserView {
    void displayAvatar(String url);
    void displayName(String name);
    void displayLogin(String login);
    void displayRepositoriesCount(int repositoriesCount);
    void displayFollowersCount(int followersCount);
    void displayAvatarPlaceholder();
    void displayErrorMessage();
}
