package com.mall.dayi.net.api;


import com.mall.dayi.bean.response.BaseRes;
import com.mall.dayi.common.NetResponseCode;
import com.mall.dayi.net.exception.HandlerException;
import com.mall.dayi.util.LogUtil;

import io.reactivex.functions.Function;


/**
 * 请求返回结果的过滤
 */
public class BaseNetFunction<T> implements Function<BaseRes<T>, T> {
    public BaseNetFunction() {
    }

    @Override
    public T apply(BaseRes<T> resultEntry) throws Exception {

        String responseCode = resultEntry.getCode();
        String responseMessage = resultEntry.getMessage();
        T data = resultEntry.getData();
        switch (responseCode) {
            case NetResponseCode.HMC_SUCCESS:
                LogUtil.i(data == null ? "null" : "请求成功");
                if (data == null) {
                    throw new HandlerException.ResponeThrowable(responseMessage, NetResponseCode.HMC_SUCCESS_NULL);
                }
                return data;
            default:
                throw new HandlerException.ResponeThrowable(responseMessage, responseCode, data);
        }
    }
}
