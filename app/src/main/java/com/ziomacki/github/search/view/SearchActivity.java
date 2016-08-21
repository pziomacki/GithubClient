package com.ziomacki.github.search.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import com.ziomacki.github.R;
import com.ziomacki.github.search.model.SearchableItem;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements SearchView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
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
