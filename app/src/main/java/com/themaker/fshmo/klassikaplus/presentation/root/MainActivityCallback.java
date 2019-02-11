package com.themaker.fshmo.klassikaplus.presentation.root;

import androidx.annotation.NonNull;
import com.themaker.fshmo.klassikaplus.data.domain.Item;

public interface MainActivityCallback {

    void launchItemWebViewFragment(@NonNull Item item);
}
