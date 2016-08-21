package com.ziomacki.github.search.view;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import com.jakewharton.rxbinding.widget.RxSearchView;
import com.ziomacki.github.GithubApplication;
import com.ziomacki.github.R;
import com.ziomacki.github.inject.ApplicationComponent;
import com.ziomacki.github.inject.SearchModule;
import com.ziomacki.github.search.model.SearchableItem;
import com.ziomacki.github.search.presenter.SearchPresenter;
import java.util.List;
import javax.inject.Inject;
import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.Subscriptions;
import static java.util.concurrent.TimeUnit.SECONDS;

public class SearchActivity extends AppCompatActivity implements SearchView{

    @Inject
    SearchPresenter searchPresenter;
    private Subscription searchInputSubscription = Subscriptions.empty();


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
    protected void onDestroy() {
        super.onDestroy();
        searchInputSubscription.unsubscribe();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        android.widget.SearchView searchView = (android.widget.SearchView) MenuItemCompat.getActionView(item);
        searchView.setImeOptions(EditorInfo.IME_FLAG_NO_EXTRACT_UI | EditorInfo.IME_FLAG_NO_FULLSCREEN | EditorInfo.IME_ACTION_SEARCH);
        searchView.setIconified(false);
        subscribeToSearchViewQueries(searchView);
        return true;
    }

    void subscribeToSearchViewQueries(android.widget.SearchView searchView) {
        searchInputSubscription = RxSearchView.queryTextChanges(searchView).debounce(1L, SECONDS)
                .skip(1)
                .subscribe(new SearchTextChanged());
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

    class SearchTextChanged implements Action1<CharSequence> {

        @Override
        public void call(CharSequence charSequence) {
            searchPresenter.searchAction(charSequence.toString());
        }
    }

}
