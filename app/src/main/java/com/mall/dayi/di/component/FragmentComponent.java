package com.mall.dayi.di.component;


import android.content.Context;
import android.support.v4.app.Fragment;

import com.mall.dayi.di.module.FragmentModule;
import com.mall.dayi.di.scope.FragmentScope;
import com.mall.dayi.module.community.view.CommunityFragment;
import com.mall.dayi.module.globalvillage.view.GlobAlvillageFragment;
import com.mall.dayi.module.home.view.HomeFragment;
import com.mall.dayi.module.member.view.MemberFragment;
import com.mall.dayi.module.shoppingcart.view.ShoppingCartFragment;

import dagger.Component;

/**
 * 将对象注解到实力对象的接口
 */
@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    Fragment getFragment();

    Context getContext();

    void inject(HomeFragment homeFragment);

    void inject(GlobAlvillageFragment globAlvillageFragment);

    void inject(CommunityFragment communityFragment);

    void inject(ShoppingCartFragment shoppingCartFragment);

    void inject(MemberFragment memberFragment);
}
