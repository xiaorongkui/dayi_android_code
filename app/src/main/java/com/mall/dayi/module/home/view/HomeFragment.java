package com.mall.dayi.module.home.view;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.mall.dayi.R;
import com.mall.dayi.base.BaseMvpFragment;
import com.mall.dayi.module.home.presenter.BannerImageLoader;
import com.mall.dayi.module.home.presenter.HomePresenter;
import com.mall.dayi.ui.widget.AutoHeighBanner;
import com.mall.dayi.ui.widget.FullGridView;
import com.mall.dayi.ui.widget.UPMarqueeView;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author：rongkui.xiao --2018/6/7
 * email：dovexiaoen@163.com
 * description:
 */

public class HomeFragment extends BaseMvpFragment<HomePresenter> {
    @BindView(R.id.home_auto_ll)
    AutoHeighBanner autoHeighBanner;
    @BindView(R.id.home_program_gv)
    FullGridView homeProgramGv;
    @BindView(R.id.marquee_home_header_notice)
    UPMarqueeView marqueeHomeHeaderNotice;
    @BindView(R.id.upMarqueeView_fl)
    FrameLayout upMarqueeViewFl;
    @BindView(R.id.home_dayi_selected_rv)
    RecyclerView homeDayiSelectedRv;
    @BindView(R.id.recommended_for_you_ahb)
    AutoHeighBanner recommendedForYouAhb;
    @BindView(R.id.home_hot_business_gv)
    FullGridView homeHotBusinessGv;
    Unbinder unbinder;
    private List<String> bannerList = new ArrayList<>();  //首页轮播图

    @Override
    protected void injectPresenter() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initView() {
        initBanner();
        initProgramGridView();
        initMarqueeView();
        initDayiSelectedRecyclerView();
        initRecommendedBanner();
        initHotBusinessRecyclerView();
    }


    @Override
    protected void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.home_fragment_home;
    }

    @Override
    protected String getSimpleNme() {
        return getClass().getSimpleName();
    }

    /**
     * 初始化Banner
     */
    private void initBanner() {
        //设置banner样式
        autoHeighBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        autoHeighBanner.setImageLoader(new BannerImageLoader());
        //设置图片集合
        autoHeighBanner.setImages(bannerList);
        //设置banner动画效果
        autoHeighBanner.setBannerAnimation(Transformer.Default);
        //设置自动轮播，默认为true
        autoHeighBanner.isAutoPlay(true);
        autoHeighBanner.setOnBannerListener(position -> {
        });
        //设置轮播时间
        autoHeighBanner.setDelayTime(4000);
        //设置指示器位置（当banner模式中有指示器时）
        autoHeighBanner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        autoHeighBanner.start();
    }

    private void initHotBusinessRecyclerView() {

    }

    private void initRecommendedBanner() {
    }

    private void initDayiSelectedRecyclerView() {

    }

    private void initMarqueeView() {

    }

    private void initProgramGridView() {
    }

}
