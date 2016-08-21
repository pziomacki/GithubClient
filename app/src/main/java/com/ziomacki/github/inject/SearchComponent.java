package com.ziomacki.github.inject;

import com.ziomacki.github.search.view.SearchActivity;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = {SearchModule.class})
public interface SearchComponent {
    void inject(SearchActivity searchActivity);
}
