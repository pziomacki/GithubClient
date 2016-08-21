package com.ziomacki.github.search.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import com.ziomacki.github.GithubApplication;
import com.ziomacki.github.R;
import com.ziomacki.github.inject.ApplicationComponent;
import com.ziomacki.github.inject.SearchModule;
import com.ziomacki.github.search.model.SearchableItem;
import com.ziomacki.github.search.presenter.SearchPresenter;
import java.util.List;
import javax.inject.Inject;

public class SearchActivity extends AppCompatActivity implements SearchView{

    @Inject
    SearchPresenter searchPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        injectDependencies();
    }

    private void injectDependencies() {
        ApplicationComponent applicationComponent =
                ((GithubApplication) getApplication()).getApplicationComponent();
        applicationComponent.searchComponent(new SearchModule()).inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public void displayResults(List<SearchableItem> resultItemList) {
        //TODO: implement
    }

    @Override
    public void displayNoResultsMessage() {
        //TODO: implement
    }

    @Override
    public void displayErrorMessage() {
        //TODO: implement
    }

}
