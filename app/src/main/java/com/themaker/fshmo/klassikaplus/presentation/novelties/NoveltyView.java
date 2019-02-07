package com.themaker.fshmo.klassikaplus.presentation.novelties;

import androidx.annotation.NonNull;
import com.arellomobile.mvp.MvpView;
import com.themaker.fshmo.klassikaplus.data.domain.Item;
import com.themaker.fshmo.klassikaplus.presentation.common.State;
import io.reactivex.disposables.Disposable;

import java.util.List;

public interface NoveltyView extends MvpView {

    void setDataset(List<Item> items);

    void showError();

    void showState(@NonNull State state);

    void addSub(Disposable subscription);
}
