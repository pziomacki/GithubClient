package com.ziomacki.github.user.model;

import com.ziomacki.github.component.RealmWrapper;
import java.util.List;
import javax.inject.Inject;
import io.realm.Realm;

public class UserRepository {

    private RealmWrapper realmWrapper;

    @Inject
    public UserRepository(RealmWrapper realmWrapper) {
        this.realmWrapper = realmWrapper;
    }

    public void deleteOldAndStoreNewUserList(List<User> userList) {
        realmWrapper.deleteOldAndStoreNewList(User.class, userList);
    }

    public User getUserById(int id) {
        Realm realm = realmWrapper.getRealmInstance();
        User user = realm.where(User.class).equalTo(User.KEY_ID, id).findFirst();
        User userUnmanaged;
        if (user != null) {
            userUnmanaged = user;
        } else {
            userUnmanaged = new User();
        }
        realm.close();
        return userUnmanaged;
    }

}
