package com.ziomacki.github.component;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;

public class RealmWrapper {

    private RealmConfiguration realmConfiguration;

    @Inject
    public RealmWrapper(RealmConfiguration realmConfiguration) {
        this.realmConfiguration = realmConfiguration;
    }

    public Realm getRealmInstance() {
        return Realm.getInstance(realmConfiguration);
    }

    public  <T extends RealmObject> void deleteOldAndStoreNewList(Class<T> clazz, List<T> newList) {
        Realm realm = getRealmInstance();
        realm.beginTransaction();
        realm.delete(clazz);
        realm.commitTransaction();
        realm.beginTransaction();
        realm.copyToRealm(newList);
        realm.commitTransaction();
        realm.close();
    }

    public <T extends RealmObject> List<T> getAllItems(Class<T> clazz) {
        Realm realm = getRealmInstance();
        List<T> resultsManaged = realm.where(clazz).findAll();
        List<T> resultsUnmanaged = new ArrayList<>();
        if (resultsManaged != null) {
            resultsUnmanaged.addAll(realm.copyFromRealm(resultsManaged));
        }
        realm.close();
        return resultsUnmanaged;
    }
}
