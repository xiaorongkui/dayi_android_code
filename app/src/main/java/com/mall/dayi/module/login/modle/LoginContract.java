package com.mall.dayi.module.login.modle;


import com.mall.dayi.base.BaseView;

import java.io.File;

public interface LoginContract {

    interface UpdateView extends BaseView {
        void update(int progrss);

        void downLoadFinish(File file);

        void downLoadFailed();
    }

}
