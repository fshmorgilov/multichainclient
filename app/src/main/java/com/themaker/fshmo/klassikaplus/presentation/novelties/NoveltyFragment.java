package com.themaker.fshmo.klassikaplus.presentation.novelties;

import android.util.Log;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.themaker.fshmo.klassikaplus.R;
import com.themaker.fshmo.klassikaplus.data.domain.Item;
import com.themaker.fshmo.klassikaplus.presentation.base.MvpBaseFragment;

import java.util.ArrayList;
import java.util.List;

public class NoveltyFragment extends MvpBaseFragment implements NoveltyView {

    private static final String TAG = NoveltyFragment.class.getName();
    private List<Item> dataset = new ArrayList<>();
    private RequestManager glide;

    // TODO: 2/5/2019 dispose disposable, иначе приложение продолжает жрать сеть

    @BindView(R.id.novelty_recycler)
    RecyclerView recycler;

    @InjectPresenter
    NoveltyPresenter presenter;

    private NoveltyAdapter noveltyAdapter;

    @Override
    protected void onPostCreateView() {
        super.onPostCreateView();
        glide = Glide.with(rootView);
        presenter.provideDataset();
        noveltyAdapter = new NoveltyAdapter(
                dataset,
                glide,
                item -> {
                    Log.i(TAG, "onPostCreateView: item pressed: " + item.getName());
                }
        );
        noveltyAdapter.setDataset(dataset);
        recycler.setAdapter(noveltyAdapter);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
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
        if (!items.isEmpty()) {
            this.dataset = items;
            noveltyAdapter.notifyDataSetChanged();

        }

    }

    @Override
    public void showError() {
//todo Показывать ошибку. Добавить вьюху с изображением ошибки
    }
}
