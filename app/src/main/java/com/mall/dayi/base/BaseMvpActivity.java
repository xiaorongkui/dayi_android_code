package com.mall.dayi.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.mall.dayi.DaYiApp;
import com.mall.dayi.common.NetResponseCode;
import com.mall.dayi.di.component.ActivityComponent;
import com.mall.dayi.di.component.DaggerActivityComponent;
import com.mall.dayi.di.module.ActivityModule;
import com.mall.dayi.ui.widget.CommonDialog;
import com.mall.dayi.util.CommonUtil;
import com.mall.dayi.util.LogUtil;

import javax.inject.Inject;

/**
 * author：rongkui.xiao --2018/3/16
 * email：dovexiaoen@163.com
 * description:基础的BaseMvpActivity
 */

public abstract class BaseMvpActivity<T extends BasePresenter> extends BaseActivity implements BaseView {

    /**
     * 对象要给子类用，所以要在其实例化的地方进行注解
     */
    @Inject
    public T presenter;
    private CommonDialog loginCommonDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        injectPresenter();
        if (presenter != null) presenter.attachView(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
        presenter = null;
        if (loginCommonDialog != null && loginCommonDialog.isShowing()) loginCommonDialog.dismiss();
    }

    public ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(DaYiApp.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    private ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    protected abstract void injectPresenter();

    @Override
    public void onSuccess(Object response, int tag) {
    }

    @Override
    public void onError(String errorMsg, String code, int tag, Object response) {
        LogUtil.i("接口请求失败" + ";errorMsg=" + errorMsg + ";errorcode=" + code + ";tag=" + tag);
        if (TextUtils.isEmpty(code)) {
            toast(errorMsg);
            return;
        }
        switch (code) {
            case NetResponseCode.HMC_NO_LOGIN:

                break;
            default:
                toast(errorMsg);
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (loginCommonDialog != null && loginCommonDialog.isShowing()) loginCommonDialog.dismiss();
    }
}
