package com.themaker.fshmo.klassikaplus.presentation.root;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.themaker.fshmo.klassikaplus.App;
import com.themaker.fshmo.klassikaplus.R;
import com.themaker.fshmo.klassikaplus.data.domain.Item;
import com.themaker.fshmo.klassikaplus.data.preferences.Preferences;
import com.themaker.fshmo.klassikaplus.presentation.base.MvpAppCompatActivity;
import com.themaker.fshmo.klassikaplus.presentation.catalog.CatalogFragment;
import com.themaker.fshmo.klassikaplus.presentation.novelties.NoveltyFragment;
import com.themaker.fshmo.klassikaplus.presentation.web_item.WebItemFragment;

import javax.inject.Inject;
import java.util.List;

public class MainActivity extends MvpAppCompatActivity implements MainActivityView, WebItemCallback {

    private static final String TAG = MainActivity.class.getName();

    @Inject
    Preferences preferences;

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
        App.getInstance().getComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        preferences.setFirstTimeAppLaunch();
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.main_frame, new NoveltyFragment())
//                .addToBackStack(NOVELTY_TAG)
//                .commit();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_frame, new CatalogFragment())
                .addToBackStack("Catalog")
                .commit();
        // FIXME: 20.03.2019 Edit
    }

    @Override
    public void onBackPressed() {
        Log.i(TAG, "onBackPressed: ");
        int backStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
        if (backStackEntryCount > 1) {
            List<Fragment> fragments = getSupportFragmentManager().getFragments();
            for (Fragment fragment : fragments) {
                if (fragment instanceof WebItemFragment)
                    getSupportFragmentManager().beginTransaction()
                            .remove(fragment)
                            .commit();
                getSupportFragmentManager().popBackStack();
                Log.i(TAG, "onBackPressed: details fragment removed");
            }
        } else finish();
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
