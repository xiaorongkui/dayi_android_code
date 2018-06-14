package com.mall.dayi.module.login.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mall.dayi.MainActivity;
import com.mall.dayi.R;
import com.mall.dayi.base.BaseMvpActivity;
import com.mall.dayi.manager.ActivityManager;
import com.mall.dayi.module.login.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author：rongkui.xiao --2018/6/7
 * email：dovexiaoen@163.com
 * description:登录页面
 */

public class LoginActivity extends BaseMvpActivity<LoginPresenter> {
    @BindView(R.id.login_bt)
    Button loginBt;

    @Override
    protected void injectPresenter() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initTitle() {
        presenter.getLoginService().startLogin(null);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.login_activity_login;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.login_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_bt:
                ActivityManager.gotoActivity(mContext, MainActivity.class);
                ActivityManager.getInstance().removeCurrent();
                break;
        }
    }
}
