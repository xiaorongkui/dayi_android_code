package com.mall.dayi.module.community.view;

import com.mall.dayi.R;
import com.mall.dayi.base.BaseMvpFragment;
import com.mall.dayi.module.community.presenter.CommunityPresenter;

/**
 * author：rongkui.xiao --2018/6/7
 * email：dovexiaoen@163.com
 * description:社区模块
 */

public class CommunityFragment extends BaseMvpFragment<CommunityPresenter> {
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
        return R.layout.community_fragment_community;
    }

    @Override
    protected String getSimpleNme() {
        return getClass().getSimpleName();
    }
}
