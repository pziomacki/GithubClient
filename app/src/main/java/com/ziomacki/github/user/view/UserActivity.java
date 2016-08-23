package com.ziomacki.github.user.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.ziomacki.github.GithubApplication;
import com.ziomacki.github.R;
import com.ziomacki.github.inject.ApplicationComponent;
import com.ziomacki.github.inject.UserModule;
import com.ziomacki.github.user.presenter.UserPresenter;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;

public class UserActivity extends AppCompatActivity implements UserView{


    @BindView(R.id.user_avater)
    ImageView avatarView;
    @BindView(R.id.user_name)
    TextView nameView;
    @BindView(R.id.user_login)
    TextView loginView;
    @BindView(R.id.user_followers)
    TextView followersView;
    @BindView(R.id.user_repositories)
    TextView repositoriesView;
    @BindView(R.id.user_container)
    LinearLayout mainContainer;
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
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);
        injectDependencies();
        userPresenter.attachView(this);
        userPresenter.readIntentExtras(getIntent().getExtras());
    }

    @Override
    protected void onStop() {
        super.onStop();
        userPresenter.onStop();
    }

    private void injectDependencies() {
        ApplicationComponent applicationComponent =
                ((GithubApplication) getApplication()).getApplicationComponent();
        applicationComponent.userComponent(new UserModule()).inject(this);
    }

    @Override
    public void displayAvatar(String url) {
        Picasso.with(this).load(url).noFade().placeholder(R.drawable.avatar_placeholder).into(avatarView);
    }

    @Override
    public void displayAvatarPlaceholder() {
        avatarView.setImageResource(R.drawable.avatar_placeholder);
    }

    @Override
    public void displayLogin(String login) {
        loginView.setText(login);
    }

    @Override
    public void displayName(String name) {
        nameView.setText(name);
    }

    @Override
    public void displayRepositoriesCount(int repositoriesCount) {
        repositoriesView.setText(String.format(getString(R.string.user_repositories), repositoriesCount));
    }

    @Override
    public void displayFollowersCount(int followersCount) {
        followersView.setText(String.format(getString(R.string.user_followers), followersCount));
    }

    public void displayErrorMessage() {
        Snackbar.make(mainContainer, getString(R.string.user_error_message), Snackbar.LENGTH_LONG).show();
    }

}
