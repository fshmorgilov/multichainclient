package com.themaker.fshmo.klassikaplus.presentation.novelties;

import com.themaker.fshmo.klassikaplus.presentation.base.MvpBaseFragment;

public class NoveltyFragment extends MvpBaseFragment implements NoverltyView {
    @Override
    protected int setLayoutRes() {
        return 0;
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }
}
