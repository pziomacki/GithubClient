package com.ziomacki.github.search.view;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import com.jakewharton.rxbinding.support.v4.widget.RxSwipeRefreshLayout;
import com.jakewharton.rxbinding.widget.RxSearchView;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import com.ziomacki.github.GithubApplication;
import com.ziomacki.github.R;
import com.ziomacki.github.inject.ApplicationComponent;
import com.ziomacki.github.inject.SearchModule;
import com.ziomacki.github.search.eventbus.OnUserOpenEvent;
import com.ziomacki.github.search.model.SearchableItem;
import com.ziomacki.github.search.presenter.SearchPresenter;
import com.ziomacki.github.user.view.UserActivity;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subscriptions.Subscriptions;
import static java.util.concurrent.TimeUnit.SECONDS;

public class SearchActivity extends AppCompatActivity implements SearchView{

    @BindView(R.id.search_results_recycler_view)
    RecyclerView resultsRecyclerView;
    @BindView(R.id.search_swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.search_search_view)
    android.widget.SearchView searchView;
    @BindView(R.id.search_toolbar)
    Toolbar toolbar;
    @Inject
    SearchPresenter searchPresenter;
    @Inject
    ResultsAdapter resultsAdapter;
    @Inject
    EventBus eventBus;
    private Subscription searchInputSubscription = Subscriptions.empty();
    private Subscription refreshSubscription = Subscriptions.empty();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        injectDependencies();
        initViews();
        searchPresenter.attachView(this);
        searchPresenter.restoreState(savedInstanceState);
    }

    private void initViews() {
        setupRecyclerView();
        setupSearchView();
        subscribeToSwipeToRefresh();
        setSupportActionBar(toolbar);
        setupSearchView();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        searchPresenter.onSaveInstance(outState);
        super.onSaveInstanceState(outState);
    }

    private void injectDependencies() {
        ApplicationComponent applicationComponent =
                ((GithubApplication) getApplication()).getApplicationComponent();
        applicationComponent.searchComponent(new SearchModule()).inject(this);
    }

    private void subscribeToSwipeToRefresh() {
        refreshSubscription = RxSwipeRefreshLayout.refreshes(swipeRefreshLayout)
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Refreshed());
    }

    @Override
    protected void onStart() {
        super.onStart();
        eventBus.register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        searchPresenter.onStop();
        eventBus.unregister(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        refreshSubscription.unsubscribe();
        searchInputSubscription.unsubscribe();
    }

    @Override
    public void setSearchQuery(String query) {
        searchView.setQuery(query, false);
    }

    void subscribeToSearchViewQueries(android.widget.SearchView searchView) {
        searchInputSubscription = RxSearchView.queryTextChanges(searchView).debounce(1L, SECONDS)
                .skip(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SearchTextChanged());
    }

    private void setupRecyclerView() {
        resultsRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        HorizontalDividerItemDecoration listDivider = new HorizontalDividerItemDecoration
                .Builder(this).build();
        resultsRecyclerView.setLayoutManager(linearLayoutManager);
        resultsRecyclerView.setAdapter(resultsAdapter);
        resultsRecyclerView.addItemDecoration(listDivider);
    }

    private void setupSearchView() {
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint(getString(R.string.search_hint));
        searchView.clearFocus();
        subscribeToSearchViewQueries(searchView);
    }


    @Override
    public void displayResults(List<SearchableItem> resultItemList) {
        resultsAdapter.setResult(resultItemList);
    }

    @Override
    public void displayNoResultsMessage() {
        displaySnackbar(getString(R.string.search_no_results));
    }

    @Override
    public void displayErrorMessage() {
        displaySnackbar(getString(R.string.search_error_message));
    }

    @Override
    public void displayDataLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideDataLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    class SearchTextChanged implements Action1<CharSequence> {
        @Override
        public void call(CharSequence charSequence) {
            searchPresenter.searchAction(charSequence.toString());
        }
    }

    class Refreshed implements Action1<Void> {
        @Override
        public void call(Void aVoid) {
            searchPresenter.refresh();
        }
    }

    private void displaySnackbar(String message) {
        Snackbar.make(swipeRefreshLayout, message, Snackbar.LENGTH_LONG).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onResultItemClickEvent(OnUserOpenEvent userOpenEvent) {
        searchPresenter.onUserOpenEvent(userOpenEvent);
    }

    @Override
    public void openUserView(long id) {
        UserActivity.startActivity(this, id);
    }
}
