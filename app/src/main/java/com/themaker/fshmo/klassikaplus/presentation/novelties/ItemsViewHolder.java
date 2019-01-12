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
    private TextView price;
    @BindView(R.id.novelty_item_gotoshop)
    private TextView goToShop;
    @BindView(R.id.novelty_item_novelty)
    private TextView noverlty;

    private View baseView;

    public ItemsViewHolder(View v) {
        super(v);
        this.baseView = v;
        ButterKnife.bind(v);
    }

    public void bind(@NonNull final Item item,
                     @NonNull RequestManager glide,
                     @NonNull ItemAdapter.OnItemClickListener onItemClickListener) {
//        if (item.getCategory() != null)
//            categoryTextView.setText(item.getCategory().getName());
//        else
//            categoryTextView.setVisibility(View.GONE);
//        if (item.getTitle() != null && !"".equals(item.getTitle()))
//            headerTextView.setText(item.getTitle());
//        else
//            headerTextView.setVisibility(View.GONE);
//
//        if (item.getPreviewText() != null && !"".equals(item.getPreviewText()))
//            textTextView.setText(item.getPreviewText());
//        else
//            textTextView.setVisibility(View.GONE);
//        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
//        if (item.getPublishDate() != null)
//            dateTextView.setText(sdf.format(item.getPublishDate()));
//        else
//            dateTextView.setVisibility(View.GONE);
//        String url = item.getImageUrl();
//        glide.load(url)
//                .into(photo);
//        view.setOnClickListener(v -> onItemClickListener.onItemClick(item));
    }
}
