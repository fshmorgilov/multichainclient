package com.themaker.fshmo.klassikaplus.presentation.root;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.themaker.fshmo.klassikaplus.R;
import com.themaker.fshmo.klassikaplus.data.domain.Item;
import com.themaker.fshmo.klassikaplus.presentation.base.MvpAppCompatActivity;
import com.themaker.fshmo.klassikaplus.presentation.novelties.NoveltyFragment;
import com.themaker.fshmo.klassikaplus.presentation.web_item.WebItemFragment;

public class MainActivity extends MvpAppCompatActivity implements MainActivityView, MainActivityCallback {

    private static final String TAG = MainActivity.class.getName();

    @InjectPresenter
    MainActivityPresenter presenter;

    private static final String WEBVIEW_TAG = "ItemWebView";
    private static final String NOVELTY_TAG = "NoveltyFragment";

    public static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: ----------------------");
        Log.i(TAG, "onCreate: STARTED");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_frame, new NoveltyFragment())
                .addToBackStack(NOVELTY_TAG)
                .commit();
    }

    @Override
    public void launchItemWebViewFragment(@NonNull Item item) {
        Log.i(TAG, "launchItemWebViewFragment: displaying itemWebViewFragment with item: " + item.getName());
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_frame, WebItemFragment.newInstance(item))
                .addToBackStack(WEBVIEW_TAG)
                .commit();
    }
}
