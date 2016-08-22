package com.ziomacki.github.user.model;

import com.ziomacki.github.component.RealmWrapper;
import java.util.List;
import javax.inject.Inject;
import io.realm.Realm;
import rx.Observable;
import rx.Subscriber;

public class UserRepository {

    private RealmWrapper realmWrapper;

    @Inject
    public UserRepository(RealmWrapper realmWrapper) {
        this.realmWrapper = realmWrapper;
    }

    public void deleteOldAndStoreNewUserList(List<User> userList) {
        realmWrapper.deleteOldAndStoreNewList(User.class, userList);
    }

    public User getUserById(long id) {
        Realm realm = realmWrapper.getRealmInstance();
        User user = realm.where(User.class).equalTo(User.KEY_ID, id).findFirst();
        User userUnmanaged;
        if (user != null) {
            userUnmanaged = realm.copyFromRealm(user);
        } else {
            userUnmanaged = new User();
        }
        realm.close();
        return userUnmanaged;
    }

    public Observable<User> getUserByIdObservable(final long id) {
        return Observable.create(new Observable.OnSubscribe<User>() {
            @Override
            public void call(Subscriber<? super User> subscriber) {
                User user = getUserById(id);
                subscriber.onNext(user);
            }
        });
    }

    public void copyOrUpdateUser(User user) {
        Realm realm = realmWrapper.getRealmInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(user);
        realm.commitTransaction();
        realm.close();
    }
}
