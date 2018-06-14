package com.mall.dayi.di.module;


import android.text.TextUtils;

import com.mall.dayi.BuildConfig;
import com.mall.dayi.common.AppNetConfig;
import com.mall.dayi.common.Constants;
import com.mall.dayi.net.api.BaseNetFunction;
import com.mall.dayi.net.api.CommunityService;
import com.mall.dayi.net.api.HomeService;
import com.mall.dayi.net.api.LoginService;
import com.mall.dayi.net.api.MemberService;
import com.mall.dayi.net.api.ShoppingCartService;
import com.mall.dayi.net.api.GlobAlvillageService;
import com.mall.dayi.util.CommonUtil;
import com.mall.dayi.util.LogUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class HttpModule {

    @Provides
    @Singleton
    Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }

    @Provides
    @Singleton
    OkHttpClient.Builder provideOkHttpBuilder() {
        return new OkHttpClient.Builder();
    }

    @Provides
    @Singleton
    BaseNetFunction provideBaseNetFunction() {
        return new BaseNetFunction();
    }

    @Provides
    @Singleton
    OkHttpClient provideClient(OkHttpClient.Builder builder) {
        if (BuildConfig.LOG_DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message -> {
                LogUtil.i("OkHttp====" + message);
            });
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }
        //设置Http缓存
        Cache cache = new Cache(new File(CommonUtil.getCacheDir(), "httpCache"), 1024 * 1024 * 10);
        return builder.addInterceptor(new TokenInterceptor())
                .addNetworkInterceptor(new SleepInterceptor())
                .cache(cache)
                .connectTimeout(Constants.connectionTime, TimeUnit.SECONDS)
                .readTimeout(Constants.connectionTime, TimeUnit.SECONDS)
                .writeTimeout(Constants.connectionTime, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
    }


    @Provides
    @Singleton
    Retrofit provideRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, AppNetConfig.BASE_URL);
    }

    @Provides
    @Singleton
    LoginService provideLoginService(Retrofit retrofit) {
        return retrofit.create(LoginService.class);
    }

    @Provides
    @Singleton
    HomeService provideHomeService(Retrofit retrofit) {
        return retrofit.create(HomeService.class);
    }

    @Provides
    @Singleton
    GlobAlvillageService provideGlobAlvillageService(Retrofit retrofit) {
        return retrofit.create(GlobAlvillageService.class);
    }

    @Provides
    @Singleton
    CommunityService provideCommunityService(Retrofit retrofit) {
        return retrofit.create(CommunityService.class);
    }


    @Provides
    @Singleton
    ShoppingCartService provideShoppingCartService(Retrofit retrofit) {
        return retrofit.create(ShoppingCartService.class);
    }

    @Provides
    @Singleton
    MemberService provideMemberService(Retrofit retrofit) {
        return retrofit.create(MemberService.class);
    }

    private Retrofit createRetrofit(Retrofit.Builder builder, OkHttpClient client, String url) {
//        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
        return builder.baseUrl(url)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    /**
     * token拦截器
     */
    private class TokenInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request oldRequest = chain.request();
            String token = CommonUtil.getToken();
            LogUtil.i("OkHttp====token；" + token);
            Request.Builder builder = oldRequest.newBuilder()
                    .addHeader("JYDP_PUBLIC_KEY", Constants.JYDP_PUBLIC_KEY);
            if (TextUtils.isEmpty(token)) {
                return chain.proceed(builder.build());
            }
            builder.addHeader("X-Access-Auth-Token", token).build();
            return chain.proceed(builder.build());
        }
    }

    /**
     * 平滑请求时间拦截器
     */
    private class SleepInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            long start = System.currentTimeMillis();
            Response response = chain.proceed(chain.request());
            if (System.currentTimeMillis() - start < 1000) {
                try {
                    Thread.sleep(700);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return response;
        }
    }

}
