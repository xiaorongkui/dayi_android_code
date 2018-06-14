package com.mall.dayi.module.shoppingcart.view;

import com.mall.dayi.R;
import com.mall.dayi.base.BaseMvpFragment;
import com.mall.dayi.module.shoppingcart.presenter.ShoppingCartPresenter;

/**
 * author：rongkui.xiao --2018/6/7
 * email：dovexiaoen@163.com
 * description:购物车模块
 */

public class ShoppingCartFragment extends BaseMvpFragment<ShoppingCartPresenter> {
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
        return R.layout.shoppingcart_fragment_shoppingcart;
    }

    @Override
    protected String getSimpleNme() {
        return getClass().getSimpleName();
    }
}
