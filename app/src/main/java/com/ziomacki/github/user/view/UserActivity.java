package com.ziomacki.github.user.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import com.ziomacki.github.GithubApplication;
import com.ziomacki.github.R;
import com.ziomacki.github.inject.ApplicationComponent;
import com.ziomacki.github.user.presenter.UserPresenter;
import javax.inject.Inject;
import butterknife.BindView;

public class UserActivity extends AppCompatActivity implements UserView{


    @BindView(R.id.user_avater)
    ImageView avatar;
    @BindView(R.id.user_name)
    TextView name;
    @BindView(R.id.user_followers)
    TextView followers;
    @BindView(R.id.user_stars)
    TextView stars;

    @Inject
    UserPresenter userPresenter;

    public static void startActivity(Context context, long userId) {
        Intent intent = new Intent(context, UserActivity.class);
        intent.putExtra(UserPresenter.KEY_ID, userId);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
        userPresenter.attachView(this);
        userPresenter.readIntentExtras(getIntent().getExtras());
    }

    private void injectDependencies() {
        ApplicationComponent applicationComponent =
                ((GithubApplication) getApplication()).getApplicationComponent();
        applicationComponent.userComponent().inject(this);
    }

    @Override
    public void displayAvatar(String url) {
        //TODO: implement
    }

    @Override
    public void displayName(String name) {
        //TODO: implement
    }

    @Override
    public void displayStarsCount(int starsCount) {
        //TODO: implement
    }

    @Override
    public void displayFollowersCount(int followersCount) {
        //TODO: implement
    }
}
