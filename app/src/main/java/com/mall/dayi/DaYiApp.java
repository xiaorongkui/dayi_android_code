package com.mall.dayi;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import com.bumptech.glide.Glide;
import com.mall.dayi.common.Constants;
import com.mall.dayi.di.component.AppComponent;
import com.mall.dayi.di.component.DaggerAppComponent;
import com.mall.dayi.di.module.AppModule;
import com.mall.dayi.manager.ActivityManager;
import com.mall.dayi.util.DensityHelper;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.tencent.bugly.crashreport.CrashReport;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DaYiApp extends Application {
    private static DaYiApp context;
    private static AppComponent mAppComponent;

    public DaYiApp() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        initBugly();
        initLog();
        initScreenAdapter();
    }

    //用于做屏幕适配
    private void initScreenAdapter() {
        new DensityHelper(this, BuildConfig.DESIGN_WIDTH).activate();
    }


    /**
     * 初始化Bugly(APP异常捕获)
     */
    private void initBugly() {
        CrashReport.initCrashReport(getApplicationContext(), Constants.BUGLY_APP_ID, BuildConfig.DEBUG);
    }

    /**
     * 初始化Log
     */
    private void initLog() {
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    /**
     * 获取Application Context
     */
    public static DaYiApp getInstance() {
        return context;
    }

    /**
     * 设置app字体不随系统改变
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.fontScale != 1)//非默认值
            getResources();
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        if (res.getConfiguration().fontScale != 1) {//非默认值
            Configuration newConfig = new Configuration();
            newConfig.setToDefaults();//设置默认
            res.updateConfiguration(newConfig, res.getDisplayMetrics());
        }
        return res;
    }

    public static Context getContext() {
        return context;
    }

    /**
     * 退出应用
     */
    public static void exit() {
        ActivityManager.getInstance().removeCurrent();
        ActivityManager.getInstance().clear();
        System.exit(0);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        Observable.create(e -> Glide.get(context).clearDiskCache()).subscribeOn(Schedulers.io()).subscribeOn
                (AndroidSchedulers.mainThread()).subscribe(o -> Glide.get(context).clearMemory());
    }

    public static AppComponent getAppComponent() {
        if (mAppComponent == null) {
            mAppComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(context))
                    .build();
        }
        return mAppComponent;
    }

}
