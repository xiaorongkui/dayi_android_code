package com.mall.dayi.di.component;

import com.mall.dayi.DaYiApp;
import com.mall.dayi.di.module.AppModule;
import com.mall.dayi.di.module.HttpModule;
import com.mall.dayi.net.api.BaseNetFunction;
import com.mall.dayi.net.api.CommunityService;
import com.mall.dayi.net.api.GlobAlvillageService;
import com.mall.dayi.net.api.HomeService;
import com.mall.dayi.net.api.LoginService;
import com.mall.dayi.net.api.MemberService;
import com.mall.dayi.net.api.ShoppingCartService;
import com.mall.dayi.util.SPHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 引入dragger2依赖注入，创建全局对象
 */
@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {
    DaYiApp getContext();

    LoginService provideLoginService();

    HomeService provideHomeService();

    CommunityService provideCommunityService();

    GlobAlvillageService provideGlobAlvillageService();

    ShoppingCartService provideShoppingCartService();

    MemberService provideMemberService();

    BaseNetFunction provideBaseNetFunction();

    SPHelper providePreferencesHelper();

}
