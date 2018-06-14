package com.mall.dayi.di.module;


import com.mall.dayi.DaYiApp;
import com.mall.dayi.util.SPHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * 提供全局的对象，通过注解生成
 */
@Module
public class AppModule {
    private DaYiApp myApplication;

    public AppModule(DaYiApp myApplication) {
        this.myApplication = myApplication;
    }

    @Provides
    @Singleton
    DaYiApp provideMyApplication() {
        return myApplication;
    }

    @Provides
    @Singleton
    SPHelper providePreferencesHelper() {
        return SPHelper.getInstance();
    }
}
