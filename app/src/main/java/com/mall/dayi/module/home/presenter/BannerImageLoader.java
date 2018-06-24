package com.mall.dayi.module.home.presenter;

import android.content.Context;
import android.widget.ImageView;

import com.mall.dayi.R;
import com.mall.dayi.base.GlideApp;
import com.youth.banner.loader.ImageLoader;


public class BannerImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object obj, ImageView imageView) {
//        GlideApp.with(context)
//                .load(((HomeDataRes.SystemAdsHomepagesListBean) obj).getAdsImageUrlFormat())
//                .placeholder(R.mipmap.ic_banner_loading)
//                .error(R.mipmap.ic_banner_fail)
//                .into(imageView);
    }

    @Override
    public ImageView createImageView(Context context) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }
}
