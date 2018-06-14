package com.mall.dayi.net.observer;

import android.content.Context;


import com.mall.dayi.DaYiApp;
import com.mall.dayi.R;
import com.mall.dayi.common.NetResponseCode;
import com.mall.dayi.net.HttpCallBack;
import com.mall.dayi.net.exception.HandlerException;
import com.mall.dayi.ui.widget.LoadingDialog;
import com.mall.dayi.util.CommonUtil;
import com.mall.dayi.util.LogUtil;
import com.mall.dayi.util.ProgressDialogUtil;

import java.lang.ref.WeakReference;

/**
 * Created by Yun on 2018/2/19 0019.
 * 网络请求观察者(刷新样式由子类决定)
 */
public class BaseShowLoadingObserver<T> extends BaseObserver<T> {

    private final WeakReference<Context> mActivity;
    private HttpCallBack httpCallBack = null;
    private boolean showProgressDialog;

    private boolean isCancel;
    private int tag;

    /**
     * 显示loading
     */
    protected void showLoading() {
        ProgressDialogUtil.showWaitDialog();
    }


    /**
     * 隐藏loading
     */
    protected void hideLoading() {
        ProgressDialogUtil.stopWaitDialog();
    }

    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    public void onCancelProgress() {
        if (!this.isDisposed())
            this.dispose();
        hideLoading();
        if (httpCallBack != null) httpCallBack.onCancel(tag);
        LogUtil.i("请求已经取消");
    }

    private void initProgressDialog(boolean cancel) {
        Context context = mActivity.get();
        LoadingDialog loadingDialog = ProgressDialogUtil.initProgressDialog(context, cancel);
        if (cancel && loadingDialog != null) loadingDialog.setOnCancelListener(dialog -> onCancelProgress());
    }

    public BaseShowLoadingObserver(Context context, HttpCallBack httpCallBack, boolean isShowProgress, int tag) {
        this.httpCallBack = httpCallBack;
        this.mActivity = new WeakReference<>(context);
        this.showProgressDialog = isShowProgress;
        this.tag = tag;
    }

    @Override
    protected void onRequestStart() {
        super.onRequestStart();
        if (showProgressDialog) {
            initProgressDialog(isCancel);
            showLoading();
        }
        if (!CommonUtil.isNetworkAvailable(DaYiApp.getContext())) {
            onRequestError(HandlerException.handleException(new
                    HandlerException.ResponeThrowable(CommonUtil.getString(R.string.net_connect_error), NetResponseCode
                    .HMC_NETWORK_ERROR)));

        }
    }

    @Override
    protected void onRequestSuccess(T t) {
        LogUtil.i("成功网络请求返回");
        if (httpCallBack != null) {
            httpCallBack.onNext(t, tag);
        }
        hideLoading();
    }

    @Override
    protected void onRequestError(Throwable e) {
        hideLoading();
        LogUtil.i("订阅出错。异常情况=" + e.toString() + ":" + e.getMessage());
        errorDo(e);
    }

    private void errorDo(Throwable e) {
        Context context = mActivity.get();
        if (context == null) return;
        if (httpCallBack != null) {
            httpCallBack.onError(HandlerException.handleException(e), tag);
        }
    }

    @Override
    protected void onRequestComplete() {
        super.onRequestComplete();
        hideLoading();
    }

    public void setCancel(boolean cancel) {
        isCancel = cancel;
    }

}
