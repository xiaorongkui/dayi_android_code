package com.mall.dayi.module.globalvillage.view;

import com.mall.dayi.R;
import com.mall.dayi.base.BaseMvpFragment;
import com.mall.dayi.module.globalvillage.presenter.GlobAlvillagePresenter;

/**
 * author：rongkui.xiao --2018/6/7
 * email：dovexiaoen@163.com
 * description:
 */

public class GlobAlvillageFragment extends BaseMvpFragment<GlobAlvillagePresenter> {
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
        return R.layout.globalvillage_fragment_globalvillage;
    }

    @Override
    protected String getSimpleNme() {
        return getClass().getSimpleName();
    }
}
