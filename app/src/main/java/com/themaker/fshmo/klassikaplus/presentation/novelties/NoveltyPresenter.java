package com.themaker.fshmo.klassikaplus.presentation.novelties;

import android.annotation.SuppressLint;
import android.util.Log;
import com.themaker.fshmo.klassikaplus.data.repositories.CatalogRepository;
import com.themaker.fshmo.klassikaplus.presentation.base.MvpBasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;

import javax.inject.Inject;


public class NoveltyPresenter extends MvpBasePresenter<NoverltyView> {

    private static final String TAG = NoveltyPresenter.class.getName();

    private CatalogRepository repository;

    public NoveltyPresenter() {
    }

    @Inject
    public NoveltyPresenter(CatalogRepository repository) {
        this.repository = repository;
    }

    @SuppressLint("CheckResult")
    void provideDataset() {
        repository.provideNoveltyData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        getViewState()::setDataset,
                        this::logError);
    }

    private void logError(Throwable throwable) {
        Log.e(TAG, "logError: error in dataset" + throwable.getMessage() );
    }

}
