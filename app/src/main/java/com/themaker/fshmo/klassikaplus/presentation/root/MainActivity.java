package com.themaker.fshmo.klassikaplus.presentation.root;

import android.os.Bundle;
import com.themaker.fshmo.klassikaplus.presentation.base.MvpAppCompatActivity;
import com.themaker.fshmo.klassikaplus.presentation.novelties.NoveltyFragment;

public class MainActivity extends MvpAppCompatActivity {

    private static final String NOVELTY_TAG = "NoveltyFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction()
                .add(new NoveltyFragment(), NOVELTY_TAG)
                .addToBackStack(NOVELTY_TAG)
                .commit();
    }
}
