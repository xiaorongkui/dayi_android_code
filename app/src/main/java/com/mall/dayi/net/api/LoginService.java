package com.mall.dayi.net.api;


import com.mall.dayi.bean.response.BaseRes;
import com.mall.dayi.common.AppNetConfig;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * 网络请求接口,入参统一使用map集合,
 */
public interface LoginService {
    /**
     * 用户登录
     *
     * @param req the maps
     * @return the home auto roll product
     */
    @POST(AppNetConfig.urlPath + "wap/userLogin/login")
    Observable<BaseRes<Object>> startLogin(@Body Object req);

    /**
     * 实名认证
     */
    @Multipart
    @POST(AppNetConfig.urlPath + "wap/identificationController/add")
    Observable<BaseRes<Object>> submitCertify(@Part("data") RequestBody req, @Part("frontImg\"; filename = " +
            "\"frontImg.jpg") RequestBody frontFile, @Part("backImg\"; filename = \"backImg.jpg") RequestBody backFile);


}
