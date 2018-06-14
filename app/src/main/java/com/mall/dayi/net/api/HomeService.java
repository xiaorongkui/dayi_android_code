package com.mall.dayi.net.api;


import com.mall.dayi.bean.response.BaseRes;
import com.mall.dayi.common.AppNetConfig;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Yun on 2018/1/5.
 * 网络请求接口,入参统一使用map集合,
 */
public interface HomeService {
    /**
     * 首页接口
     *
     * @return the home auto roll product
     */
    @GET(AppNetConfig.urlPath + "wap/homePage/show")
    Observable<BaseRes<Object>> getCurrentPrice();
}
