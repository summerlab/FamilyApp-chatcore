package com.ivollo.familychat.login;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.text.TextUtils;
import android.view.View;

import com.ivollo.commons.account.AccountApi;
import com.ivollo.commons.api.ApiCallback;
import com.ivollo.commons.api.NODATA;
import com.ivollo.commons.api.oauth.OAuth2;
import com.ivollo.commons.api.oauth.OAuth2LoginSuccessEvent;
import com.ivollo.commons.api.oauth.OAuth2TokenFailureEvent;
import com.ivollo.commons.binding.TwoWayBoundString;
import com.ivollo.commons.utils.MD5;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Comments:
 *
 * @author yining
 *         Created on 2016/3/18 10:03
 */
public class LoginVM {
    public ObservableBoolean loginInProgress = new ObservableBoolean(false);
    public ObservableField<String> loginBtnText = new ObservableField<>("登 陆");
    public ObservableField<String> registerBtnText = new ObservableField<>("注 册");
    public TwoWayBoundString username = new TwoWayBoundString();
    public TwoWayBoundString password = new TwoWayBoundString();

    private static final String TAG = "LoginVM";
    private AccountApi accountApi;
    private OAuth2 oAuth2;

    public LoginVM(OAuth2 oAuth2, AccountApi accountApi) {
        this.oAuth2 = oAuth2;
        this.accountApi = accountApi;
        EventBus.getDefault().register(this);
    }

    /**
     * 点击按钮触发用户登录事件
     */
    public void login(View v) {
        if (TextUtils.isEmpty(username.get())) {
            EventBus.getDefault().post(new OAuth2TokenFailureEvent("请输入手机号码"));
            return;
        } else if (TextUtils.isEmpty(password.get())) {
            EventBus.getDefault().post(new OAuth2TokenFailureEvent("请输入登陆密码"));
            return;
        }

        //禁用登录按钮
        loginInProcess(true);
        oAuth2.login(username.get(), password.get());
    }

    /**
     * 点击按钮触发用户注册事件
     */
    public void register(View v) {
        if (TextUtils.isEmpty(username.get())) {
            EventBus.getDefault().post(new OAuth2TokenFailureEvent("请输入手机号码"));
            return;
        } else if (TextUtils.isEmpty(password.get())) {
            EventBus.getDefault().post(new OAuth2TokenFailureEvent("请输入登陆密码"));
            return;
        }

        registerInProcess(true);
        accountApi.register(username.get(), MD5.encodePassword(password.get()), 1, "", "").enqueue(new ApiCallback<NODATA>() {
            @Override
            public void onApiSuccess(NODATA nodata) {
                registerInProcess(false);
                //注册成功后需要再执行一次登陆操作获取OAuth授权
                login(null);
            }

            @Override
            public void onApiFailure(int errorCode, String errorMessage) {
                registerInProcess(false);
                EventBus.getDefault().post(new OAuth2TokenFailureEvent(errorMessage));
            }
        });
    }

    @Subscribe
    public void onLoginSuccess(OAuth2LoginSuccessEvent event) {
        loginInProcess(true);
        //chatVM.login(String.valueOf(event.userInfo.id), password.get(), event.userInfo.nickname);
    }

    @Subscribe
    public void onLoginFailure(OAuth2TokenFailureEvent event) {
        loginInProcess(false);
    }

    private void loginInProcess(boolean inProcess) {
        loginBtnText.set(inProcess ? "登陆中..." : "登陆");
        loginInProgress.set(inProcess);
    }

    private void registerInProcess(boolean inProcess) {
        registerBtnText.set(inProcess ? "注册中..." : "注 册");
        loginInProgress.set(inProcess);
    }
}
