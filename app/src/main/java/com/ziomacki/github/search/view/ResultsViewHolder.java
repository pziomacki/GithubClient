package com.ziomacki.github.search.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ziomacki.github.R;
import com.ziomacki.github.component.ResourceProvider;
import com.ziomacki.github.search.eventbus.SearchableItemOpenEvent;
import com.ziomacki.github.search.model.SearchableItem;
import org.greenrobot.eventbus.EventBus;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.result_item_title)
    TextView resultItemTitle;
    @BindView(R.id.result_item_id)
    TextView resultItemId;
    @BindView(R.id.result_item_container)
    LinearLayout mainContainer;

    public ResultsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(SearchableItem resultItem, EventBus eventBus, ResourceProvider resourceProvider) {
        setResultItemTitle(resourceProvider, resultItem.getNameForList(), resultItem.getItemIconId());
        resultItemId.setText(Long.toString(resultItem.getId()));
        mainContainer.setOnClickListener(new OnResultsItemClickListener(eventBus,
                resultItem.getSearchableItemOpenEvent()));
    }

    private static class OnResultsItemClickListener implements View.OnClickListener {
        private SearchableItemOpenEvent event;
        private EventBus eventBus;

        public OnResultsItemClickListener(EventBus eventBus, SearchableItemOpenEvent event) {
            this.event = event;
            this.eventBus = eventBus;
        }

        @Override
        public void onClick(View view) {
            eventBus.post(event);
        }
    }

    private void setResultItemTitle(ResourceProvider resourceProvider, String title, int iconId) {
        resultItemTitle.setText(title);
        resultItemTitle.setCompoundDrawablesWithIntrinsicBounds(resourceProvider.getDrawable(iconId), null, null, null);
    }
}
