package com.mall.dayi.module.shoppingcart.presenter;

import android.content.Context;

import com.mall.dayi.base.BaseRxPresenter;
import com.mall.dayi.base.BaseView;
import com.mall.dayi.net.api.ShoppingCartService;

import javax.inject.Inject;

/**
 * author：rongkui.xiao --2018/3/16
 * email：dovexiaoen@163.com
 * description:登录模块的所有网络请求
 */

public class ShoppingCartPresenter extends BaseRxPresenter<BaseView> {
    @Inject
    ShoppingCartService shoppingCartService;

    @Inject
    public ShoppingCartPresenter(Context context) {
        super(context);
    }

}
