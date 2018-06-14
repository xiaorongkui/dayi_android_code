package com.mall.dayi.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;


import com.mall.dayi.DaYiApp;
import com.mall.dayi.common.NetResponseCode;
import com.mall.dayi.di.component.DaggerFragmentComponent;
import com.mall.dayi.di.component.FragmentComponent;
import com.mall.dayi.di.module.FragmentModule;
import com.mall.dayi.ui.widget.CommonDialog;
import com.mall.dayi.util.LogUtil;

import javax.inject.Inject;

/**
 * author：rongkui.xiao --2018/3/16
 * email：dovexiaoen@163.com
 * description:mvp模式下的fragment
 */

public abstract class BaseMvpFragment<T extends BasePresenter> extends BaseFragment implements BaseView {

    @Inject
    public T presenter;
    private CommonDialog loginCommonDialog;

    protected abstract void injectPresenter();

    public FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .appComponent(DaYiApp.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    private FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        injectPresenter();
        if (presenter != null) presenter.attachView(this);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (presenter != null) {
            presenter.detachView();
        }
        if (loginCommonDialog != null && loginCommonDialog.isShowing()) loginCommonDialog.dismiss();
    }

    @Override
    public void onSuccess(Object response, int tag) {
    }

    @Override
    public void onError(String errorMsg, String code, int tag, Object o) {
        LogUtil.i("接口请求失败" + ";errorMsg=" + errorMsg + ";errorcode=" + code + ";tag=" + tag);
        if (isShowNetErrorDefaultToast()) toast(errorMsg);
        if (TextUtils.isEmpty(code)) {
            return;
        }
        switch (code) {
            case NetResponseCode.HMC_NO_LOGIN:

                break;
            default:
                break;
        }
    }

    protected boolean isShowNetErrorDefaultToast() {
        return true;
    }

    @Override
    public void onStop() {
        super.onStop();
        if (loginCommonDialog != null && loginCommonDialog.isShowing()) loginCommonDialog.dismiss();
    }
}
