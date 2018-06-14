package com.mall.dayi.common;

public final class AppNetConfig {
    public static final String urlPath = "jydp/";//测试环境

    //        public static final String BASE_URL = "http://test.oksheng.com.cn/";//测试环境
    public static final String BASE_URL = "http://121.43.160.46:8101";

    //kline里的H5页面
    public static final String kline_url = BASE_URL + "/jydp/userWap/wapDealRecord/toChartPage/";

    public static final String SYSTEM_NOTICE_URL = BASE_URL + "/jydp/userWap/wapSystemNotice/showNoticeDetailApp/";
    public static final String HOT_TOPIC_URL = BASE_URL + "/jydp/userWap/wapSystemHot/showHotDetailApp/";
    public static final String HELP_CENTER_URL = BASE_URL + "/jydp/userWap/wapHelpCenter/showApp/";
    //.....所有的项目当中接口的请求url全部配置在这里.....//
}
