package com.themaker.fshmo.klassikaplus.presentation.novelties;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.RequestManager;
import com.themaker.fshmo.klassikaplus.R;
import com.themaker.fshmo.klassikaplus.data.domain.Item;

class ItemsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.novelty_item_icon)
    ImageView icon;
    @BindView(R.id.novelty_item_name)
    TextView name;
    @BindView(R.id.novelty_item_price)
    TextView price;
    @BindView(R.id.novelty_item_gotoshop)
    TextView goToShop;
    @BindView(R.id.novelty_item_novelty)
    TextView noverlty;

    private View baseView;

    public ItemsViewHolder(View v) {
        super(v);
        this.baseView = v;
        ButterKnife.bind(v);
    }

    public void bind(@NonNull final Item item,
                     @NonNull RequestManager glide,
                     @NonNull ItemAdapter.OnItemClickListener onItemClickListener) {
        price.setText(String.valueOf(item.getPrice()));
        if (item.getName() != null)
            name.setText(item.getName());
        else
            name.setVisibility(View.GONE);
        if (item.getPageAlias() != null)
            goToShop.setVisibility(View.VISIBLE);
        if (item.getNovelty() == true)
            noverlty.setVisibility(View.VISIBLE);
        String url = item.getIcon();
        glide.load(url)
                .into(icon);
        baseView.setOnClickListener(v -> onItemClickListener.onItemClick(item));
    }
}
