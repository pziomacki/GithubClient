package com.ziomacki.github.user.model;

import com.ziomacki.github.component.RealmWrapper;
import java.util.List;
import javax.inject.Inject;

public class UserRepository {

    private RealmWrapper realmWrapper;

    @Inject
    public UserRepository(RealmWrapper realmWrapper) {
        this.realmWrapper = realmWrapper;
    }

    public void deleteOldAndStoreNewUserList(List<User> userList) {
        realmWrapper.deleteOldAndStoreNewList(User.class, userList);
    }

}
