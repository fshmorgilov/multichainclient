package com.themaker.fshmo.klassikaplus.presentation.novelties;

import android.annotation.SuppressLint;
import android.util.Log;
import com.themaker.fshmo.klassikaplus.data.repositories.CatalogRepository;
import com.themaker.fshmo.klassikaplus.presentation.base.MvpBasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;


public class NoveltyPresenter extends MvpBasePresenter<NoveltyView> {

    private static final String TAG = NoveltyPresenter.class.getName();

//    @Inject
    CatalogRepository repository = CatalogRepository.getInstance();

//    @Inject
//    public NoveltyPresenter(CatalogRepository repository) {
//        this.repository = repository;
//    }

    @SuppressLint("CheckResult")
    void provideDataset() {
        if (repository == null)
            Log.e(TAG, "provideDataset: Repository problem");
        Disposable subscribe = repository.provideNoveltyData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        getViewState()::setDataset,
                        this::displayError);
    }

   private void displayError(Throwable trowable){
        logError(trowable);
        getViewState().showError();
   }

    private void logError(Throwable throwable) {
        Log.e(TAG, "logError: error in dataset" + throwable.getMessage() );
    }

}
