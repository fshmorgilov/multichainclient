package com.themaker.fshmo.klassikaplus.presentation.web_item;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import butterknife.BindView;
import com.themaker.fshmo.klassikaplus.R;
import com.themaker.fshmo.klassikaplus.data.domain.Item;
import com.themaker.fshmo.klassikaplus.presentation.base.MvpBaseFragment;
import com.themaker.fshmo.klassikaplus.presentation.common.State;

public class WebItemFragment extends MvpBaseFragment {

    private static final String TAG = WebItemFragment.class.getName();
    private static final String KEY_ID = "BundleKey";

    private Item item;

    @BindView(R.id.web_item_webview)
    WebView webView;

    @BindView(R.id.web_item_error)
    TextView error;


    public static WebItemFragment newInstance(@NonNull Item item) {
        WebItemFragment fragment = new WebItemFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_ID, item);
        fragment.setArguments(bundle);
        Log.i(TAG, "newInstance: fragment created for item: " + item.getName());
        return fragment;
    }

    @Override
    protected void onPostCreateView() {
        super.onPostCreateView();
        showState(State.Loading);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            this.item = (Item) bundle.getSerializable(KEY_ID);
            Log.d(TAG, "onPostCreateView: item: " + item.getName());
            if (item != null && item.getPageAlias() != null) {
                webView.loadUrl(item.getPageAlias());
                showState(State.HasData);
                Log.i(TAG, "onPostCreateView: item loaded: " + item.getPageAlias());
            } else { showError("item is null"); }
        } else { showError("bundle is null"); }
    }

    private void showError(String message) {
        Log.e(TAG, "onPostCreateView: no data provided for webView. Reason: " + message);
        showState(State.NoData);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.web_item;
        // TODO: 2/6/2019 сделать webView layout
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void showState(@NonNull State state) {
        Log.i(TAG, "showState: calling state " + state.toString());
        switch (state) {
            case HasData:
                webView.setVisibility(View.VISIBLE);
                error.setVisibility(View.GONE);
                break;
            case NoData:
                webView.setVisibility(View.GONE);
                error.setVisibility(View.VISIBLE);
                break;
            case Loading:
                webView.setVisibility(View.GONE);
                error.setVisibility(View.GONE);
                // TODO: 2/7/2019 progressbar
                break;
            default:
                throw new IllegalStateException();
        }
    }

}
