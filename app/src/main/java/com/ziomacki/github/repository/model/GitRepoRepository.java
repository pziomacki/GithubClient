package com.ziomacki.github.repository.model;

import com.ziomacki.github.component.RealmWrapper;
import java.util.List;
import javax.inject.Inject;

public class GitRepoRepository {

    private RealmWrapper realmWrapper;

    @Inject
    public GitRepoRepository(RealmWrapper realmWrapper) {
        this.realmWrapper = realmWrapper;
    }

    public void deleteOldAndStoreNewList(List<GitRepo> gitRepoList) {
        if (gitRepoList != null) {
            realmWrapper.deleteOldAndStoreNewList(GitRepo.class, gitRepoList);
        } else {

            throw new IllegalArgumentException("userList must be set");
        }
    }
}
