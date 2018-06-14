package com.mall.dayi;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mall.dayi.base.BaseActivity;
import com.mall.dayi.common.Constants;
import com.mall.dayi.manager.ResourcesManager;
import com.mall.dayi.module.community.view.CommunityFragment;
import com.mall.dayi.module.globalvillage.view.GlobAlvillageFragment;
import com.mall.dayi.module.home.view.HomeFragment;
import com.mall.dayi.module.member.view.MemberFragment;
import com.mall.dayi.module.shoppingcart.view.ShoppingCartFragment;
import com.mall.dayi.util.CommonUtil;
import com.mall.dayi.util.LogUtil;
import com.mall.dayi.util.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;


public class MainActivity extends BaseActivity {

    @BindView(R.id.iv_home)
    ImageView ivHome;
    @BindView(R.id.tv_home)
    TextView tvHome;
    @BindView(R.id.iv_globalvillage)
    ImageView ivGlobalvillage;
    @BindView(R.id.tv_globalvillage)
    TextView tvGlobalvillage;
    @BindView(R.id.iv_community)
    ImageView ivCommunity;
    @BindView(R.id.tv_community)
    TextView tvCommunity;
    @BindView(R.id.iv_shopping_cart)
    ImageView ivShoppingCart;
    @BindView(R.id.tv_shopping_cart)
    TextView tvShoppingCart;
    @BindView(R.id.iv_member)
    ImageView ivMember;
    @BindView(R.id.tv_member)
    TextView tvMember;
    private FragmentTransaction ft;
    private HomeFragment homeFragment;
    public static final int HOME = 0;
    public static final int GLOB_ALVILLAGE = 1;
    public static final int COMMUNITY = 2;
    public static final int SHOPPING_CART = 3;
    public static final int MEMBER = 4;
    private GlobAlvillageFragment globAlvillageFragment;
    private CommunityFragment communityFragment;
    private ShoppingCartFragment shoppingCartFragment;
    private MemberFragment memberFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= 21) {
            setTheme(R.style.mainActivityTheme);
            CommonUtil.setStatusBarInvisible(this, false);
        } else {
            setTheme(R.style.BaseAppTheme);
        }
        super.onCreate(savedInstanceState);
        LogUtil.i("MainActivity onCreate,状态栏高度=" + CommonUtil.getStatusBarHeight() + ";\t 分辨率=" +
                CommonUtil.getAndroidPix(mContext) + ";\t设备信息");
    }

    private long recodeTime = 0;

    @Override
    protected void initView() {
        setSelect(0);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {
            int index = intent.getIntExtra(Constants.INTENT_PARAMETER_1, 0);//默认进入交易中心页面
            switch (index) {
                case HOME://首页
                    setSelect(0);
                    break;
                case GLOB_ALVILLAGE://地球村
                    setSelect(1);
                    break;
                case COMMUNITY://社区
                    setSelect(2);
                    break;
                case SHOPPING_CART://购物车
                    setSelect(3);
                    break;
                case MEMBER://会员
                    setSelect(4);
                    break;
            }
        }
    }

    @Override
    protected void initTitle() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - recodeTime < 2000) {
                //退出程序
                DaYiApp.exit();
            } else {
                ToastUtil.toast(this, getResources().getString(R.string.click_exit));
                recodeTime = System.currentTimeMillis();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.ll_home, R.id.ll_globalvillage, R.id.ll_community, R.id.ll_shopping_cart, R.id.ll_member})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.ll_home:
                setSelect(0);
                break;
            case R.id.ll_globalvillage:
                setSelect(1);
                break;
            case R.id.ll_community:
                setSelect(2);
                break;
            case R.id.ll_shopping_cart:
                setSelect(3);
                break;
            case R.id.ll_member:
                setSelect(4);
                break;
        }
    }

    public void setSelect(int i) {
        FragmentManager fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        hideFragments();
        resetImages();

        switch (i) {
            case 0://首页
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    ft.add(R.id.main_container, homeFragment);
                }
                ft.show(homeFragment);
                ivHome.setImageResource(R.mipmap.home_select);
                tvHome.setTextColor(ResourcesManager.getRBGColor(255, 51, 51));
                break;
            case 1://地球村
                if (globAlvillageFragment == null) {
                    globAlvillageFragment = new GlobAlvillageFragment();
                    ft.add(R.id.main_container, globAlvillageFragment);
                }
                ft.show(globAlvillageFragment);
                ivGlobalvillage.setImageResource(R.mipmap.globalvillage_select);
                tvGlobalvillage.setTextColor(ResourcesManager.getRBGColor(255, 51, 51));
                break;
            case 2://社区
                if (communityFragment == null) {
                    communityFragment = new CommunityFragment();
                    ft.add(R.id.main_container, communityFragment);
                }
                ft.show(communityFragment);
                ivCommunity.setImageResource(R.mipmap.community_select);
                tvCommunity.setTextColor(ResourcesManager.getRBGColor(255, 51, 51));
                break;
            case 3://购物车
                if (shoppingCartFragment == null) {
                    shoppingCartFragment = new ShoppingCartFragment();
                    ft.add(R.id.main_container, shoppingCartFragment);
                }
                ft.show(shoppingCartFragment);
                ivShoppingCart.setImageResource(R.mipmap.shopping_cart_select);
                tvShoppingCart.setTextColor(ResourcesManager.getRBGColor(255, 51, 51));
                break;
            case 4://会员
                if (memberFragment == null) {
                    memberFragment = new MemberFragment();
                    ft.add(R.id.main_container, memberFragment);
                }
                ft.show(memberFragment);
                ivMember.setImageResource(R.mipmap.member_select);
                tvMember.setTextColor(ResourcesManager.getRBGColor(255, 51, 51));
                break;
        }
        ft.commit();
    }


    private void hideFragments() {
        if (homeFragment != null) {
            ft.hide(homeFragment);
        }
        if (globAlvillageFragment != null) {
            ft.hide(globAlvillageFragment);
        }
        if (communityFragment != null) {
            ft.hide(communityFragment);
        }
        if (shoppingCartFragment != null) {
            ft.hide(shoppingCartFragment);
        }
        if (memberFragment != null) {
            ft.hide(memberFragment);
        }
    }


    private void resetImages() {
        ivHome.setImageResource(R.mipmap.home_unselect);
        ivGlobalvillage.setImageResource(R.mipmap.globalaillage_unselect);
        ivCommunity.setImageResource(R.mipmap.community_unselect);
        ivShoppingCart.setImageResource(R.mipmap.shopping_cart_unselect);
        ivMember.setImageResource(R.mipmap.member_unselect);

        tvHome.setTextColor(ResourcesManager.getRBGColor(51, 51, 51));
        tvGlobalvillage.setTextColor(ResourcesManager.getRBGColor(51, 51, 51));
        tvCommunity.setTextColor(ResourcesManager.getRBGColor(51, 51, 51));
        tvShoppingCart.setTextColor(ResourcesManager.getRBGColor(51, 51, 51));
        tvMember.setTextColor(ResourcesManager.getRBGColor(51, 51, 51));
    }

    /**
     * 是否使用沉浸式状态栏，默认为true；
     */
    @Override
    public boolean isImmersiveStatusBar() {
        return Build.VERSION.SDK_INT < 21;
    }


}
