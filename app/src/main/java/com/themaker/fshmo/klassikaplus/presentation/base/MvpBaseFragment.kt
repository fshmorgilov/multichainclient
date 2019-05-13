package com.themaker.fshmo.klassikaplus.presentation.base

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import butterknife.ButterKnife
import butterknife.Unbinder
import com.arellomobile.mvp.MvpView
import com.themaker.fshmo.klassikaplus.R
import com.themaker.fshmo.klassikaplus.presentation.common.State
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class MvpBaseFragment : MvpAppCompatFragment(), BackButtonListener, MvpView {

    protected lateinit var rootView: View
    protected var orientation: Int = 0


    private var toast: Toast? = null
    protected var compositeDisposable: CompositeDisposable? = CompositeDisposable()
    protected lateinit var unbinder: Unbinder

    protected open fun addSub(subscription: Disposable) {
        compositeDisposable!!.add(subscription)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (compositeDisposable != null && !compositeDisposable!!.isDisposed)
            compositeDisposable!!.dispose()
    }

    protected abstract fun setLayoutRes(): Int

    /**
     * Вызываеться после бинда вьюшек
     */
    protected open fun onPostCreateView() {
        // nothing
    }

    /**
     * Для настройки тулбара
     */
    @CallSuper
    protected fun configureToolbar(toolbar: Toolbar) {
        if (showBackButton()) {
            toolbar.setNavigationIcon(R.drawable.ic_sharp_arrow_back_24px)
            toolbar.setNavigationOnClickListener { view -> onBackPressed() }
        } else {
            toolbar.navigationIcon = null
            toolbar.setOnMenuItemClickListener(null)
        }
    }

    /**
     * Переопределить чтобы проказать кнопку назад в тулбаре
     */
    protected fun showBackButton(): Boolean {
        return false
    }

    protected fun orientationPortrait(): Boolean {
        orientation = this.resources.configuration.orientation
        return orientation == Configuration.ORIENTATION_PORTRAIT
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(setLayoutRes(), container, false)
        unbinder = ButterKnife.bind(this, rootView)
        setupUI(rootView)
        onPostCreateView()
        return rootView
    }


    protected fun setupUI(view: View) {
        if (view !is EditText) {
            view.setOnTouchListener { v, event ->
                hideSoftKeyboard(activity)
                false
            }
        }
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val innerView = view.getChildAt(i)
                setupUI(innerView)
            }
        }
    }

    protected fun hideSoftKeyboard(activity: Activity?) {
        if (activity == null) return
        val view = activity.currentFocus
        if (view != null) {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    protected fun showToast(message: String) {
        if (toast != null) toast!!.cancel()
        toast = Toast.makeText(context, message, Toast.LENGTH_LONG)
        toast!!.show()
    }

    protected fun showToast(message: Int) {
        if (toast != null) toast!!.cancel()
        toast = Toast.makeText(context, message, Toast.LENGTH_LONG)
        toast!!.show()
    }

    protected fun showDialog(context: Context, title: String, message: String) {
        AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(android.R.string.ok, null)
            .create()
            .show()
    }

    protected fun showDialog(context: Context, title: String, message: String, lis: DialogInterface.OnClickListener) {
        AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(android.R.string.ok, lis)
            .create()
            .show()
    }

    /**
     * Показывать статус
     */
    protected open fun showState(state: State) {
        when (state) {
            else -> throw IllegalArgumentException("Unknown state: $state")
        }
    }

}