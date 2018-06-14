package com.mall.dayi.net.api;


import com.mall.dayi.bean.response.BaseRes;
import com.mall.dayi.common.AppNetConfig;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


/**
 * The interface Exchange service.
 */
public interface CommunityService {
    /**
     * 交易中心币种获取
     *
     * @return ExchangeCurrencyRes exchange currency
     */
    @GET(AppNetConfig.urlPath + "wap/tradeCenter/currencyInfo")
    Observable<BaseRes<Object>> getExchangeCurrency();

    /**
     * 交易中心数据
     *
     * @param req the req
     * @return ExchangeCenterRes exchange center data
     */
    @POST(AppNetConfig.urlPath + "wap/tradeCenter/getWapTradeCenterInfo")
    Observable<BaseRes<Object>> getExchangeCenterData(@Body Object req);

}
