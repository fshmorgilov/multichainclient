package com.themaker.fshmo.klassikaplus.presentation.novelties;

import android.icu.text.SimpleDateFormat;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.RequestManager;
import com.themaker.fshmo.klassikaplus.data.domain.Item;

import java.util.Locale;

import static android.provider.Settings.System.DATE_FORMAT;

class ItemsViewHolder extends RecyclerView.ViewHolder {

    public ItemsViewHolder(View v) {
        super(v);
        //TODO
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
