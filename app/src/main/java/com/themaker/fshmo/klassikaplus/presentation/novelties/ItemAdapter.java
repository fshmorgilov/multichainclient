package com.themaker.fshmo.klassikaplus.presentation.novelties;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.RequestManager;
import com.themaker.fshmo.klassikaplus.R;
import com.themaker.fshmo.klassikaplus.data.domain.Item;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemsViewHolder> {
    private List<Item> dataset;
    private OnItemClickListener onItemClickListener;
    private RequestManager glide;
    private static final String LTAG = ItemAdapter.class.getName();

    public interface OnItemClickListener {
        void onItemClick(@NonNull Item item);
    }

    public ItemAdapter(@NonNull List<Item> items,
                       @NonNull RequestManager glide, OnItemClickListener clickListener) {
        this.glide = glide;
        this.dataset = items;
        this.onItemClickListener = clickListener;
    }

    @NonNull
    @Override
    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                              int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.novelties, parent, false);
        return new ItemsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsViewHolder holder, int position) {
        holder.bind(dataset.get(position), glide, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void addItem(@NonNull Item item){
        this.dataset.add(item);
        notifyItemInserted(dataset.indexOf(item));
    }

    public void setDataset(@NonNull List<Item> newsItems){
        int currSize = this.dataset.size();
        this.dataset.addAll(newsItems);
        notifyItemRangeInserted(currSize + 1, newsItems.size());
        Log.i(LTAG, "Dataset changed. Added: " + newsItems.size() + " items");

    }
}