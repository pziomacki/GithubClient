package com.ziomacki.github.search.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.ziomacki.github.R;
import com.ziomacki.github.search.model.SearchableItem;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.result_item_title)
    TextView resultItemTitle;
    @BindView(R.id.result_item_id)
    TextView resultItemId;

    private SearchableItem resultItem;

    public ResultsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(SearchableItem resultItem) {
        this.resultItem = resultItem;
        resultItemTitle.setText(resultItem.getDisplayName());
        resultItemId.setText(Long.toString(resultItem.getId()));
    }

}
