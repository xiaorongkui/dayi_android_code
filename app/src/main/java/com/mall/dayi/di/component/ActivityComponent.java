package com.mall.dayi.di.component;

import android.app.Activity;
import android.content.Context;

import com.mall.dayi.MainActivity;
import com.mall.dayi.di.module.ActivityModule;
import com.mall.dayi.di.scope.ActivityScope;
import com.mall.dayi.module.login.view.LoginActivity;
import com.mall.dayi.module.login.view.SplashActivity;

import dagger.Component;


@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();

    Context getContext();

    void inject(MainActivity mainActivity);

    void inject(SplashActivity splashActivity);

    void inject(LoginActivity loginActivity);
}
