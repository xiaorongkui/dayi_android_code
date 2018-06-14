package com.mall.dayi.net.api;


import com.mall.dayi.bean.response.BaseRes;
import com.mall.dayi.common.AppNetConfig;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Yun on 2018/1/5.
 * 网络请求接口,入参统一使用json,
 */
public interface MemberService {
    //获取我的信息
    @GET(AppNetConfig.urlPath + "wap/userInfo/show")
    Observable<BaseRes<Object>> getMineInfo();
}
