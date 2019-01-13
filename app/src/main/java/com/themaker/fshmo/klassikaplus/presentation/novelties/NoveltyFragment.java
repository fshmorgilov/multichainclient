package com.themaker.fshmo.klassikaplus.presentation.novelties;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.themaker.fshmo.klassikaplus.R;
import com.themaker.fshmo.klassikaplus.data.domain.Item;
import com.themaker.fshmo.klassikaplus.presentation.base.MvpBaseFragment;

import java.util.List;

public class NoveltyFragment extends MvpBaseFragment implements NoverltyView {

    private static final String TAG = NoveltyFragment.class.getName();
    private List<Item> dataset;
    private RequestManager glide = Glide.with(rootView);

    @BindView(R.id.novelty_recycler)
    RecyclerView recycler;

    @Override
    protected void onPostCreateView() {
        super.onPostCreateView();
        presenter.provideDataset();
        recycler.setAdapter(new NoveltyAdapter(
                dataset,
                glide,
                item -> {
            // TODO: 1/13/2019
        }));
    }

    @InjectPresenter
    private NoveltyPresenter presenter;

    @Override
    protected int setLayoutRes() {
        return R.layout.novelty_fragment;
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void setDataset(List<Item> items) {
        this.dataset = items;
    }
}
