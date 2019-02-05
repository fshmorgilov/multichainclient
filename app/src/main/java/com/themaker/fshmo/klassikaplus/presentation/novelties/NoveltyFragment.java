package com.themaker.fshmo.klassikaplus.presentation.novelties;

import android.util.Log;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.themaker.fshmo.klassikaplus.R;
import com.themaker.fshmo.klassikaplus.data.domain.Item;
import com.themaker.fshmo.klassikaplus.presentation.base.MvpBaseFragment;

import java.util.List;

public class NoveltyFragment extends MvpBaseFragment implements NoveltyView {

    private static final String TAG = NoveltyFragment.class.getName();
    private List<Item> dataset;
    private RequestManager glide;

    @BindView(R.id.novelty_recycler)
    RecyclerView recycler;
    @InjectPresenter
    NoveltyPresenter presenter;


    @Override
    protected void onPostCreateView() {
        super.onPostCreateView();
        glide = Glide.with(rootView);
        presenter.provideDataset();
        recycler.setAdapter(new NoveltyAdapter(
                dataset,
                glide,
                item -> {
                    Log.i(TAG, "onPostCreateView: item pressed: " + item.getName());
                    // TODO: 1/13/2019
                }));
    }


    @Override
    protected int setLayoutRes() {
        return R.layout.novelty_fragment;
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public void setDataset(List<Item> items) {
        if (!items.isEmpty())
            this.dataset = items;
    }

    @Override
    public void showError() {
//todo Показывать ошибку. Добавить вьюху с изображением ошибки
    }
}
