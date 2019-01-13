package com.themaker.fshmo.klassikaplus.presentation.novelties;

import com.arellomobile.mvp.MvpView;
import com.themaker.fshmo.klassikaplus.data.domain.Item;

import java.util.List;

public interface NoverltyView extends MvpView {

    void setDataset(List<Item> items);
}
