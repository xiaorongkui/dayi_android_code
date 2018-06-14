package com.mall.dayi.net.api;


import com.mall.dayi.bean.response.BaseRes;
import com.mall.dayi.common.AppNetConfig;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Yun on 2018/1/5.
 * 网络请求接口,入参统一使用map集合,
 */
public interface GlobAlvillageService {
    /**
     * 场外交易列表接口
     *
     */
    @POST(AppNetConfig.urlPath + "wap/otcTradeCenter/showMore")
    Observable<BaseRes<Object>> getOutsideExchangeData(@Body Object req);

}
