package com.mall.dayi.module.login.presenter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import com.google.gson.Gson;
import com.mall.dayi.base.BaseRxPresenter;
import com.mall.dayi.module.login.modle.LoginContract;
import com.mall.dayi.net.api.LoginService;
import com.mall.dayi.rx.RxPermissionUtils;
import com.mall.dayi.util.CommonUtil;
import com.mall.dayi.util.LogUtil;
import com.mall.dayi.util.ToastUtil;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * author：rongkui.xiao --2018/3/16
 * email：dovexiaoen@163.com
 * description:登录模块的所有网络请求
 */

public class LoginPresenter extends BaseRxPresenter<LoginContract.UpdateView> {
    @Inject
    LoginService loginService;

    public LoginService getLoginService() {
        return loginService;
    }

    @Inject
    public LoginPresenter(Context context) {
        super(context);
    }


    public void loginStart( int tag, boolean isShowProgress) {
        sendHttpRequest(loginService.startLogin(""), tag);
    }
    public void submitCertify( byte[] frontBytes, byte[] backBytes, int tag) {
        RequestBody data = RequestBody.create(MediaType.parse("application/json"), new Gson().toJson(""));
        RequestBody frontRequestBody = MultipartBody.create(MediaType.parse("image/jpg"), frontBytes);
        RequestBody backRequestBody = MultipartBody.create(MediaType.parse("image/jpg"), backBytes);
        sendHttpRequest(loginService.submitCertify(data, frontRequestBody, backRequestBody), tag);
    }


    public void checkAppUpdate(int tag, boolean isShowProgress) {
        sendHttpRequest(null, tag, isShowProgress);
    }

    /**
     * 下载app
     *
     * @param mContext
     * @param url
     */
    public void downLoadApk(Activity mContext, String url) {
        LogUtil.i("开始下载url=" + url);
        RxPermissionUtils.getInstance(mContext).setPermission(Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission
                        .READ_PHONE_STATE).setOnPermissionCallBack(new RxPermissionUtils
                .OnPermissionListener() {

            @Override
            public void onPermissionGranted(String name) {
                LogUtil.i("onPermissionGranted name=" + name);
            }

            @Override
            protected void onPermissionException(Throwable e) {
                super.onPermissionException(e);
                ToastUtil.toast(mContext, "权限申请失败" + e.getMessage());
            }

            @Override
            protected void onAllPermissionFinish() {
                FinalHttp finalHttp = new FinalHttp();
                String target = CommonUtil.createDir("download", CommonUtil.getFileNameFromUrl(url));
                finalHttp.download(url, target, false, new AjaxCallBack<File>() {

                    @Override
                    public void onLoading(long count, long current) {
                        super.onLoading(count, current);
                        int progrss = (int) ((float) current / (float) count) * 100;
                        LogUtil.i("下载进度=progrss" + progrss);
                        mView.update(progrss);
                    }

                    @Override
                    public void onSuccess(File file) {
                        LogUtil.i("下载进度=progrss" + "下载完成");
                        super.onSuccess(file);
                        mView.downLoadFinish(file);
                    }

                    @Override
                    public void onFailure(Throwable t, int errorNo, String strMsg) {
                        super.onFailure(t, errorNo, strMsg);
                        LogUtil.i("下载进度=progrss" + "onFailure" + strMsg);
                        mView.downLoadFailed();
                    }
                });
            }
        }).start();

    }

    /**
     * 描述：打开并安装文件.
     *
     * @param context the context
     * @param file    apk文件路径
     */
    public void installApk(Context context, File file) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Uri fileUri = FileProvider.getUriForFile(context, "com.mall.dayi.fileProvider",
                    file);//android 7.0以上
            intent.setDataAndType(fileUri, "application/vnd.android.package-archive");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            grantUriPermission(context, fileUri, intent);
        } else {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android" + "" +
                    ".package-archive");
        }
        context.startActivity(intent);
    }

    private void grantUriPermission(Context context, Uri fileUri, Intent intent) {
        List<ResolveInfo> resInfoList = context.getPackageManager().queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);
        for (ResolveInfo resolveInfo : resInfoList) {
            String packageName = resolveInfo.activityInfo.packageName;
            context.grantUriPermission(packageName, fileUri, Intent
                    .FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
    }
}
