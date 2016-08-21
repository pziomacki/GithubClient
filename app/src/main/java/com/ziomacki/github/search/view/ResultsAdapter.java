package com.ziomacki.github.search.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ziomacki.github.R;
import com.ziomacki.github.search.model.SearchableItem;
import org.greenrobot.eventbus.EventBus;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

public class ResultsAdapter extends RecyclerView.Adapter<ResultsViewHolder> {

    private List<SearchableItem> resultItemList = Collections.emptyList();

    private EventBus eventBus;

    @Inject
    public ResultsAdapter(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void setResult(List<SearchableItem> resultItemList) {
        this.resultItemList = resultItemList;
        notifyDataSetChanged();
    }

    @Override
    public ResultsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.result_item_view, parent, false);
        return new ResultsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ResultsViewHolder holder, int position) {
        SearchableItem resultItem = resultItemList.get(position);
        holder.bind(resultItem, eventBus);
    }

    @Override
    public int getItemCount() {
        return resultItemList.size();
    }
}
