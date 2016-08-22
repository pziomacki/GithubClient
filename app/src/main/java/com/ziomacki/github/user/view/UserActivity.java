package com.ziomacki.github.user.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import com.ziomacki.github.R;
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
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
