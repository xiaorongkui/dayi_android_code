package com.mall.dayi.module.member.presenter;

import android.content.Context;

import com.mall.dayi.base.BaseRxPresenter;
import com.mall.dayi.base.BaseView;
import com.mall.dayi.module.login.modle.LoginContract;
import com.mall.dayi.net.api.HomeService;
import com.mall.dayi.net.api.MemberService;

import javax.inject.Inject;

/**
 * author：rongkui.xiao --2018/3/16
 * email：dovexiaoen@163.com
 * description:登录模块的所有网络请求
 */

public class MemberPresenter extends BaseRxPresenter<BaseView> {
    @Inject
    MemberService memberService;

    @Inject
    public MemberPresenter(Context context) {
        super(context);
    }

}
