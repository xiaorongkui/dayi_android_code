package com.mall.dayi.module.member.view;

import com.mall.dayi.R;
import com.mall.dayi.base.BaseMvpFragment;
import com.mall.dayi.module.member.presenter.MemberPresenter;

/**
 * author：rongkui.xiao --2018/6/7
 * email：dovexiaoen@163.com
 * description:会员管理模块
 */

public class MemberFragment extends BaseMvpFragment<MemberPresenter> {
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
        return R.layout.member_fragment_member;
    }

    @Override
    protected String getSimpleNme() {
        return getClass().getSimpleName();
    }
}
