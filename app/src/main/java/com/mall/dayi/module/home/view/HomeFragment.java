package com.mall.dayi.module.home.view;

import com.mall.dayi.R;
import com.mall.dayi.base.BaseMvpFragment;
import com.mall.dayi.module.home.presenter.HomePresenter;

/**
 * author：rongkui.xiao --2018/6/7
 * email：dovexiaoen@163.com
 * description:
 */

public class HomeFragment extends BaseMvpFragment<HomePresenter> {
    @Override
    protected void injectPresenter() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.home_fragment_home;
    }

    @Override
    protected String getSimpleNme() {
        return getClass().getSimpleName();
    }
}
